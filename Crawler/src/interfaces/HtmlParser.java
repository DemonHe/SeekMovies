package interfaces;

import java.util.List;

/**
 * Created by douchengfeng on 2017/5/30.
 * html页面的解析器
 */
public interface HtmlParser {

    /**
     * 用于解析html页面
     * @param html 字符串形式的html页面
     * @param url 访问的页面的url
     * @return 页面上的所有url链接
     */
    List<String> parseHtml(String html, String url);

    /**
     * 停止html解析器，注意如果解析队列里面还有内容没有解析，在停止前要把它解析完毕
     */
    void stopHtmlParser();
}
