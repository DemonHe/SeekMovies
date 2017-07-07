package dao.taoppEntity.taoppPK;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created by Administrator on 2017/7/7.
 */
@Embeddable
public class MovieActorPK {
    @Column(name = "movieId")
    private int movieId;
    @Column(name = "actorName")
    private String actorName;


    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorId) {
        this.actorName = actorName;
    }

    @Override
    public String toString() {
        return "MovieActorPK{" +
                "movieId=" + movieId +
                ", actorName=" + actorName +
                '}';
    }
}
