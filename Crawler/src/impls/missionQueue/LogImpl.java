package impls.missionQueue;

import interfaces.Log;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by douchengfeng on 2017/5/30.
 * 日志记录功能
 */
public class LogImpl implements Log{
    private File inQueueFile = new File("src/data/InQueue.txt");
    private File outQueueFile = new File("src/data/OutQueue.txt");

    @Override
    public void storeUrls(List<String> inQueue, List<String> outQueue) throws IOException {
        System.out.println("正在保存日志...");
        writeUrls(inQueueFile, inQueue);
        writeUrls(outQueueFile, outQueue);
        System.out.println("日志保存结束！");
    }

    private void writeUrls(File file, List<String> urls) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file,false));
        for(String url: urls){
            writer.append(url);
            writer.newLine();
        }
        writer.flush();
        writer.close();
    }

    @Override
    public List<String> loadInQueue() throws IOException {
        return readFile(inQueueFile);
    }

    @Override
    public List<String> loadOutQueue() throws IOException {
        return readFile(outQueueFile);
    }

    private List<String> readFile(File file) throws IOException {
        List<String> urls = new LinkedList<>();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String url;
        while((url = reader.readLine()) != null){
            urls.add(url);
        }
        return urls;
    }
}
