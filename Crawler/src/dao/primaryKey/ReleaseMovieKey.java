package dao.primaryKey;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by douchengfeng on 2017/6/3.
 * ReleasingMovie的主见类
 */
@Embeddable
public class ReleaseMovieKey implements Serializable{
    @Column(name = "city", length = 100)
    private String city;
    @Column(name = "movieName", length = 100)
    private String movieName;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }
}
