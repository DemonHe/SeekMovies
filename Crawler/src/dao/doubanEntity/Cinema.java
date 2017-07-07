package dao.doubanEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by douchengfeng on 2017/6/18.
 *
 */
@Entity
@Table(name = "douban_cinema")
public class Cinema implements Serializable{
    @Id
    private int cinemaId;
    @Column(name = "cinemaName")
    private String cinemaName;
    @Column(name = "route")
    private String route;
    @Column(name = "address")
    private String address;
    @Column(name = "web")
    private String web;


    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(String cinemaId) {
        cinemaId = cinemaId.replace("https://site.douban.com/", "");
        cinemaId = cinemaId.replace("/", "");
        this.cinemaId = Integer.parseInt(cinemaId);
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        Pattern pattern = Pattern.compile("乘车路线：([\\u4e00-\\u9fa5]|、|[0-9]|，|.|[a-zA-Z]|：)*");
        Matcher matcher = pattern.matcher(route);
        if(matcher.find()){
            route = matcher.group();
            this.route = route.replace("乘车路线：", "");
        }

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        Pattern pattern = Pattern.compile("地址：([\\u4e00-\\u9fa5]|、|[0-9]|,)*");
        Matcher matcher = pattern.matcher(address);
        if(matcher.find()){
            this.address = matcher.group().replace("地址：", "");
        }
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }


    @Override
    public String toString() {
        return "Cinema{" +
                "cinemaId=" + cinemaId +
                ", cinemaName='" + cinemaName + '\'' +
                ", route='" + route + '\'' +
                ", address='" + address + '\'' +
                ", web='" + web + '\'' +
                '}';
    }
}
