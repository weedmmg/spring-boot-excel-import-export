package com.home.base.msg;

import lombok.Data;

/**
 * Created by Ace on 2017/6/11.
 */
@Data
public class ObjectRestResponse<T> {
    boolean rel;
    String msg;
    T result;

    public ObjectRestResponse() {

    }
    public ObjectRestResponse(String msg) {
        this.msg = msg;
    }

    public static ObjectRestResponse success() {
        return new ObjectRestResponse().rel(true);
    }

    public static ObjectRestResponse success(String msg) {
        return new ObjectRestResponse(msg).rel(true);
    }

    public static ObjectRestResponse success(Object data) {
        ObjectRestResponse jr = new ObjectRestResponse().rel(true);
        jr.setResult(data);
        return jr;
    }

    public static ObjectRestResponse error() {

        return  new ObjectRestResponse().rel(false);
    }

    public static ObjectRestResponse error(String msg) {
        return new ObjectRestResponse(msg).rel(false);
    }

    public ObjectRestResponse rel(boolean rel) {
        this.setRel(rel);
        return this;
    }

    public ObjectRestResponse msg(String msg) {
        this.setMsg(msg);
        return this;
    }

    public ObjectRestResponse result(T result) {
        this.setResult(result);
        return this;
    }
}
