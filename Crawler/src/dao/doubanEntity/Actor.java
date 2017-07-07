package dao.doubanEntity;

import tools.DateUtil;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by douchengfeng on 2017/6/16.
 * 豆瓣电影中对应的演员
 */
@Entity
@Table(name = "douban_actors")
public class Actor implements Serializable{
    @Id
    private int id;
    @Column(name = "actorName")
    private String actorName;
    @Column(name = "sex")
    private String sex;
    @Column(name = "birthDay")
    private Date birthDay;
    @Column(name = "homeLand")
    private String homeLand;
    @Column(name = "headPic")
    private String headPic;
    @Column(name = "introduce")
    private String introduce;

    public int getId() {
        return id;
    }

    public void setId(String id) {
        id = id.replace("https://movie.douban.com/celebrity/","");
        id = id.replace("/","");
        this.id = Integer.parseInt(id);
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        //System.out.println(sex);
        Pattern pattern = Pattern.compile("性别: [\\u4e00-\\u9fa5]");
        Matcher matcher = pattern.matcher(sex);
        if(matcher.find()){
            this.sex = matcher.group().split(" ")[1];
        }

    }

    public Date getBirthDay() {

        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        Pattern pattern = Pattern.compile("出生日期: [0-9]{4}-[0-9]{2}-[0-9]{2}");
        Matcher matcher = pattern.matcher(birthDay);
        if(matcher.find()){
            this.birthDay = DateUtil.formatDate(matcher.group().split(" ")[1], "YYYY-MM-DD");
        }
    }

    public String getHomeLand() {
        return homeLand;
    }

    public void setHomeLand(String homeLand) {
        Pattern pattern = Pattern.compile("出生地: ([\\u4e00-\\u9fa5]|,|-)+");
        Matcher matcher = pattern.matcher(homeLand);
        if(matcher.find()){
            this.homeLand = matcher.group().split(" ")[1];
        }

    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "id=" + id +
                ", actorName='" + actorName + '\'' +
                ", sex='" + sex + '\'' +
                ", birthDay=" + birthDay +
                ", homeLand='" + homeLand + '\'' +
                ", headPic='" + headPic + '\'' +
                ", introduce='" + introduce + '\'' +
                '}';
    }
}
