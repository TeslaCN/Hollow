package ltd.scau.utils.pe;

import com.google.gson.JsonSyntaxException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;
import java.util.concurrent.*;

public class Traverser implements ApplicationContextAware {

    private ApplicationContext context;

    private DataLoader dataLoader;

    private Map<String, String> pages;

    private BlockingQueue<String> queue = new LinkedBlockingQueue<>();

    private ExecutorService executorService = Executors.newFixedThreadPool(64);

    public void execute() throws Exception {
        Log log = LogFactory.getLog(Traverser.class);
        Map<String, String> missions = getPages();
        log.info("Initializing queue");
        for (String key : missions.keySet()) {
            for (int i = 0; i < Integer.parseInt(missions.get(key)); i++) {
                queue.put(i + "-" + key);// params:"page-examId"
            }
        }
        log.info("Queue initialized!");
        log.info("Starting traverser...");
        int processorsCount = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < 64; i++) {
            executorService.execute(new Thread(new Loader(this.context)));
        }
        log.info("Traverser Started!");
        executorService.shutdown();
        log.info("Traverser executorService shutdown");
    }

    private class Loader implements Runnable {

        private ApplicationContext context;

        private DataLoader dataLoader;

        Loader(ApplicationContext ctx) {
            this.context = ctx;
        }

        @Override
        public void run() {
            dataLoader = (DataLoader) this.context.getBean("dataLoader");
            Log log = LogFactory.getLog(Traverser.class);
            for (; ; ) {
                if (queue.isEmpty()) {
                    log.info(">>>> Queue Empty");
                    break;
                }
                String param = null;
                String[] params = null;
                boolean result = false;
                try {
                    param = queue.poll(30, TimeUnit.SECONDS);
                    params = param.split("-");
                    result = dataLoader.execute(params[0], params[1]);
                    if (!result) {
                        queue.put(param);
                        log.info(String.format("==> Append: {page: %s, examId: %s, remain: %d}", params[0], params[1], queue.size()));
                    } else {
                        log.info(String.format("Succeed: {page: %s, examId: %s, result: %s, remain: %d}", params[0], params[1], result, queue.size()));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    try {
                        queue.put(param);
                        log.info(String.format("==> Append: {page: %s, examId: %s, remain: %d}", params[0], params[1], queue.size()));
                    } catch (InterruptedException ex) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    public DataLoader getDataLoader() {
        return dataLoader;
    }

    public void setDataLoader(DataLoader dataLoader) {
        this.dataLoader = dataLoader;
    }

    public Map<String, String> getPages() {
        return pages;
    }

    public void setPages(Map<String, String> pages) {
        this.pages = pages;
    }
}
