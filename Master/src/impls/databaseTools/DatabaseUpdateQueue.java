package impls.databaseTools;


import interfaces.DatabaseUpdateOperation;


import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by douchengfeng on 2017/5/30.
 * 用于将url保存到url仓库里面
 */
public class DatabaseUpdateQueue {
    private BlockingQueue<DatabaseUpdateOperation> urlQueue = new ArrayBlockingQueue<>(20);
    private Thread thread = new Thread(new SaveMission());
    private boolean stop = false;

    private DatabaseUpdateQueue(){
        thread.start();
    }

    public static DatabaseUpdateQueue getInstance(){
        return DatabaseHolder.instance;
    }

    public void saveOperation(DatabaseUpdateOperation operation) throws InterruptedException {
        urlQueue.put(operation);
    }


    private static class DatabaseHolder{
        static final DatabaseUpdateQueue instance = new DatabaseUpdateQueue();
    }


    public void stop(){
        stop = true;
        thread.interrupt();
    }

    private class SaveMission implements Runnable{

        @Override
        public void run() {
            try {
                while(!stop){
                    DatabaseUpdateOperation operation = urlQueue.take();
                    operation.execute();
                }
            } catch (InterruptedException | SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
