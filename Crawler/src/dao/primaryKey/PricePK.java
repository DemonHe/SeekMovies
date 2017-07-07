package dao.primaryKey;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by douchengfeng on 2017/6/11.
 * price表的主键
 */
@Embeddable
public class PricePK implements Serializable{
    @Column(name = "id", length = 50)
    private String id;
    @Column(name = "movieName", length = 100)
    private String movieName;

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

    @Override
    public String toString() {
        return "PricePK{" +
                "id='" + id + '\'' +
                ", movieName='" + movieName + '\'' +
                '}';
    }
}
