package ltd.scau.entity;

import javax.persistence.*;

@Entity
@Table(name = "grades")
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "stu_id", nullable = false)
    private String stuId;

    private String year = "";

    private String semester = "";

    @Column(nullable = false)
    private String code = "";

    @Column(nullable = false)
    private String title = "";

    private String property = "";

    private String attribution = "";

    private Double credit = 0.0;

    @Column(name = "GPA")
    private Double gpa = 0.0;

    private Double usual = 0.0;

    @Column(name = "mid_term")
    private Double midTerm = 0.0;

    @Column(name = "final_exam")
    private Double finalExam = 0.0;

    private Double experiment = 0.0;

    private Double total = 0.0;

    private String minor = "";

    private Double resit = 0.0;

    private Double retake = 0.0;

    private String college = "";

    private String remarks = "";

    @Column(name = "mark_retake")
    private String markRetake = "";

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getAttribution() {
        return attribution;
    }

    public void setAttribution(String attribution) {
        this.attribution = attribution;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public Double getGpa() {
        return gpa;
    }

    public void setGpa(Double gpa) {
        this.gpa = gpa;
    }

    public Double getUsual() {
        return usual;
    }

    public void setUsual(Double usual) {
        this.usual = usual;
    }

    public Double getMidTerm() {
        return midTerm;
    }

    public void setMidTerm(Double midTerm) {
        this.midTerm = midTerm;
    }

    public Double getFinalExam() {
        return finalExam;
    }

    public void setFinalExam(Double finalExam) {
        this.finalExam = finalExam;
    }

    public Double getExperiment() {
        return experiment;
    }

    public void setExperiment(Double experiment) {
        this.experiment = experiment;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getMinor() {
        return minor;
    }

    public void setMinor(String minor) {
        this.minor = minor;
    }

    public Double getResit() {
        return resit;
    }

    public void setResit(Double resit) {
        this.resit = resit;
    }

    public Double getRetake() {
        return retake;
    }

    public void setRetake(Double retake) {
        this.retake = retake;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getMarkRetake() {
        return markRetake;
    }

    public void setMarkRetake(String markRetake) {
        this.markRetake = markRetake;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Grade grade = (Grade) o;

        if (!stuId.equals(grade.stuId)) return false;
        if (!year.equals(grade.year)) return false;
        if (!semester.equals(grade.semester)) return false;
        if (!code.equals(grade.code)) return false;
        if (!title.equals(grade.title)) return false;
        if (property != null ? !property.equals(grade.property) : grade.property != null) return false;
        return attribution != null ? attribution.equals(grade.attribution) : grade.attribution == null;
    }

    public boolean detailEquals(Object o) {
        if (!equals(o)) return false;

        Grade grade = (Grade) o;

        if (!retake.equals(grade.retake)) return false;
        if (!gpa.equals(grade.gpa)) return false;
        if (!total.equals(grade.total)) return false;
        if (!finalExam.equals(grade.finalExam)) return false;
        if (!usual.equals(grade.usual)) return false;
        if (!markRetake.equals(grade.markRetake)) return false;
        if (!midTerm.equals(grade.midTerm)) return false;
        if (!credit.equals(grade.credit)) return false;
        if (!experiment.equals(grade.experiment)) return false;
        if (!resit.equals(grade.resit)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = stuId.hashCode();
        result = 31 * result + year.hashCode();
        result = 31 * result + semester.hashCode();
        result = 31 * result + code.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + (property != null ? property.hashCode() : 0);
        result = 31 * result + (attribution != null ? attribution.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", stuId='" + stuId + '\'' +
                ", year='" + year + '\'' +
                ", semester='" + semester + '\'' +
                ", title='" + title + '\'' +
                ", credit=" + credit +
                ", gpa=" + gpa +
                ", usual=" + usual +
                ", finalExam=" + finalExam +
                ", total=" + total +
                ", retake=" + retake +
                '}';
    }
}
