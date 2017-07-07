package dao.taoppEntity;
import javax.persistence.*;
import java.io.Serializable;
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
    private Set<MovirDirector> directorNames = new HashSet<>();

    public Movie(int movieId, String movieName, String region, String language, Date releaseDate, double during, String poster, double score, String introduction,
                 String actorNames, String tags, String directorNames) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.region = region;
        this.language = language;
        this.releaseDate = releaseDate;
        this.during = during;
        this.poster = poster;
        this.score = score;
        this.introduction = introduction;

        for(String a: actorNames.split(",")){
            addActor(new MovieActor(movieId,a));
        }

        for(String t: tags.split(",")){
            addTag(new MovieTag(movieId,t));
        }

        for(String d: directorNames.split(",")){
            addDirector(new MovirDirector(movieId,d));
        }

    }

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


    public Set<MovieActor> getActorNames() {
        return actorNames;
    }

    public void setActorNames(Set<MovieActor> actorNames) {
        this.actorNames = actorNames;
    }

    public void addActor(MovieActor actor){
        actorNames.add(actor);
    }

    public Set<MovieTag> getTags() {
        return tags;
    }

    public void setTags(Set<MovieTag> tags) {
        this.tags = tags;
    }

    public void addTag(MovieTag tag){
        tags.add(tag);
    }

    public Set<MovirDirector> getDirectorNames() {
        return directorNames;
    }

    public void setDirectorNames(Set<MovirDirector> directorNames) {
        this.directorNames = directorNames;
    }

    public void addDirector(MovirDirector directorName){
        directorNames.add(directorName);
    }
}
