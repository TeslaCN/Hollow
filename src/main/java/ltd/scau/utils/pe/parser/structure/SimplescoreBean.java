package ltd.scau.utils.pe.parser.structure;

public class SimplescoreBean {
    /**
     * bodyMeasureId : 0
     * originalScore : 0
     * score : 81.2
     * attachOriginalScore : 0
     * attachScore : 0
     * text : 良好
     * message :
     */

    private int bodyMeasureId;
    private int originalScore;
    private double score;
    private String text;
    private String message;

    public int getBodyMeasureId() {
        return bodyMeasureId;
    }

    public void setBodyMeasureId(int bodyMeasureId) {
        this.bodyMeasureId = bodyMeasureId;
    }

    public int getOriginalScore() {
        return originalScore;
    }

    public void setOriginalScore(int originalScore) {
        this.originalScore = originalScore;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
