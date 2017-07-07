package dao.taoppEntity;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/7.
 */
public class MovieTag implements Serializable{
    @Id
    private int movieId;
    @Id
    private String tag;

    public MovieTag(int movieId, String tag) {
        this.movieId = movieId;
        this.tag = tag;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        String[] strings = movieId.split("&");
        movieId = strings[1];
        movieId = movieId.split("=")[1];
        this.movieId = Integer.parseInt(movieId);
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "MovieTag{" +
                "movieId=" + movieId +
                ", tag='" + tag + '\'' +
                '}';
    }
}
