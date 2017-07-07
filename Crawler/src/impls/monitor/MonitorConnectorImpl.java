package impls.monitor;

import interfaces.Monitor;
import interfaces.MonitorConnector;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;


/**
 * Created by douchengfeng on 2017/5/30.
 * monitor的连接器，使用rmi进行远程链接，之所以不使用EJB是因为EJB这个框架太笨重了
 */
public class MonitorConnectorImpl implements MonitorConnector {

    @Override
    public void heartBeat(String name) {
        Thread thread = new Thread(new MonitorMission(name));
        thread.start();
    }


    private class MonitorMission implements Runnable{
        private String name;
        MonitorMission(String name){
            this.name = name;
        }

        @Override
        public void run() {
            try {
                Monitor monitor = (Monitor) Naming.lookup("rmi://localhost:13000/Monitor");
                monitor.resetTimer(name);
                System.out.println("成功续30s！");
            } catch (NotBoundException | MalformedURLException | RemoteException e) {
                System.out.println("续命失败！请检查网络连接");
                e.printStackTrace();
            }

        }
    }
}
