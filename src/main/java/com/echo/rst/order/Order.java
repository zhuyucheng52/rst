package com.echo.rst.order;

import com.echo.rst.entity.CommonEntity;
import com.echo.rst.menu.Menu;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by echo on 16-6-11.
 * 订单
 */
@Entity
@Table(name = "tbl_order")
public class Order extends CommonEntity implements Serializable {
    private static final long serialVersionUID = 4445022925774052611L;

    public static final int STATE_UNKOWN = 0;
    public static final int STATE_UNFINISH = 1;
    public static final int STATE_FINISHED = 2;
    public static final int STATE_CANCEL = 3;

    /** 订单生成时间 */
    private Date gTime;
    /** 状态 */
    private Integer state;
    /** 份数 */
    private Integer copies;
    /** 号码 */
    private Integer num;
    /** 备注 */
    private String comment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    public Order() {
    }

    public Order(String comment, Date gTime, Integer state, Integer copies, Integer num) {
        this.comment = comment;
        this.gTime = gTime;
        this.state = state;
        this.copies = copies;
        this.num = num;
    }

    public Date getgTime() {
        return gTime;
    }

    public void setgTime(Date gTime) {
        this.gTime = gTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getCopies() {
        return copies;
    }

    public void setCopies(Integer copies) {
        this.copies = copies;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Order{" +
                "gTime=" + gTime +
                ", state=" + state +
                ", copies=" + copies +
                ", num=" + num +
                ", comment" + comment +
                '}';
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}
