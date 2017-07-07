package dao.doubanEntity;

import tools.DateUtil;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by douchengfeng on 2017/6/16.
 * 豆瓣电影上的movie实体
 */
@Entity
@Table(name = "douban_movie")
public class Movie implements Serializable {
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
    @Column(name = "summary")
    private String summary;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        movieId = movieId.replace("https://movie.douban.com/subject/", "");
        movieId = movieId.replace("/photos?type=R","");
        this.movieId = Integer.parseInt(movieId);
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName.split(" ")[0];
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        //System.out.println(region);
        Pattern pattern = Pattern.compile("制片国家/地区: [\\u4e00-\\u9fa5]*");
        Matcher matcher = pattern.matcher(region);
        if(matcher.find()){
            this.region = matcher.group().split(" ")[1];
        }
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        Pattern pattern = Pattern.compile("语言: [\\u4e00-\\u9fa5]*");
        Matcher matcher = pattern.matcher(language);
        if(matcher.find()){
            this.language = matcher.group().split(" ")[1];
        }

    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        releaseDate = releaseDate.replace("(中国大陆)", "");
        this.releaseDate = DateUtil.formatDate(releaseDate, "YYYY-MM-DD");
    }

    public double getDuring() {
        return during;
    }

    public void setDuring(String during) {
        this.during = Double.parseDouble(during);
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public double getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = Double.parseDouble(score);
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", movieName='" + movieName + '\'' +
                ", region='" + region + '\'' +
                ", language='" + language + '\'' +
                ", releaseDate=" + releaseDate +
                ", during=" + during +
                ", poster='" + poster + '\'' +
                ", score=" + score +
                ", summary='" + summary + '\'' +
                '}';
    }
}
