package dao.taoppEntity;

import dao.taoppEntity.taoppPK.MovieActorPK;
import javax.persistence.*;
import java.io.Serializable;
/**
 * Created by Administrator on 2017/7/7.
 */
@Entity
@Table(name = "taopp_movie_actor")
@IdClass(MovieActorPK.class)
public class MovieActor implements Serializable{
    @Id
    private int movieId;

    @Id
    @Column(name = "actorName")
    private String actorName;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        String[] strings = movieId.split("&");
        movieId = strings[1];
        movieId = movieId.split("=")[1];
        this.movieId = Integer.parseInt(movieId);
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
                ", actorName='" + actorName + '\'' +
                '}';
    }
}
