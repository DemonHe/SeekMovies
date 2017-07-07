package dao;

import dao.primaryKey.ActorPK;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by run-jiao on 2017/6/11.
 *
 */
@Entity
@Table(name = "actor_list")
@IdClass(ActorPK.class)
public class Actor implements Serializable{
    @Id
    private String movie;
    @Id
    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return "Actor:{movie:"+movie+" name:"+name+"}";
    }
}
