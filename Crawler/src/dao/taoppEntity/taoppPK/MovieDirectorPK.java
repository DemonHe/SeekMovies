package dao.taoppEntity.taoppPK;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;


@Embeddable
public class MovieDirectorPK implements Serializable {
    @Column(name = "movieId")
    private int movieId;
    @Column(name = "directorName")
    private String directorName;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
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
        return "MovieDirectorPK{" +
                "movieId=" + movieId +
                ", directorName=" + directorName +
                '}';
    }
}
