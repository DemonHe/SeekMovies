package dao.doubanEntity;

import tools.DateUtil;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by douchengfeng on 2017/6/17.
 *
 */
@Entity
@Table(name = "douban_box_office")
public class BoxOffice implements Serializable{
    @Id
    private String movieName;
    @Column(name = "startTime")
    private Date startTime;
    @Column(name = "endTime")
    private Date endTime;
    @Column(name = "boxOffice")
    private double boxOffice;


    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        //System.out.println(startTime);
        startTime = startTime.split(" - ")[0];
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        startTime = year + "年" + startTime;
        this.startTime = DateUtil.formatDate(startTime, "yyyy年M月d日");
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        endTime = endTime.split(" - ")[1];
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        endTime = year + "年" + endTime;
        this.endTime = DateUtil.formatDate(endTime, "yyyy年M月d日");

    }

    public double getBoxOffice() {
        return boxOffice;
    }

    public void setBoxOffice(String boxOffice) {
        //System.out.println(boxOffice);
        boxOffice = boxOffice.replace("票房", "");
        double integer = 0.0;
        double decimal;
        if(boxOffice.contains("亿")){
            String[] temp = boxOffice.split("亿");
            integer = Double.parseDouble(temp[0]);
            boxOffice = temp[1];
        }
        boxOffice = boxOffice.replace("万", "");
        decimal = Double.parseDouble(boxOffice)/10000;
        this.boxOffice = integer + decimal;
    }

    @Override
    public String toString() {
        return "BoxOffice{" +
                "movieName='" + movieName + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", boxOffice=" + boxOffice +
                '}';
    }
}
