package test;

import impls.parser.ParserCore;
import org.dom4j.DocumentException;
import org.junit.Ignore;
import org.junit.Test;

import java.io.*;

import static junit.framework.TestCase.*;

/**
 * Created by douchengfeng on 2017/6/1.
 * htmlMapping的测试用例
 */
public class TestHtmlMapping {

    @Test
    @Ignore
    public void testBuild(){
        try {
            ParserCore mapping = new ParserCore("src/templates/taoppTemplates/taopp_now_playing.xml");
            mapping.printParseList();
        } catch (DocumentException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    //@Ignore
    public void testParseMulti(){
        try {
            ParserCore mapping = new ParserCore("src/templates/douban-ticket.xml");
            mapping.printParseList();
            File input = new File("C:/Users/douchengfeng/Desktop/新木乃伊 在线购票&影讯.htm");
            BufferedReader reader = new BufferedReader(new FileReader(input));
            StringBuilder html = new StringBuilder();
            String temp;
            while((temp = reader.readLine()) != null){
                html.append(temp);
            }
            mapping.parse(html.toString());
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testParseSingle(){
        try {
            ParserCore mapping = new ParserCore("src/templates/taoppTemplates/taopp_now_playing.xml");
            mapping.printParseList();
            File input = new File("D:\\Homework\\Junior\\应用集成原理与工具\\神偷奶爸3 - 南京影讯、 影片简介、电影票网上预订、在线购票、兑换券、团购 - 淘票票.html");
            BufferedReader reader = new BufferedReader(new FileReader(input));
            StringBuilder html = new StringBuilder();
            String temp;
            while((temp = reader.readLine()) != null){
                html.append(temp);
            }
            mapping.parse(html.toString());
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
            fail();
        }
    }
}
