package interfaces;

/**
 * Created by douchengfeng on 2017/5/30.
 * 连接监控器，报告自己没有死机
 */
public interface MonitorConnector {

    /**
     * 报上自己的名字，让监控器能够感受到你的存在
     * @param name 爬虫的名字
     */
    void heartBeat(String name);

}
