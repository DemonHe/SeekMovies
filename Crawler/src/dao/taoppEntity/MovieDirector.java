package dao.taoppEntity;

import dao.taoppEntity.taoppPK.MovieDirectorPK;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/7.
 */
@Entity
@Table(name = "taopp_movie_director")
@IdClass(MovieDirectorPK.class)
public class MovieDirector implements Serializable {
    @Id
    private int movieId;

    @Id
    @Column(name = "directorName")
    private String directorName;

    public MovieDirector(){

    }

    public MovieDirector(int movieId, String directorName) {
        this.movieId = movieId;
        this.directorName = directorName;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
//        String[] strings = movieId.split("&");
//        movieId = strings[1];
//        movieId = movieId.split("=")[1];
//        this.movieId = Integer.parseInt(movieId);
        this.movieId = movieId;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    @Override
    public String toString() {
        return "MovieDirector{" +
                "movieId=" + movieId +
                ", directorName='" + directorName + '\'' +
                '}';
    }
}
