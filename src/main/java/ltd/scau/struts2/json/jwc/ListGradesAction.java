package ltd.scau.struts2.json.jwc;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import ltd.scau.aspect.annotations.Student;
import ltd.scau.entity.Grade;
import ltd.scau.entity.User;
import ltd.scau.entity.dao.GradeDao;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;

import java.util.List;

@ParentPackage("hollow")
@Result(type = "json", params = {"includeProperties", "grades\\[\\d+\\]\\..*"})
public class ListGradesAction extends ActionSupport {

    private GradeDao gradeDao;

    private List<Grade> grades;

    @Override
    @Student
    public String execute() throws Exception {
        ActionContext ctx = ActionContext.getContext();
        setGrades(getGradeDao().findByStudentId(((User) ctx.getSession().get("user")).getStuId()));
        return SUCCESS;
    }

    @JSON(serialize = false)
    public GradeDao getGradeDao() {
        return gradeDao;
    }

    public void setGradeDao(GradeDao gradeDao) {
        this.gradeDao = gradeDao;
    }

    @JSON(serialize = false)
    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }
}
