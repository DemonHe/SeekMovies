package impls.controller;

import impls.downloader.HtmlDownLoaderImpl;
import impls.missionQueue.UrlCacheImpl;
import impls.parser.HtmlParserImpl;
import interfaces.Controller;
import interfaces.HtmlDownloader;
import interfaces.HtmlParser;
import interfaces.UrlCache;
import org.dom4j.DocumentException;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 * Created by douchengfeng on 2017/5/30.
 * 爬虫的控制器
 */
public class ControllerImpl extends UnicastRemoteObject implements Controller{
    //private String name;
    private UrlCache urlCache = new UrlCacheImpl();
    private HtmlDownloader downloader = new HtmlDownLoaderImpl();
    private HtmlParser parser;
    //private MonitorConnector monitor = new MonitorConnectorImpl();
    private volatile boolean stop = false;
    private Thread controllerThread = new Thread(new ControllerMission());

    public ControllerImpl() throws RemoteException {
        super();
        try {
            parser = new HtmlParserImpl();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        controllerThread.start();
    }

    @Override
    public void setName(String name) throws RemoteException{
        //this.name = name;
    }

    @Override
    public void storeUrl(List<String> urls) throws InterruptedException, RemoteException {
        urlCache.saveUrl(urls);
    }

    @Override
    public void stop() throws RemoteException{
        stop = true;
        controllerThread.interrupt();
    }

    @Override
    public void restart() throws RemoteException{
        try {
            controllerThread.wait();
            urlCache.restart();
            controllerThread.notify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class ControllerMission implements Runnable{

        @Override
        public void run() {
            try {
                //long lastReportTime = System.currentTimeMillis();
                while(!stop){
//                    /*
//                      每隔30s向监控器报告自己还活着
//                    */
//                    long currentTime = System.currentTimeMillis();
//                    if(currentTime - lastReportTime > 10000){
//                        System.out.println("正在向master报告工作情况");
//                        lastReportTime = currentTime;
//                        monitor.heartBeat(name);
//                    }

                    String url = urlCache.getUrl();
                    if(url != null){
                       // long startTime = System.currentTimeMillis();
                        String html = downloader.getHtmlPage(url);
                        //long endTime = System.currentTimeMillis();
                        try{
                            List<String> urls = parser.parseHtml(html, url);
                            if(urls != null){
                                urlCache.saveUrl(urls);
                            }
                            //System.out.println("urls get success!");


                            Thread.sleep(1000);   //控制ip访问频率为响应时间的10倍，减少ip被屏蔽的可能性
                        }catch (NullPointerException | IndexOutOfBoundsException e){
                            e.printStackTrace();
                        }


                    }
                }

            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            } finally {
                urlCache.closeCache();
                downloader.closeDownloader();
                parser.stopHtmlParser();
                //RMIPortRelease();
                System.out.println("控制器的任务结束！");
            }

        }
    }


}
