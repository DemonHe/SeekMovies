package dao.taoppEntity.taoppPK;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class MovieTagPK implements Serializable{
    @Column(name = "movieId")
    private int movieId;
    @Column(name = "tag")
    private String tag;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
