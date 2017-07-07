package dao;

import dao.primaryKey.RankPK;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by douchengfeng on 2017/6/10.
 * 每日排行榜
 */
@Entity
@Table(name = "rank")
@IdClass(RankPK.class)
public class Rank implements Serializable{
    @Id
    private String movie;
    @Id
    @GeneratedValue
    private Date time = new Date();
    @Column(name = "boxOffice")
    private double boxOffice;
    @Column(name = "img")
    private String img;


    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {

        this.movie = movie.split(" ")[0];
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public double getBoxOffice() {
        return boxOffice;
    }

    public void setBoxOffice(String boxOffice) {
        this.boxOffice = Double.parseDouble(boxOffice);
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Rank{" +
                "movie='" + movie + '\'' +
                ", time=" + time +
                ", boxOffice=" + boxOffice +
                ", img='" + img + '\'' +
                '}';
    }
}
