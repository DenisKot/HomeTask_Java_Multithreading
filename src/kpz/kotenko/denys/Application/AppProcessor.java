package kpz.kotenko.denys.Application;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by the_m on 10.12.2016.
 */
public class AppProcessor extends Thread  {

    private List<TesaurusDataModel> tesaurus;

    private DataProvider dataProvider;
    private DataConsumer dataConsumer;
    private ExecutorService threadPool;
    private int threadsNum = 5;

    public AppProcessor(){
        this.tesaurus = new ArrayList<TesaurusDataModel>();
        this.dataProvider = new DataProvider(this.tesaurus);
        this.dataConsumer = new DataConsumer(this.tesaurus);

        this.threadPool = Executors.newFixedThreadPool(this.threadsNum);
    }


    @Override
    public void run() {
        this.startApp();
    }

    private void startApp() {
        System.out.println("App Started!");

        dataProvider.start();

        do {
            try {
                Thread.sleep(180);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.threadPool.execute(this.dataConsumer);
        } while (dataProvider.isAlive());

        System.out.println("App Finished!");
    }
}
