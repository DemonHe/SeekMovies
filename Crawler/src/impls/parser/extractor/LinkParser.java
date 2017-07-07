package impls.parser.extractor;

import org.jsoup.nodes.Element;

/**
 * Created by douchengfeng on 2017/6/9.
 * 链接解析器，用于解析比较特殊的链接，需要定制
 */
public interface LinkParser {

    String parse(Element element);
}
