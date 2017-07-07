package impls.monitor;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by douchengfeng on 2017/6/2.
 * 计时器类
 */
public class Timers {
    private volatile ConcurrentHashMap<String, Timer> timerMap = new ConcurrentHashMap<>();


    public void addTimeTask(String name){
        TimerTask timerTask = new MonitorMission(name);
        Timer timer = new Timer();
        timerMap.put(name, timer);
        timer.schedule(timerTask, 30000);
    }



    public boolean resetTimeTask(String name){
        Timer timer = timerMap.get(name);
        if(timer == null){
            System.out.println(">>>>>INFO:没有这样的timer");
            return false;
        }
        timer.cancel();
        timer.schedule(new MonitorMission(name), 30000);
        System.out.printf(">>>>>INFO:%s 重置成功\n", name);
        return true;
    }

    public void closeAll(){
        for(Timer timer: timerMap.values()){
            timer.cancel();
        }
        timerMap.clear();
    }

    private class MonitorMission extends TimerTask {
        String name;
        boolean echo = true;

        MonitorMission(String name){
            this.name =  name;
        }

        @Override
        public void run() {
            if(!echo){
                System.out.printf(">>>>>INFO:%s 挂掉了\n", name);
            }

        }
    }

}
