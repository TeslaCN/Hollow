package ltd.scau.struts2.jwc;

import com.opensymphony.xwork2.ActionSupport;
import ltd.scau.utils.pe.DataLoader;

public class SportAction extends ActionSupport {

    private String page;

    private String examId;

    private DataLoader dataLoader;

    @Override
    public String execute() throws Exception {
        getDataLoader().parseJson(getDataLoader().getJson(getPage(), getExamId()), getExamId());
        return NONE;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public DataLoader getDataLoader() {
        return dataLoader;
    }

    public void setDataLoader(DataLoader dataLoader) {
        this.dataLoader = dataLoader;
    }

}
