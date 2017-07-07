package dao.taoppEntity;
import tools.DateUtil;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Created by Administrator on 2017/7/7.
 */
@Entity
@Table(name = "taopp_movie")
public class Movie implements Serializable{
    @Id
    private int movieId;
    @Column(name = "movieName")
    private String movieName;
    @Column(name = "region")
    private String region;
    @Column(name = "language")
    private String language;
    @Column(name = "releaseDate")
    private Date releaseDate;
    @Column(name = "during")
    private double during;
    @Column(name = "poster")
    private String poster;
    @Column(name = "score")
    private double score;
    @Column(name = "introduction")
    private String introduction;


    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        Pattern pattern = Pattern.compile("制片国家/地区：[\\u4e00-\\u9fa5]*");
        Matcher matcher = pattern.matcher(region);
        if(matcher.find()){
            this.region = matcher.group().split(" ")[1];
        }
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        Pattern pattern = Pattern.compile("语言：[\\u4e00-\\u9fa5]*");
        Matcher matcher = pattern.matcher(language);
        if(matcher.find()){
            this.language = matcher.group().split(" ")[1];
        }
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public double getDuring() {
        return during;
    }

    public void setDuring(double during) {
        this.during = during;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }




}
