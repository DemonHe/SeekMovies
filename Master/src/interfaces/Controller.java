package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by douchengfeng on 2017/5/30.
 * Controller 的接口，供praser中的类调用
 */
public interface Controller extends Remote {

    void setName(String name) throws RemoteException;

    /**
     * 用于将解析出来的url保存到待爬取队列中
     * @param urls 解析出来的url
     */
    void storeUrl(List<String> urls) throws InterruptedException, RemoteException;

    /**
     * 用于停止这个爬虫
     */
    void stop() throws RemoteException;

    /**
     * 用于重新启动
     */
    void restart();
}
