package dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by douchengfeng on 2017/6/10.
 * 即将上映的电影
 */
@Entity
@Table(name = "coming_movie")
public class ComingMovie implements Serializable{
    @Id
    private String movie;
    @Column(name = "img")
    private String img;
    @Column(name = "time")
    private Date time = new Date();

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "ComingMovie{" +
                "movie='" + movie + '\'' +
                ", img='" + img + '\'' +
                ", time=" + time +
                '}';
    }
}
