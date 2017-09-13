package ltd.scau.utils.pe.parser.structure;

public class ExtObjBean {
    /**
     * bestScore : {"id":263010,"examId":1,"studentId":3502,"bodyMeasureId":9,"score":28,"examTime":0,"updateTime":1475146242891,"scoreGrade":"及格","standardAttachScore":0,"standardScore":62}
     * bestSimpleScore : {"bodyMeasureId":9,"originalScore":28,"score":60,"attachOriginalScore":0,"attachScore":0,"text":"及格","message":""}
     */

    private BestScoreBean bestScore;
    private BestSimpleScoreBean bestSimpleScore;

    public BestScoreBean getBestScore() {
        return bestScore;
    }

    public void setBestScore(BestScoreBean bestScore) {
        this.bestScore = bestScore;
    }

    public BestSimpleScoreBean getBestSimpleScore() {
        return bestSimpleScore;
    }

    public void setBestSimpleScore(BestSimpleScoreBean bestSimpleScore) {
        this.bestSimpleScore = bestSimpleScore;
    }
}
