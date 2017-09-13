package ltd.scau.utils.pe.parser.structure;

public class BestScoreBean {
    /**
     * id : 263010
     * examId : 1
     * studentId : 3502
     * bodyMeasureId : 9
     * score : 28
     * examTime : 0
     * updateTime : 1475146242891
     * scoreGrade : 及格
     * standardAttachScore : 0
     * standardScore : 62
     */

    private int id;
    private int examId;
    private int studentId;
    private double score;
    private long updateTime;
    private String scoreGrade;
    private double standardScore;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public String getScoreGrade() {
        return scoreGrade;
    }

    public void setScoreGrade(String scoreGrade) {
        this.scoreGrade = scoreGrade;
    }

    public double getStandardScore() {
        return standardScore;
    }

    public void setStandardScore(double standardScore) {
        this.standardScore = standardScore;
    }
}
