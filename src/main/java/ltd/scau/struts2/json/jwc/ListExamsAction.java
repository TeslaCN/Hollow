package ltd.scau.struts2.json.jwc;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import ltd.scau.aspect.annotations.Student;
import ltd.scau.entity.Exam;
import ltd.scau.entity.User;
import ltd.scau.entity.dao.ExamDao;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;

import java.util.List;

@ParentPackage("hollow")
@Result(type = "json", params = {"includeProperties", "exams\\[\\d+\\]\\..*"})
public class ListExamsAction extends ActionSupport {

    private ExamDao examDao;

    private List<Exam> exams;

    @Override
    @Student
    public String execute() throws Exception {
        ActionContext ctx = ActionContext.getContext();
        setExams(getExamDao().findByStudentId(((User) ctx.getSession().get("user")).getStuId()));
        return SUCCESS;
    }

    @JSON(serialize = false)
    public ExamDao getExamDao() {
        return examDao;
    }

    public void setExamDao(ExamDao examDao) {
        this.examDao = examDao;
    }

    @JSON(serialize = false)
    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }
}
