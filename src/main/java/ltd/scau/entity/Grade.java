package ltd.scau.entity;

import javax.persistence.Column;

public class Grade {

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
}
