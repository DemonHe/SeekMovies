package impls.missionQueue;

import impls.database.urlDatabase.UrlRemoteConnectorImpl;
import interfaces.Log;
import interfaces.UrlCache;
import interfaces.UrlRemoteConnector;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


/**
 * Created by douchengfeng on 2017/5/30.
 * 实现接口中描述的功能，里面的线程会不停的从inQueue中提取出url，筛选后会放到outQueue中，这是一个生产者消费者问题.
 * 用于探索新的url
 */
public class UrlCacheImpl implements UrlCache {
    /**
     * 这个队列保存将要放入待爬取队列的url
     */
    private BlockingQueue<String> inQueue = new LinkedBlockingQueue<>(1000);
    /**
     * 这个队列保存将要提取的url
     */
    private BlockingQueue<String> outQueue = new LinkedBlockingQueue<>(1000);


    private Log log = new LogImpl();
    private Thread urlThread;
    private Thread logThread;
    private volatile boolean close = false;
    /**
     * 使用读写所来保证日志记录不会出问题，使用公平所来保证日志记录能够拿到锁而不被饿死
     */
    private ReadWriteLock rwl = new ReentrantReadWriteLock(true);

    /*
        同远程url仓库的连接器
     */
    private UrlRemoteConnector connector = new UrlRemoteConnectorImpl();

    public UrlCacheImpl(){
        this.urlThread = new Thread(new CacheMission());
        this.logThread = new Thread(new LogMission());
        this.urlThread.start();
        this.logThread.start();
    }



    @Override
    public void saveUrl(List<String> urls) throws InterruptedException {
        rwl.readLock().lock();
        for(String url: urls){
            inQueue.put(url);
        }
        rwl.readLock().unlock();
    }

    @Override
    public String getUrl() throws InterruptedException {
        rwl.readLock().lock();
        String url = outQueue.poll(3000, TimeUnit.MILLISECONDS);
        rwl.readLock().unlock();
        return url;
    }

    @Override
    public void closeCache() {
        //如果CacheMission和LogMission没被阻塞，那么这条语句可以结束它
        close = true;
        //如果CacheMission或LogMission被阻塞，那么这条语句可以结束它
        urlThread.interrupt();
        logThread.interrupt();
    }

    @Override
    public void restart() {
        rwl.writeLock().lock();
        inQueue.clear();
        outQueue.clear();
        try {
            inQueue.addAll(log.loadInQueue());
            outQueue.addAll(log.loadOutQueue());
        } catch (IOException e) {
            e.printStackTrace();
        }
        rwl.writeLock().unlock();
    }

    private boolean hasNotVisited(String url) throws RemoteException, NotBoundException, MalformedURLException {

        return connector.hasNotVisited(url);
    }


    private class CacheMission implements Runnable{
        private final int TIME_OUT = 1000;


        @Override
        public void run() {
            try {
                while(!close){
                    rwl.readLock().lock();
                    /*
                      使用定时锁。如果不使用当队列中没有元素的时候，该线程一致占有readLock，此时LogThread会一直等待，导致死锁
                     */
                    String url = inQueue.poll(TIME_OUT, TimeUnit.MILLISECONDS);
                    if(url != null){
                        if(hasNotVisited(url)){
                            outQueue.put(url);
                        }
                    }
                    rwl.readLock().unlock();
                }


            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (RemoteException|NotBoundException|MalformedURLException e) {
                System.out.println("与url仓库失去链接！");
                e.printStackTrace();
            } finally {
                System.out.println("cache 关闭!");
            }
        }
    }


    private class LogMission implements Runnable{
        //private Log log = new LogImpl();
        private int LOG_GAP = 30000;

        @Override
        public void run() {
            try {
                while(!close){

                    Thread.sleep(LOG_GAP);    //任务30s后继续
                    rwl.writeLock().lock();
                    System.out.println("url cache 正在进行日志记录...");
                    List<String> inQueueClone = new LinkedList<>();
                    List<String> outQueueClone = new LinkedList<>();

                    inQueueClone.addAll(inQueue);
                    outQueueClone.addAll(outQueue);

                    rwl.writeLock().unlock();

                    log.storeUrls(inQueueClone, outQueueClone);
                    System.out.println("url cache 日志记录结束！");
                }

            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            } finally {
                System.out.println("日志记录关闭！");
            }

        }
    }

}


