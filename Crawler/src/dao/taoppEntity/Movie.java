package dao.taoppEntity;
import javax.persistence.*;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
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
    @OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinColumn(name="movieId")
    private Set<MovieActor> actorNames = new HashSet<>();
    @OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinColumn(name="movieId")
    private Set<MovieTag> tags = new HashSet<>();
    @OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinColumn(name="movieId")
    private Set<MovieDirector> directorNames = new HashSet<>();

    public Movie(){

    }


    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        String s = movieId.split("&")[0].split("=")[1];
        this.movieId = Integer.parseInt(s);
//        System.out.println(movieId);
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
            this.region = matcher.group().split("：")[1];
        }
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        Pattern pattern = Pattern.compile("语言：[\\u4e00-\\u9fa5]*");
        Matcher matcher = pattern.matcher(language);
        if(matcher.find()){
            this.language = matcher.group().split("：")[1];
        }
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        releaseDate = releaseDate.split("：")[1];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.releaseDate = sdf.parse(releaseDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
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

    public void setDuring(String during) {
        Pattern pattern = Pattern.compile("片长：[0-9]*分钟");
        Matcher matcher = pattern.matcher(language);
        if(matcher.find()){
            during = matcher.group().split("：")[1];
            during = during.replace("分钟","");
            this.during = Double.parseDouble(during);
        }
    }

    public double getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = Double.parseDouble(score);
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        Pattern pattern = Pattern.compile("剧情介绍：[\\u4e00-\\u9fa5]*");
        Matcher matcher = pattern.matcher(language);
        if(matcher.find()) {
            introduction = matcher.group().split("：")[1];
            this.introduction = introduction;
        }
    }


    public Set<MovieActor> getActorNames() {
        return actorNames;
    }

//    public void setActorNames(Set<MovieActor> actorNames) {
//        this.actorNames = actorNames;
//    }

    public void setActorNames(String actorNames) {
        Pattern pattern = Pattern.compile("主演：[\\u4e00-\\u9fa5]*");
        Matcher matcher = pattern.matcher(language);
        if(matcher.find()) {
            actorNames = matcher.group().split("：")[1];
            for (String a : actorNames.split(",")) {
                addActor(new MovieActor(movieId, a));
            }
        }
    }

    public void addActor(MovieActor actor){
        actorNames.add(actor);
    }

    public Set<MovieTag> getTags() {
        return tags;
    }

//    public void setTags(Set<MovieTag> tags) {
//        this.tags = tags;
//    }

    public void setTags(String tags) {
        Pattern pattern = Pattern.compile("类型：[\\u4e00-\\u9fa5]*");
        Matcher matcher = pattern.matcher(language);
        if(matcher.find()) {
            tags = matcher.group().split("：")[1];
            for (String t : tags.split(" ")) {
                addTag(new MovieTag(movieId, t));
            }
        }
    }

    public void addTag(MovieTag tag){
        tags.add(tag);
    }

    public Set<MovieDirector> getDirectorNames() {
        return directorNames;
    }

//    public void setDirectorNames(Set<MovieDirector> directorNames) {
//        this.directorNames = directorNames;
//    }

    public void setDirectorNames(String directorNames) {
        Pattern pattern = Pattern.compile("导演：[\\u4e00-\\u9fa5]*");
        Matcher matcher = pattern.matcher(language);
        if(matcher.find()) {
            directorNames = matcher.group().split("：")[1];
            for (String d : directorNames.split(",")) {
                addDirector(new MovieDirector(movieId, d));
            }
        }
    }

    public void addDirector(MovieDirector directorName){
        directorNames.add(directorName);
    }
}
