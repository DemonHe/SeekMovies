package impls.parser.mapping;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

/**
 * Created by douchengfeng on 2017/6/1.
 * 抽象解析器
 */
abstract class AbstractObjectParser implements ObjectParser{

    Element getElement(String tag, Element root){
        if(!tag.contains(":")){
            return root.select(tag).first();
        }
        String[] tmp = tag.split(" ");
        Element element = root;
        for (String aTmp : tmp) {
            if (!aTmp.contains(":")) {
                element = element.select(aTmp).first();
            } else {
                //System.out.println("==="+aTmp);
                String[] tmp2 = aTmp.split(":");
                Elements elements = element.select(tmp2[0]);

                //System.out.println(elements.size());
                element = elements.get(Integer.parseInt(tmp2[1]) - 1);
            }

        }
        return element;
    }

    List<Element> getElements(String tag, Element root){
        if(!tag.contains(":")){
            return root.select(tag);
        }
        String[] tmp = tag.split(" ");
        Elements elements = null;
        for(String aTmp : tmp){
            if(!aTmp.contains(":")){
                if(elements == null){
                    elements = root.select(aTmp);
                }else{
                    elements = elements.select(aTmp);
                }
            }else{
                String[] tmp2 = aTmp.split(":");
                Elements temps = new Elements();
                int num = Integer.parseInt(tmp2[1]) - 1;
                if(elements == null){
                    Elements tmp3 = root.select(tmp2[0]);
                    if(tmp3.size() > num){
                        Element elementNew = tmp3.get(num);
                        temps.add(elementNew);
                    }

                }else{
                    for(Element element: elements){
                        Elements elementsNew = element.select(tmp2[0]);
                        if(elementsNew != null && elementsNew.size() > num){
                            temps.add(elementsNew.get(num));
                        }
                    }

                }
                elements = temps;


            }

        }
        return elements;
    }
}
