package dao;



import com.sun.istack.internal.NotNull;
import org.hibernate.annotations.*;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * Created by run-jiao on 2017/6/7.
 * 电影下载链接
 */
@Entity
@Table(name = "download_link")
public class MovieLink implements Serializable{
    @Id
    private String downloadLink;

    @NotNull
    @Column(name = "name", length = 200)
    @Index(name = "nameIndex")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        int x = name.indexOf("《");
        int y = name.indexOf("》");
        String tempName = name.substring(x + 1,y);
        int z = tempName.indexOf("/");
        if(z!=-1){
            tempName = tempName.substring(0,z);
        }
        if(x<y-1) {
            this.name = tempName;
        }else{
            this.name = "error when get name";
        }
    }

    public String getDownloadLink() {
        return downloadLink;
    }

    public void setDownloadLink(String downloadLink) {
        this.downloadLink = downloadLink;
    }

    @Override
    public String toString(){
        return "Link : {name : "+this.name+" ; downloadLink : "+this.downloadLink+" }";
    }


}

