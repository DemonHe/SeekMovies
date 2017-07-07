package dao;

import dao.primaryKey.DirectorPK;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by run-jiao on 2017/6/11.
 */
@Entity
@Table(name = "dirctor_list")

@IdClass(DirectorPK.class)
public class Director implements Serializable{
    @Id
    private String name;
    @Id
    private String movie;
    @Column(name = "directorName")
    private String name2="";

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    @Override
    public String toString(){
        return "Director:{movie:"+movie+" name:"+name+"}";
    }

}
