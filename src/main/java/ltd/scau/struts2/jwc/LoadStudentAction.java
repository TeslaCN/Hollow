package ltd.scau.struts2.jwc;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import ltd.scau.aspect.annotations.Student;
import ltd.scau.entity.User;
import ltd.scau.utils.jwc.StudentLoader;
import org.apache.struts2.convention.annotation.ParentPackage;

@ParentPackage("hollow")
public class LoadStudentAction extends ActionSupport {

    private StudentLoader studentLoader;

    private String error;

    @Override
    @Student
    public String execute() throws Exception {
        ActionContext ctx = ActionContext.getContext();
        User user = (User) ctx.getSession().get("user");
        studentLoader.setId(user.getStuId());
        studentLoader.setPassword(user.getStuPassword());
        if (!studentLoader.execute()) {
            setError(studentLoader.getError());
            return ERROR;
        }
        return SUCCESS;
    }

    public StudentLoader getStudentLoader() {
        return studentLoader;
    }

    public void setStudentLoader(StudentLoader studentLoader) {
        this.studentLoader = studentLoader;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
