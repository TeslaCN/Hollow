package ltd.scau.utils.pe.parser.structure;

public class MeasuresBean {

    private ExtObjBean extObj;
    private String name;
    private String unit;
    private int id;
    private int sortIndex;

    public ExtObjBean getExtObj() {
        return extObj;
    }

    public void setExtObj(ExtObjBean extObj) {
        this.extObj = extObj;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(int sortIndex) {
        this.sortIndex = sortIndex;
    }
}
