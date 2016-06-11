package com.rst.echo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by echo on 16-6-11.
 * 操作日志
 */
public class OperLog extends CommonEntity implements Serializable {
    private static final long serialVersionUID = -4321131666897469383L;

    /** 日志生成时间 */
    private Date gTime;
    /** 操作结果 */
    private Integer state;
    /** 操作内容 */
    private String content;
    /** 操作原因 */
    private String reason;

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
                ", content='" + content + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }
}
