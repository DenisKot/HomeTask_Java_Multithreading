package kpz.kotenko.denys.Application;

import com.sun.istack.internal.NotNull;

import java.util.List;

/**
 * Created by the_m on 10.12.2016.
 */
public class DataConsumer implements Runnable {

    private List<TesaurusDataModel> tesaurus;

    public DataConsumer(@NotNull List<TesaurusDataModel> tesaurus){
        this.tesaurus = tesaurus;
    }

    @Override
    public void run() {
        synchronized (this.tesaurus) {
            System.out.println("////////////////");
            System.out.println("Research started");

            for (int i = 0; i < tesaurus.size(); i++){
                for (int j = i + 1; j < tesaurus.size(); j++){
                    if(tesaurus.get(i).equals(tesaurus.get(j))){
                        System.out.println("REMOVE: " + tesaurus.get(j));
                        tesaurus.remove(j);
                        j--;
                    }
                }
            }

            System.out.println("Research finished");
            System.out.println("/////////////////");
        }
    }
}
