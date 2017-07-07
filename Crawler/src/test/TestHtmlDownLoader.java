package test;

import impls.downloader.HtmlDownLoaderImpl;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.*;

/**
 * Created by douchengfeng on 2017/5/31.
 * htmlDownloader的测试用例
 */
public class TestHtmlDownLoader {

    @Test
    public void testDownLoad(){
        HtmlDownLoaderImpl impl = new HtmlDownLoaderImpl();
        try {
            String result = impl.getHtmlPage("https://www.douban.com/");
            System.out.println(result);
            impl.closeDownloader();
        }catch (IOException e) {
            e.printStackTrace();
            fail();
        }

    }
}
