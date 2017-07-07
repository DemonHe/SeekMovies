package dao.doubanEntity.doubanPk;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by douchengfeng on 2017/6/16.
 *
 */
@Embeddable
public class MovieActorPK implements Serializable{
    @Column(name = "movieId")
    private int movieId;
    @Column(name = "actorId")
    private int actorId;


    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getActorId() {
        return actorId;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
    }

    @Override
    public String toString() {
        return "MovieActorPK{" +
                "movieId=" + movieId +
                ", actorId=" + actorId +
                '}';
    }
}
