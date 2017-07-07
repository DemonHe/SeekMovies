package interfaces;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Created by douchengfeng on 2017/5/30.
 * 日志记录或读取模块
 */
public interface Log {

    /**
     * 用于记录urlCache中的两个队列的内容
     * @param inQueue inQueue中的内容
     * @param outQueue outQueue中的内容
     */
    void storeUrls(List<String> inQueue, List<String> outQueue) throws IOException;

    /**
     *
     * @return 日志中InQueue中的内容
     */
    List<String> loadInQueue() throws IOException;

    /**
     *
     * @return 日志中OutQueue中的内容
     */
    List<String> loadOutQueue() throws IOException;

}
