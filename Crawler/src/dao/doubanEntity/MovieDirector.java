package dao.doubanEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by douchengfeng on 2017/6/16.
 *
 */
@Entity
@Table(name = "douban_movie_director")
public class MovieDirector implements Serializable {
    @Id
    private int movieId;
    @Id
    private int directorId;
    @Column(name = "directorName")
    private String directorName;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        movieId = movieId.replaceAll("(https://movie.douban.com)?/subject/","");
        movieId = movieId.replace("/photos?type=R","");
        this.movieId = Integer.parseInt(movieId);
    }

    public int getDirectorId() {
        return directorId;
    }

    public void setDirectorId(String directorId) {
        directorId = directorId.replaceAll("(https://movie.douban.com)?/celebrity/","");
        directorId = directorId.replace("/","");
        this.directorId = Integer.parseInt(directorId);
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
                ", directorId=" + directorId +
                ", directorName='" + directorName + '\'' +
                '}';
    }
}
