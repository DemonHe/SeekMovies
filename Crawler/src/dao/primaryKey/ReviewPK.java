package dao.primaryKey;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by run-jiao on 2017/6/11.
 *
 */
@Embeddable
public class ReviewPK implements Serializable{
    @Column(name = "movie")
    private String movie;
    @Column(name = "time", columnDefinition ="datetime")
    private Date time;

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
