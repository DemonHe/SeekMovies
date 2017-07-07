package dao.primaryKey;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by douchengfeng on 2017/6/11.
 *
 */
@Embeddable
public class PriceDetailPK implements Serializable {
    @Column(name = "id", length = 50)
    private String id;
    @Column(name = "movieName", length = 100)
    private String movieName;
    @Column(name = "time", columnDefinition ="datetime")
    private Date time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "PriceDetailPK{" +
                "id='" + id + '\'' +
                ", movieName='" + movieName + '\'' +
                ", time=" + time +
                '}';
    }
}
