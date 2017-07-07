package dao.doubanEntity;

import dao.primaryKey.ReleaseMovieKey;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by douchengfeng on 2017/6/1.
 * 正在上映的电影的entity
 */
@Entity
@Table(name = "release_movie")
public class ReleasingMovie implements Serializable{
    @Id
    private String movieName;
    @Column(name = "score", length = 10)
    private String score;
    @Column(name = "star", length = 10)
    private String star;
    @Column(name = "releaseTime", length = 100)
    private String releaseTime;
    @Column(name = "duration", length = 100)
    private String duration;
    @Column(name = "region", length = 150)
    private String region;
    @Column(name = "director", length = 150)
    private String director;
    @Column(name = "img")
    private String img;
    @Column(name = "time")
    private Date time = new Date();


    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        int x = movieName.indexOf(" ");
        if(x>0){
            movieName = movieName.substring(0,x);
        }
        this.movieName = movieName;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star.substring(star.length()-2,star.length());
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
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
        return "ReleasingMovie{" +
                "movieName='" + movieName + '\'' +
                ", score='" + score + '\'' +
                ", star='" + star + '\'' +
                ", releaseTime='" + releaseTime + '\'' +
                ", duration='" + duration + '\'' +
                ", region='" + region + '\'' +
                ", director='" + director + '\'' +
                ", img='" + img + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
