package test;

import impls.downloader.HtmlDownLoaderImpl;
import impls.parser.HtmlParserImpl;
import org.dom4j.DocumentException;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by run-jiao on 2017/6/8.
 * 提取链接的测试用例
 */
public class TestGetLink {
    @Test
    public void testLink(){
        HtmlDownLoaderImpl impl = new HtmlDownLoaderImpl();
        try {
            String url = "https://dianying.taobao.com/showList.htm";
            HtmlParserImpl impl2 = new HtmlParserImpl();
            String page = impl.getHtmlPage(url);
            impl2.parseHtml(page,url);
            Thread.sleep(3000);
        } catch (DocumentException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
