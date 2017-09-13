package ltd.scau.utils.pe.parser.structure;

import com.google.gson.annotations.SerializedName;
import ltd.scau.entity.sports.Clazz;

import java.util.List;

public class ExtObjBeanX {

    private SimplescoreBean simplescore;
    @SerializedName("class")
    private Clazz classX;

    private List<MeasuresBean> measures;

    public SimplescoreBean getSimplescore() {
        return simplescore;
    }

    public void setSimplescore(SimplescoreBean simplescore) {
        this.simplescore = simplescore;
    }

    public List<MeasuresBean> getMeasures() {
        return measures;
    }

    public void setMeasures(List<MeasuresBean> measures) {
        this.measures = measures;
    }

    public Clazz getClassX() {
        return classX;
    }

    public void setClassX(Clazz classX) {
        this.classX = classX;
    }
}
