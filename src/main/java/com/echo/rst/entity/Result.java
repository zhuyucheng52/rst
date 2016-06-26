package com.echo.rst.entity;

import java.io.Serializable;

/**
 * Created by echo on 16-6-11.
 */
public class Result<T> implements Serializable {

    private static final long serialVersionUID = -1293935376154545476L;
    public static final int PAGE_SIZE = 20;

    static enum State {
        SUCCESS,        // 成功
        FAILURE,        // 失败
        SUCCESS_PART    // 部分成功
    }


    private State state = State.SUCCESS;
    private T data;
    private String msg;

    public Result(State state, String msg) {
        this.state = state;
        this.msg = msg;
    }

    public Result(State state, String msg, T data) {
        this.state = state;
        this.msg = msg;
        this.data = data;
    }

    public State getState() {
        return state;
    }

    public T getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }
}
