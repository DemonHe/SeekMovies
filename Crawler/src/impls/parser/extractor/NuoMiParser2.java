package impls.parser.extractor;

import org.jsoup.nodes.Element;

/**
 * Created by douchengfeng on 2017/6/11.
 *
 */
public class NuoMiParser2 implements LinkParser {
    @Override
    public String parse(Element element) {
        return element.attr("data-url");
    }
}
