package dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by douchengfeng on 2017/6/10.
 * 电影的实体类
 */
@Entity
@Table(name = "movie")
public class Movie implements Serializable{
    @Id
    private String movie;
    @Column(name = "region")
    private String region;
    @Column(name = "introduce")
    private String introduce;
    @Column(name = "releaseTime")
    private Date releaseTime;
    @Column(name = "during")
    private String during;
    @Column(name = "score")
    private int score;
    @Column(name = "img")
    private String img;

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region.split(" ")[0];
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        //System.out.println(releaseTime);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-mm-dd");
        releaseTime = releaseTime.split(" ")[3].replace("    ","");
        //System.out.println(releaseTime);
        try {
            this.releaseTime = simpleDateFormat.parse(releaseTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getDuring() {
        return during;
    }

    public void setDuring(String during) {

        this.during = during.split(" ")[2];
    }

    public int getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = (int) (Double.parseDouble(score) * 10);
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movie='" + movie + '\'' +
                ", region='" + region + '\'' +
                ", introduce='" + introduce + '\'' +
                ", releaseTime=" + releaseTime +
                ", during='" + during + '\'' +
                ", score=" + score +
                ", img='" + img + '\'' +
                '}';
    }
}
