package dao.doubanEntity;

import dao.doubanEntity.doubanPk.MovieTicketPK;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by douchengfeng on 2017/6/18.
 *
 */
@Entity
@Table(name = "douban_ticket")
@IdClass(MovieTicketPK.class)
public class MovieTicket implements Serializable{
    @Id
    private int movieId;
    @Id
    private int cinemaId;
    @Id
    private Date time;
    @Column(name = "price")
    private double price;
    @Column(name = "orderLink")
    private String orderLink;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = Integer.parseInt(movieId);
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(String cinemaId) {
        cinemaId = cinemaId.replace("https://site.douban.com/", "");
        cinemaId = cinemaId.replace("/", "");
        this.cinemaId = Integer.parseInt(cinemaId);
    }

    public Date getTime() {
        return time;
    }

    public void setTime(String time) {
        Calendar calendar = Calendar.getInstance();
        String timePick[] = time.split(":");
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timePick[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(timePick[1]));
        calendar.set(Calendar.SECOND, 0);
        this.time = calendar.getTime();
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(String price) {
        Pattern pattern = Pattern.compile("豆瓣售价[0-9]+元");
        Matcher matcher = pattern.matcher(price);
        if(matcher.find()){
            price = matcher.group();
            price = price.replace("豆瓣售价", "");
            price = price.replace("元", "");
            this.price = Double.parseDouble(price);
        }
    }

    public String getOrderLink() {
        return orderLink;
    }

    public void setOrderLink(String orderLink) {
        this.orderLink = orderLink;
    }

    @Override
    public String toString() {
        return "MovieTicket{" +
                "movieId=" + movieId +
                ", cinemaId=" + cinemaId +
                ", time=" + time +
                ", price=" + price +
                ", orderLink='" + orderLink + '\'' +
                '}';
    }
}
