package impls.parser.extractor;

import org.jsoup.nodes.Element;

/**
 * Created by douchengfeng on 2017/6/11.
 * 豆瓣网的链接解析器
 */
public class DouBanLinkParser implements LinkParser{
    @Override
    public String parse(Element element) {
        return element.attr("data-subject-id") + "/";
    }
}
