package ltd.scau.entity.sports;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "classes")
public class Clazz {

    @Column(name = "class_id", unique = true, nullable = false)
    @Id
    private int id;

    private int grade;

    private String num;

    @Column(name = "class_name")
    private String name;

    @Column(name = "class_admin")
    private String classAdmin;

    @Column(name = "college_code")
    private int collegeCode;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCollegeCode() {
        return collegeCode;
    }

    public void setCollegeCode(int collegeCode) {
        this.collegeCode = collegeCode;
    }

    public String getClassAdmin() {
        return classAdmin;
    }

    public void setClassAdmin(String classAdmin) {
        this.classAdmin = classAdmin;
    }

    @Override
    public String toString() {
        return "Clazz{" +
                "id=" + id +
                ", grade=" + grade +
                ", num='" + num + '\'' +
                ", name='" + name + '\'' +
                ", collegeCode=" + collegeCode +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Clazz clazz = (Clazz) o;

        if (id != clazz.id) return false;
        if (grade != clazz.grade) return false;
        if (collegeCode != clazz.collegeCode) return false;
        if (num != null ? !num.equals(clazz.num) : clazz.num != null) return false;
        if (name != null ? !name.equals(clazz.name) : clazz.name != null) return false;
        return classAdmin != null ? classAdmin.equals(clazz.classAdmin) : clazz.classAdmin == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + grade;
        result = 31 * result + (num != null ? num.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (classAdmin != null ? classAdmin.hashCode() : 0);
        result = 31 * result + collegeCode;
        return result;
    }
}
