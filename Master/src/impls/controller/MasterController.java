package impls.controller;

import impls.monitor.Timers;
import impls.remote.MemoryCache;
import interfaces.Controller;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by douchengfeng on 2017/6/2.
 * 控制器
 */
public class MasterController {
    private static Timers timers =  new Timers();
    private HashMap<String, Controller> controllerHashMap = new HashMap<>();

    private void addTimers(String name){
        timers.addTimeTask(name);
    }

    public static void resetTimers(String name){
        timers.resetTimeTask(name);
    }

    public void restart(String name){
       Controller controller = controllerHashMap.get(name);
       if(controller != null){
           controller.restart();
       }
    }

    public void clear(){
        controllerHashMap.clear();
    }

    public void closeAll(){
        for(Controller controller: controllerHashMap.values()){
            try {
                controller.stop();
            } catch (RemoteException e) {
                System.out.println(">>>>>INFO:与一些爬虫失去联系，有些爬虫可能未终止！");
                e.printStackTrace();
            }
        }
        MemoryCache memoryCache = MemoryCache.getInstance();
        memoryCache.save();
        timers.closeAll();
        System.out.println(">>>>>INFO:爬虫已全部关闭！");
    }

    public void beginNewMission(List<String> urls, String name, String ip){
        try {
            Controller crawler = (Controller) Naming.lookup("rmi://"+ip+"/Controller");
            controllerHashMap.put(name, crawler);
            crawler.setName(name);
            this.addTimers(name);
            crawler.storeUrl(urls);
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            System.out.println(">>>>>INFO:任务建立失败，请检查网络连接！");
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println(">>>>>INFO:爬虫忙!");
            e.printStackTrace();
        }
    }


}
