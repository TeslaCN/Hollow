package ltd.scau.entity.sports;

import com.google.gson.annotations.SerializedName;

import javax.persistence.*;

@Entity
@Table(name = "classes")
public class Clazz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SerializedName("generate_id")
    @Column(name = "generate_id")
    private Integer id;

    @Column(name = "class_id")
    @SerializedName("id")
    private int classId;

    private int grade;

    @Column(length = 30)
    private String num;

    @Column(name = "class_name", length = 30)
    private String name;

    @Column(name = "class_admin", length = 30)
    private String classAdmin;

    @Column(name = "college_code")
    private int collegeCode;


    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
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
                ", classId=" + classId +
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

        if (classId != clazz.classId) return false;
        if (grade != clazz.grade) return false;
        if (collegeCode != clazz.collegeCode) return false;
        if (num != null ? !num.equals(clazz.num) : clazz.num != null) return false;
        if (name != null ? !name.equals(clazz.name) : clazz.name != null) return false;
        return classAdmin != null ? classAdmin.equals(clazz.classAdmin) : clazz.classAdmin == null;
    }

    @Override
    public int hashCode() {
        int result = classId;
        result = 31 * result + grade;
        result = 31 * result + (num != null ? num.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (classAdmin != null ? classAdmin.hashCode() : 0);
        result = 31 * result + collegeCode;
        return result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
