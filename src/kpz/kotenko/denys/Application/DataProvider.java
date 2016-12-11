package kpz.kotenko.denys.Application;

import com.sun.istack.internal.NotNull;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Постачальник
 */
public class DataProvider extends Thread {

    private final String fileName = "tesaurus.txt";

    private List<TesaurusDataModel> tesaurus;

    public DataProvider(@NotNull List<TesaurusDataModel> tesaurus){
        this.tesaurus = tesaurus;
    }

    @Override
    public void run() {
        ArrayList<String> lines = this.load();

        for (String line : lines) {
            if(!line.contains("–") && !line.contains("-"))
                continue;

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (this.tesaurus){
                int index = line.indexOf('–');
                if(index < 0)
                    index = line.indexOf('-');

                String term = line.substring(0, index - 1).trim();
                String definition = line.substring(index + 1).trim();

                TesaurusDataModel model = new TesaurusDataModel(term, definition, null);
                tesaurus.add(model);

                System.out.println("Added new term: " + model);
            }
        }
    }

    private ArrayList<String> load(){
        ArrayList<String> arr = new ArrayList<String>();

        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream is = classloader.getResourceAsStream(this.fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String str;
            while ((str = br.readLine()) != null){
                arr.add(str);
            }

            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return arr;
    }
}
