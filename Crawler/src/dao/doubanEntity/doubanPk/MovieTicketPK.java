package dao.doubanEntity.doubanPk;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by douchengfeng on 2017/6/18.
 *
 */
@Embeddable
public class MovieTicketPK implements Serializable{
    @Column(name = "movieId")
    private int movieId;
    @Column(name = "cinemaId")
    private int cinemaId;
    @Column(name = "time", columnDefinition ="datetime")
    private Date time;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
