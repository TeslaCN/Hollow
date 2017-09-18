package ltd.scau.utils.pe;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import ltd.scau.entity.dao.sports.ClazzDao;
import ltd.scau.entity.dao.sports.RecordDao;
import ltd.scau.entity.dao.sports.StudentDao;
import ltd.scau.entity.sports.Clazz;
import ltd.scau.entity.sports.Item;
import ltd.scau.entity.sports.Record;
import ltd.scau.entity.sports.Student;
import ltd.scau.entity.type.GenderType;
import ltd.scau.utils.crawler.Browser;
import ltd.scau.utils.pe.parser.structure.MeasuresBean;
import ltd.scau.utils.pe.parser.structure.Page;
import ltd.scau.utils.pe.parser.structure.StudentsBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataLoader {

    private Browser browser;

    private StudentDao studentDao;

    private RecordDao recordDao;

    private ClazzDao clazzDao;

    public static final String TARGET = "http://api.youledong.com/admin/home/studentList";

    public static final String HOST = "api.youledong.com";

    public static final String DEFAULT_REFERER = "http://api.youledong.com/student/studentDate.html?_1";

    public String getJson(String page, String examId) {
        getBrowser().setHost(HOST);
        getBrowser().setReferer(DEFAULT_REFERER);
        getBrowser().getHeadersProperties().setProperty("Accept", "text/json");
        Map<String, String> datas = new HashMap<>();
        datas.put("page", page);
        datas.put("examId", examId);
        String json = getBrowser().post(TARGET, datas, "UTF-8");
        return json;
    }

    public boolean execute(String page, String examId) {
        return parseJson(getJson(page, examId), examId);
    }

    public boolean parseJson(String json, String examId) {
        Gson gson = new Gson();
        Page page;
        try {
            page = gson.fromJson(json, Page.class);
        } catch (IllegalStateException|JsonSyntaxException e) {
            Log log = LogFactory.getLog(DataLoader.class);
//            log.error(String.format("JSON: %s", json));
            throw e;
        }
        if (!page.getMessage().equals("success")) return false;
        for (StudentsBean s : page.getData().getStudents()) {
            Student student = getStudentDao().findByStudentId(s.getPersonNumber());
            if (student == null) student = new Student();
            student.setId(s.getId());
            student.setStuId(s.getPersonNumber());
            student.setBarcodeUrl(s.getBarcodeUrl());
            student.setStuName(s.getName());
            student.setBirthday(s.getBirthday());
            student.setGender(GenderType.values()[s.getGender()]);

            Clazz clazz = getClazzDao().findByNum(s.getExtObj().getClassX().getNum());
            student.setClazz(clazz);
            if (clazz == null) {
                getClazzDao().update(s.getExtObj().getClassX());
                student.setClazz(s.getExtObj().getClassX());
            }

            List<Record> records = student.getRecords();
            if (records == null) records = new ArrayList<>();
            Record record = new Record();

            student.setRecords(records);

            record.setStudent(student);
            record.setMessage(s.getExtObj().getSimplescore().getMessage());
            record.setTotalScore(s.getExtObj().getSimplescore().getScore());
            record.setText(s.getExtObj().getSimplescore().getText());
            record.setExamId(Integer.parseInt(examId));

            if (!record.getMessage().equals("免测") && !record.getText().equals("免测")) {


                List<Item> items = record.getItems();
                if (items == null) items = new ArrayList<>();
                for (MeasuresBean measure : s.getExtObj().getMeasures()) {
                    Item item = new Item();
                    item.setItemId(measure.getId());
                    item.setStuId(s.getPersonNumber());
                    item.setBestScore(measure.getExtObj().getBestScore().getStandardScore());
                    item.setValue(measure.getExtObj().getBestScore().getScore());
                    item.setName(measure.getName());
                    item.setUnit(measure.getUnit());
                    item.setUpdateTime(measure.getExtObj().getBestScore().getUpdateTime());
                    item.setExamId(measure.getExtObj().getBestScore().getExamId());
                    item.setScoreGrade(measure.getExtObj().getBestScore().getScoreGrade());
                    item.setRecord(record);
                    if (!items.contains(item)) items.add(item);
                }

                record.setItems(items);
            }

            if (!records.contains(record)) {
                for (Record r : records) {
                    if (r.getExamId().equals(record.getExamId())) {
                        record.setId(r.getId());
                        records.set(records.indexOf(r), record);
                        break;
                    }
                }
                records.add(record);
            }

            getStudentDao().merge(student);
        }
        return true;
    }

    public Browser getBrowser() {
        return browser;
    }

    public void setBrowser(Browser browser) {
        this.browser = browser;
    }

    public StudentDao getStudentDao() {
        return studentDao;
    }

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public RecordDao getRecordDao() {
        return recordDao;
    }

    public void setRecordDao(RecordDao recordDao) {
        this.recordDao = recordDao;
    }

    public ClazzDao getClazzDao() {
        return clazzDao;
    }

    public void setClazzDao(ClazzDao clazzDao) {
        this.clazzDao = clazzDao;
    }
}
