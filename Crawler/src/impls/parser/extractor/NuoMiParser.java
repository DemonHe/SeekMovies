package impls.parser.extractor;

import org.jsoup.nodes.Element;

/**
 * Created by douchengfeng on 2017/6/9.
 * 用于解析百度糯米链接的解析器
 */
public class NuoMiParser implements LinkParser {

    @Override
    public String parse(Element element) {
        //System.out.println("parser class!");
        return "movieId=" + element.attr("data-data").split(":")[1].replace('}',' ');
    }
}
