package dao;

import dao.primaryKey.PriceDetailPK;


import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by douchengfeng on 2017/6/11.
 * 详细的票价
 */
@Entity
@Table(name = "price_detail")
@IdClass(PriceDetailPK.class)
public class PriceDetail {
    @Id
    private String id;
    @Id
    private String movieName;
    @Id
    private Date time;
    @Column(name = "orderLink")
    private String orderLink;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName.split(" ")[0];;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(String time) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String timePick[] = time.split(":");
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timePick[0]));
        calendar.set(Calendar.SECOND, Integer.parseInt(timePick[1]));
        this.time = calendar.getTime();
    }


    public void setTime(Date time) {
        this.time = time;
    }

    public String getOrderLink() {
        return orderLink;
    }

    public void setOrderLink(String orderLink) {
        this.orderLink = orderLink;
    }

    @Override
    public String toString() {
        return "PriceDetail{" +
                "id='" + id + '\'' +
                ", movieName='" + movieName + '\'' +
                ", time=" + time +
                ", orderLink='" + orderLink + '\'' +
                '}';
    }
}
