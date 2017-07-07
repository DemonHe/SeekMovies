package interfaces;

import java.util.List;

/**
 * Created by douchengfeng on 2017/5/30.
 * 保存待爬取的url，提供url去重服务
 */
public interface UrlCache {

    /**
     * 保存将要爬取的url到队列中，该方法线程安全
     * @param urls 将要爬取的url
     * @throws InterruptedException 当调用线程的Interrupt方法的时候，会抛出这个异常，我们通过这个异常来终止等待url
     */
    void saveUrl(List<String> urls) throws InterruptedException;


    /**
     * 返回一条url，这个url将被访问。如果没有url在队列中，那么这个方法将会导致调用的线程挂起
     * @return url
     * @throws InterruptedException 当调用线程的Interrupt方法的时候，会抛出这个异常，我们通过这个异常来终止等待url
     */
    String getUrl() throws InterruptedException;

    /**
     * 用于关闭urlCache
     */
    void closeCache();

    /**
     * 用于从新启动cache
     */
    void restart();
}
