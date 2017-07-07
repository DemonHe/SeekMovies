package impls.parser.extractor;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by douchengfeng on 2017/6/1.
 * url提取器
 */
public class UrlExtractor {
    private LinkParser linkParser = null;
    private List<Location> locations = new ArrayList<>();
    private String preFix = null;
    public void add(String location, String type){
        locations.add(new Location(location, type));
    }

    public void setPreFix(String preFix) {
        this.preFix = preFix;
    }

    public void setLinkParser(String className){
        //System.out.println("set parser");
        try {
            Class<?> obj = Class.forName(className);
            linkParser = (LinkParser) obj.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public List<String> extract(String html){
        List<String> list = new LinkedList<>();
        Document doc = Jsoup.parseBodyFragment(html);
        preFix = this.preFix == null ? "": this.preFix;
        for(Location location: locations){
            if(location.type.equals("all")){
                List<Element> aHref = getElements(location.url, doc);
                for(Element element: aHref){
                    parseLink(list, element);
                }
            }else{
                Element aHref = getElement(location.url, doc);
                parseLink(list, aHref);
            }
            
        }

        for(String url: list){
            System.out.println(url);
        }
        return list;
    }

    private void parseLink(List<String> list, Element element){
        try{
            if(linkParser == null){
                list.add(preFix + element.attr("href"));
            }else{
                list.add(preFix + linkParser.parse(element));
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }

    }

    private List<Element> getElements(String tag, Element root){
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

    private Element getElement(String tag, Element root){
        if(!tag.contains(":")){
            return root.select(tag).first();
        }
        String[] tmp = tag.split(" ");
        Element element = root;
        for (String aTmp : tmp) {
            if (!aTmp.contains(":")) {
                element = element.select(aTmp).first();
            } else {
                String[] tmp2 = aTmp.split(":");
                Elements elements = element.select(tmp2[0]);
                element = elements.get(Integer.parseInt(tmp2[1]) - 1);
            }

        }
        return element;
    }
}

class Location{
    String url;
    String type;

    Location(String url, String type) {
        this.url = url;
        this.type = type;
    }
}
