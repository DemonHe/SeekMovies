package dao.doubanEntity;


import dao.doubanEntity.doubanPk.MovieActorPK;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by douchengfeng on 2017/6/16.
 *
 */
@Entity
@Table(name = "douban_movie_actor")
@IdClass(MovieActorPK.class)
public class MovieActor implements Serializable {
    @Id
    private int movieId;
    @Id
    private int actorId;
    @Column(name = "actorName")
    private String actorName;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        movieId = movieId.replaceAll("(https://movie.douban.com)?/subject/","");
        movieId = movieId.replace("/photos?type=R","");
        this.movieId = Integer.parseInt(movieId);
    }

    public int getActorId() {
        return actorId;
    }

    public void setActorId(String actorId) {
        actorId = actorId.replaceAll("(https://movie.douban.com)?/celebrity/","");
        actorId = actorId.replace("/","");
        this.actorId = Integer.parseInt(actorId);
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    @Override
    public String toString() {
        return "MovieActor{" +
                "movieId=" + movieId +
                ", actorId=" + actorId +
                ", actorName='" + actorName + '\'' +
                '}';
    }
}
