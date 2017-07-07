package dao.doubanEntity;

import dao.doubanEntity.doubanPk.MovieTagPK;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by douchengfeng on 2017/6/16.
 *
 */
@Entity
@Table(name = "douban_movie_tag")
@IdClass(MovieTagPK.class)
public class MovieTag implements Serializable{
    @Id
    private int movieId;
    @Id
    private String tag;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        movieId = movieId.replaceAll("(https://movie.douban.com)?/subject/", "");
        movieId = movieId.replace("/photos?type=R","");
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
