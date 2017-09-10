package ltd.scau.utils.jwc;

import ltd.scau.entity.Exam;
import ltd.scau.entity.Grade;
import ltd.scau.entity.dao.ExamDao;
import ltd.scau.entity.dao.GradeDao;
import ltd.scau.utils.crawler.Browser;
import ltd.scau.utils.crawler.HtmlParser;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

public class StudentLoader {

    private String[] hosts;

    public static final String LOGIN_PAGE = "http://%s/default_vsso.htm";
    public static final String LOGIN_POST = "http://%s/default_vsso.aspx";
    public static final String MAIN_PAGE = "http://%s/xs_main.aspx?xh=%s";
    public static final String GRADE = "http://%s/xscjcx.aspx?xh=%s&xm=%s&gnmkdm=N121605";
    public static final String COURSE= "http://%s/xskbcx.aspx?xh=%s&xm=%s&gnmkdm=N121603";
    public static final String EXAM="http://%s/xskscx.aspx?xh=%s&xm=%s&gnmkdm=N121604";

    private String id;

    private String password;

    private String nameEncoded;

    private String name;

    private Browser browser;

    private String error;

    private GradeDao gradeDao;

    private ExamDao examDao;

    public StudentLoader() {

    }

    public boolean execute() {
        for (String host : hosts) {
            if (!login(host)) continue;
            if (!loadGrade(host)) continue;
//            if (!loadCourse(host)) continue;
            if (!loadExam(host)) continue;
            return true;
        }
        return false;
    }

    public boolean login() {
        for (String host : hosts) {
            if (!login(host)) continue;
            return true;
        }
        return false;
    }

    public boolean login(String host) {
        getBrowser().setReferer(String.format(LOGIN_PAGE, host));
        getBrowser().setHost(host);
        Map<String, String> datas = new HashMap<>();
        datas.put("TextBox1", getId());
        datas.put("TextBox2", getPassword());
        datas.put("Button1", "");
        datas.put("RadioButtonList1_2", "学生");
        String html = getBrowser().post(String.format(LOGIN_POST, host), datas, "GBK");
        String tip = JwcParser.parseError(html);
        if (!tip.equals("")) {
            setError(tip);
            System.err.println(tip);
            return false;
        }
        String name = JwcParser.parseName(html);
        if (!name.equals("")) {
            setName(name);
            System.out.println(name);
            try {
                setNameEncoded(URLEncoder.encode(getName(), "GBK"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    public boolean loadGrade(String host) {
        String html = getBrowser().get(String.format(GRADE, host, getId(), getNameEncoded()), "GBK");
        Map<String, String> form = HtmlParser.parseForm(html);

        form.remove("btn_zg");
        form.remove("Button1");
        form.remove("Button2");
        form.remove("btn_xq");
        form.remove("btn_xn");
        form.remove("btn_dy");
        form.put("ddlXN", "");
        form.put("ddlXQ", "");
        form.put("ddl_kcxz", "");

        html = getBrowser().post(String.format(GRADE, host, getId(), getNameEncoded()), form, "GBK");
        String tip = JwcParser.parseError(html);
        if (!tip.equals("")) {
            setError(tip);
            System.err.println(tip);
            return false;
        }

        //TODO 此处代码效率有待改进

        List<Grade> dbData = gradeDao.findByStudentId(getId());
        Set<Grade> set = new HashSet<>();
        set.addAll(dbData);

        List<Grade> grades = JwcParser.parseGrade(html);
        if (grades == null) return false;
        for (Grade g : grades) {
            if (dbData.contains(g) && !dbData.get(dbData.indexOf(g)).detailEquals(g)) {
                g.setId(dbData.get(dbData.indexOf(g)).getId());
                if (set.contains(g)) {
                    set.remove(g);
                }
            }
            set.add(g);
        }
        getGradeDao().saveOrUpdateAll(set);
        return true;
    }

    public boolean loadCourse(String host) {
        String html = getBrowser().get(String.format(COURSE, host, getId(), getNameEncoded()), "GBK");
        String table = JwcParser.selectCourseTable(html);
        return true;
    }

    public boolean loadExam(String host) {
        String html = getBrowser().get(String.format(EXAM, host, getId(), getNameEncoded()), "GBK");
        List<Exam> dbData = examDao.findByStudentId(getId());
        Set<Exam> set = new HashSet<>();
        set.addAll(dbData);

        List<Exam> exams = JwcParser.parseExam(html);
        if (exams == null) return false;
        for (Exam e : exams) {
            if (dbData.contains(e) && !dbData.get(dbData.indexOf(e)).detailEquals(e)) {
                e.setId(dbData.get(dbData.indexOf(e)).getId());
                if (set.contains(e)) {
                    set.remove(e);
                }
            }
            set.add(e);
        }
        getExamDao().saveOrUpdateAll(set);
        return true;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getNameEncoded() {
        return nameEncoded;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Browser getBrowser() {
        return browser;
    }

    public void setBrowser(Browser browser) {
        this.browser = browser;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setNameEncoded(String nameEncoded) {
        this.nameEncoded = nameEncoded;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GradeDao getGradeDao() {
        return gradeDao;
    }

    public void setGradeDao(GradeDao gradeDao) {
        this.gradeDao = gradeDao;
    }

    public String[] getHosts() {
        return hosts;
    }

    public void setHosts(String[] hosts) {
        this.hosts = hosts;
    }

    public ExamDao getExamDao() {
        return examDao;
    }

    public void setExamDao(ExamDao examDao) {
        this.examDao = examDao;
    }
}
