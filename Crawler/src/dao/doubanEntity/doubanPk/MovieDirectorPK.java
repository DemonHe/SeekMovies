package dao.doubanEntity.doubanPk;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by douchengfeng on 2017/6/16.
 *
 */
@Embeddable
public class MovieDirectorPK implements Serializable {
    @Column(name = "movieId")
    private int movieId;
    @Column(name = "directorId")
    private int directorId;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getDirectorId() {
        return directorId;
    }

    public void setDirectorId(int directorId) {
        this.directorId = directorId;
    }

    @Override
    public String toString() {
        return "MovieDirectorPK{" +
                "movieId=" + movieId +
                ", directorId=" + directorId +
                '}';
    }
}
