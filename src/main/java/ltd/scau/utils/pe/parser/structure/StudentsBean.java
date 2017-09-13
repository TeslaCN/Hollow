package ltd.scau.utils.pe.parser.structure;

public class StudentsBean {
    /**
     * extObj : {"simplescore":{"bodyMeasureId":0,"originalScore":0,"score":81.2,"attachOriginalScore":0,"attachScore":0,"text":"良好","message":""},"measures":[{"extObj":{"bestScore":{"id":263010,"examId":1,"studentId":3502,"bodyMeasureId":9,"score":28,"examTime":0,"updateTime":1475146242891,"scoreGrade":"及格","standardAttachScore":0,"standardScore":62},"bestSimpleScore":{"bodyMeasureId":9,"originalScore":28,"score":60,"attachOriginalScore":0,"attachScore":0,"text":"及格","message":""}},"id":9,"sortIndex":900,"name":"一分钟仰卧起坐","unit":"次"},{"extObj":{"bestScore":{"id":263009,"examId":1,"studentId":3502,"bodyMeasureId":8,"score":-1,"examTime":0,"updateTime":1475146242891,"scoreGrade":"","standardAttachScore":0,"standardScore":0},"bestSimpleScore":{"bodyMeasureId":8,"originalScore":-1,"score":-1,"attachOriginalScore":0,"attachScore":0,"text":"","message":""}},"id":8,"sortIndex":800,"name":"引体向上","unit":"次"},{"extObj":{"bestScore":{"id":263008,"examId":1,"studentId":3502,"bodyMeasureId":7,"score":13.6,"examTime":0,"updateTime":1475146242891,"scoreGrade":"及格","standardAttachScore":0,"standardScore":70},"bestSimpleScore":{"bodyMeasureId":7,"originalScore":13.6,"score":70,"attachOriginalScore":0,"attachScore":0,"text":"及格","message":""}},"id":7,"sortIndex":700,"name":"坐位体前屈","unit":"cm"},{"extObj":{"bestScore":{"id":263007,"examId":1,"studentId":3502,"bodyMeasureId":6,"score":173,"examTime":0,"updateTime":1475146242891,"scoreGrade":"及格","standardAttachScore":0,"standardScore":74},"bestSimpleScore":{"bodyMeasureId":6,"originalScore":173,"score":74,"attachOriginalScore":0,"attachScore":0,"text":"及格","message":""}},"id":6,"sortIndex":600,"name":"立定跳远","unit":"cm"},{"extObj":{"bestScore":{"id":263006,"examId":1,"studentId":3502,"bodyMeasureId":10,"score":220000,"examTime":0,"updateTime":1475146242891,"scoreGrade":"良好","standardAttachScore":0,"standardScore":80},"bestSimpleScore":{"bodyMeasureId":10,"originalScore":220000,"score":80,"attachOriginalScore":0,"attachScore":0,"text":"良好","message":""}},"id":10,"sortIndex":501,"name":"800米","unit":"ms"},{"extObj":{"bestScore":{"id":263005,"examId":1,"studentId":3502,"bodyMeasureId":5,"score":-1000,"examTime":0,"updateTime":1475146242891,"scoreGrade":"不及格","standardAttachScore":0,"standardScore":0},"bestSimpleScore":{"bodyMeasureId":5,"originalScore":-1000,"score":0,"attachOriginalScore":0,"attachScore":0,"text":"不及格","message":""}},"id":5,"sortIndex":500,"name":"1000米","unit":"ms"},{"extObj":{"bestScore":{"id":263004,"examId":1,"studentId":3502,"bodyMeasureId":4,"score":8700,"examTime":0,"updateTime":1475146242891,"scoreGrade":"及格","standardAttachScore":0,"standardScore":76},"bestSimpleScore":{"bodyMeasureId":4,"originalScore":8700,"score":74,"attachOriginalScore":0,"attachScore":0,"text":"及格","message":""}},"id":4,"sortIndex":400,"name":"50米","unit":"ms"},{"extObj":{"bestScore":{"id":263003,"examId":1,"studentId":3502,"bodyMeasureId":3,"score":3959,"examTime":0,"updateTime":1475146242891,"scoreGrade":"优秀","standardAttachScore":0,"standardScore":100},"bestSimpleScore":{"bodyMeasureId":3,"originalScore":3959,"score":100,"attachOriginalScore":0,"attachScore":0,"text":"优秀","message":""}},"id":3,"sortIndex":300,"name":"肺活量","unit":"ml"},{"extObj":{"bestScore":{"id":263002,"examId":1,"studentId":3502,"bodyMeasureId":2,"score":48.1,"examTime":0,"updateTime":1475146242891,"scoreGrade":"正常","standardAttachScore":0,"standardScore":100},"bestSimpleScore":{"bodyMeasureId":2,"originalScore":0,"score":-1,"attachOriginalScore":0,"attachScore":0,"text":"","message":""}},"id":2,"sortIndex":200,"name":"体重","unit":"kg"},{"extObj":{"bestScore":{"id":263001,"examId":1,"studentId":3502,"bodyMeasureId":1,"score":164.3,"examTime":0,"updateTime":1475146242891,"scoreGrade":"正常","standardAttachScore":0,"standardScore":100},"bestSimpleScore":{"bodyMeasureId":1,"originalScore":0,"score":-1,"attachOriginalScore":0,"attachScore":0,"text":"","message":""}},"id":1,"sortIndex":100,"name":"身高","unit":"cm"}],"class":{"id":8232,"schoolId":1,"grade":2015,"num":"2015100078232","name":"15食品安全2","classAdmin":"201522070201","collegeCode":10007}}
     * id : 3502
     * personNumber : 201522070222
     * name : 徐诗琪
     * classId : 8232
     * collegeCode : 0
     * nation : 1
     * birthday : 846864000000
     * gender : 2
     * headVerify : 0
     * barcodeUrl : http://static.youledong.com/studentBarcode/1/150234\201522070222.png
     */

    private ExtObjBeanX extObj;
    private int id;
    private String personNumber;
    private String name;
    private int classId;
    private long birthday;
    private int gender;
    private String barcodeUrl;

    public ExtObjBeanX getExtObj() {
        return extObj;
    }

    public void setExtObj(ExtObjBeanX extObj) {
        this.extObj = extObj;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPersonNumber() {
        return personNumber;
    }

    public void setPersonNumber(String personNumber) {
        this.personNumber = personNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getBarcodeUrl() {
        return barcodeUrl;
    }

    public void setBarcodeUrl(String barcodeUrl) {
        this.barcodeUrl = barcodeUrl;
    }
}
