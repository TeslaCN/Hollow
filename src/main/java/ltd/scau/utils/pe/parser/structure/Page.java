package ltd.scau.utils.pe.parser.structure;

/**
 * 体测成绩读取为 JSON 格式，通过 gson 把 JSON 格式的数据解析为 Java 对象
 */
public class Page {

    private String message;
    private DataBean data;
    private int code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
