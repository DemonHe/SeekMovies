package impls.downloader;

import interfaces.HtmlDownloader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by douchengfeng on 2017/5/31.
 * html页面的下载器
 */
public class HtmlDownLoaderImpl implements HtmlDownloader{
    private Runtime runtime;

    public HtmlDownLoaderImpl(){
        runtime = Runtime.getRuntime();
    }

    @Override
    public String getHtmlPage(String url) throws IOException {
        String jsPath = "src/js/codes.js";
        Process p = runtime.exec("G:\\Program Files\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe " + jsPath + " " + url);
        InputStream is = p.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sbf = new StringBuilder();
        String tmp;
        while((tmp = br.readLine())!=null){
            sbf.append(tmp);
        }
        //System.out.println(sbf.toString());
        return sbf.toString();
    }

    @Override
    public void closeDownloader(){
        try {
            runtime.exec("phantomjs.exe src/js/close.js");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
