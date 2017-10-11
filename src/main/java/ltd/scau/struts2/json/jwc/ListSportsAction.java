package ltd.scau.struts2.json.jwc;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import ltd.scau.aspect.annotations.Ordinary;
import ltd.scau.entity.User;
import ltd.scau.entity.dao.sports.StudentDao;
import ltd.scau.entity.sports.Student;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;

@ParentPackage("hollow")
@Result(type = "json", params = {
        "excludeProperties",
        "student\\.records\\[\\d+\\]\\.(student|items\\[\\d+\\]\\.record)",
        "root", "student"
})
public class ListSportsAction extends ActionSupport {

    private StudentDao studentDao;

    private Student student;

    @Override
    public String execute() throws Exception {
        ActionContext ctx = ActionContext.getContext();
        String stuId = (String) ctx.getSession().get("stuId");
        if (stuId == null || stuId.trim().equals("")) {
            User user = (User) ctx.getSession().get("user");
            if (user == null || user.getStuId() == null) {
                return SUCCESS;
            }
            stuId = user.getStuId();
        }
        if (stuId != null && !stuId.trim().equals("")) {
            setStudent(getStudentDao().findByStudentId(stuId));
        }
        return SUCCESS;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @JSON(serialize = false)
    public StudentDao getStudentDao() {
        return studentDao;
    }

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }
}
