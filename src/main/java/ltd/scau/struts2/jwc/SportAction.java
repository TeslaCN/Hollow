package ltd.scau.struts2.jwc;

import com.opensymphony.xwork2.ActionSupport;
import ltd.scau.aspect.annotations.Administrator;
import ltd.scau.utils.pe.DataLoader;
import ltd.scau.utils.pe.Traverser;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;

public class SportAction extends ActionSupport {

    private String page;

    private String examId;

    private DataLoader dataLoader;

    private Traverser traverser;

    @Override
    public String execute() throws Exception {
        getDataLoader().parseJson(getDataLoader().getJson(getPage(), getExamId()), getExamId());
        return NONE;
    }

    @Action(value = "traverse")
    @Administrator
    public String traverse() throws Exception {
        getTraverser().execute();
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

    public Traverser getTraverser() {
        return traverser;
    }

    public void setTraverser(Traverser traverser) {
        this.traverser = traverser;
    }
}
