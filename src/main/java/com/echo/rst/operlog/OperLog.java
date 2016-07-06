package com.echo.rst.operlog;

import com.echo.rst.domain.CommonEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by echo on 16-6-11.
 * 操作日志
 */
@Entity
@Table(name = "tbl_oper_log")
public class OperLog extends CommonEntity implements Serializable {
    private static final long serialVersionUID = -4321131666897469383L;

    public static final int STATE_UNKNOWN = 0;
    public static final int STATE_SUCCESS = 1;
    public static final int STATE_FAILURE = 2;

    /** 日志生成时间 */
    private Date gTime;
    /** 操作类型 */
    private Integer category;
    /** 操作结果 */
    private Integer state;
    /** 操作内容 */
    private String content;
    /** 操作原因 */
    private String reason;

    public OperLog() {
    }

    public OperLog(Date gTime, Integer category, Integer state, String content, String reason) {
        this.gTime = gTime;
        this.category = category;
        this.state = state;
        this.content = content;
        this.reason = reason;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "OperLog{" +
                "gTime=" + gTime +
                ", state=" + state +
                ", category=" + category +
                ", content='" + content + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }
}
