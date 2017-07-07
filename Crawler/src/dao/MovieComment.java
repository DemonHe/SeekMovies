package dao;

import dao.primaryKey.ReviewPK;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.*;

/**
 * Created by douchengfeng on 2017/6/1.
 * 影评的entity
 */
@Entity
@Table(name = "comment")
@IdClass(ReviewPK.class)
public class MovieComment implements Serializable{
    @Id
    private String movie;
    @Id
    private Date time;
    @Column(name = "score")
    private Double score;
    @Column(name = "comment")
    private String comment;
    @Column(name = "username")
    private String username;

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(String time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-mm-DD HH:mm:SS");
        try {
            this.time = simpleDateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Double getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = Double.parseDouble(score);
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @Override
    public String toString() {
        return "MovieComment{" +
                "Movie='" + movie + '\'' +
                ", time='" + time + '\'' +
                ", score='" + score + '\'' +
                ", comment='" + comment + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
