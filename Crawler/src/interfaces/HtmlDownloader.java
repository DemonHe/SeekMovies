package interfaces;

import java.io.IOException;

/**
 * Created by douchengfeng on 2017/5/30.
 * 用于下载html页面
 */
public interface HtmlDownloader {

    /**
     * 用于下载html页面
     * @param url 页面的url
     * @return html页面，以字符串的形式
     */
    String getHtmlPage(String url) throws IOException;


    /**
     * 用于结束下载器
     */
    void closeDownloader();
}
