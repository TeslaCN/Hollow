package ltd.scau.utils.pe.parser.structure;

public class BestSimpleScoreBean {
    /**
     * bodyMeasureId : 9
     * originalScore : 28
     * score : 60
     * attachOriginalScore : 0
     * attachScore : 0
     * text : 及格
     * message :
     */

    private double originalScore;
    private double score;
    private String text;
    private String message;

    public double getOriginalScore() {
        return originalScore;
    }

    public void setOriginalScore(double originalScore) {
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
