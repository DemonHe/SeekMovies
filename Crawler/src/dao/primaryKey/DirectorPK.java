package dao.primaryKey;

import javax.persistence.Column;
import java.io.Serializable;

/**
 * Created by run-jiao on 2017/6/11.
 */
public class DirectorPK implements Serializable{
    @Column(name = "dirctorName")
    private String name;
    @Column(name = "movieName")
    private String movie;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        int x = movie.indexOf(" ");
        if(x>0){
            movie = movie.substring(0,x);
        }
        this.movie = movie;
    }
}
