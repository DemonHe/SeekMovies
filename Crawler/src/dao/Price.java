package dao;

import dao.primaryKey.PricePK;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by douchengfeng on 2017/6/11.
 * 票价表
 */
@Entity
@Table(name = "price")
@IdClass(PricePK.class)
public class Price implements Serializable{
    @Id
    private String id;
    @Id
    private String movieName;
    @Column(name = "price", length = 150)
    private String price;

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
        this.movieName = movieName.split(" ")[0];
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price.replace(" 可购票", "").replace("票价 ", "");
    }

    @Override
    public String toString() {
        return "Price{" +
                "id='" + id + '\'' +
                ", movieName='" + movieName + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

}
