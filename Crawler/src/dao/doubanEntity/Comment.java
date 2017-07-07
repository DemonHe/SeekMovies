package dao.doubanEntity;


import tools.DateUtil;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by douchengfeng on 2017/6/16.
 *
 */
@Entity
@Table(name = "douban_comment")
public class Comment {
    @Id
    private long id;
    @Column(name = "movieId")
    private int movieId;
    @Column(name = "commentTime", columnDefinition ="datetime")
    private Date commentTime;
    @Column(name = "commentator")
    private String commentator;
    @Column(name = "commentLevel")
    private String commentLevel;
    @Column(name = "comment")
    private String comment;

    public long getId() {
        return id;
    }

    public void setId(String id) {
        this.id = Long.parseLong(id);
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        movieId = movieId.replace("https://movie.douban.com/subject/", "");
        movieId = movieId.replace("/","");
        this.movieId = Integer.parseInt(movieId);
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = DateUtil.formatDate(commentTime, "YYYY-MM-DD hh:mm:ss");
    }

    public String getCommentator() {
        return commentator;
    }

    public void setCommentator(String commentator) {
        this.commentator = commentator;
    }

    public String getCommentLevel() {
        return commentLevel;
    }

    public void setCommentLevel(String commentLevel) {
        this.commentLevel = commentLevel;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", movieId=" + movieId +
                ", commentTime=" + commentTime +
                ", commentator='" + commentator + '\'' +
                ", commentLevel='" + commentLevel + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
