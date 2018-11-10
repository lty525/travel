package cn.itcast.travel.domain;

import java.util.Date;

/**
 * @Description: java类作用描述
 * @Author: Lty
 * @CreateDate: 2018/10/12$ 15:56$
 * @Version: 1.0
 */

public class Rid {
    private int rid;
    private Date date;
    private int uid;

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "Rid{" +
                "rid=" + rid +
                ", date=" + date +
                ", uid=" + uid +
                '}';
    }
}
