package com.home.enums;

/**
 * @author ：chenxf
 * @date ：Created in 2019/4/8 9:57
 * @description：咨询状态
 * @modified By：
 * @version: 1.0$
 */
public enum  BoardEnums {
    /**
     * 未处理状态
     */
    UNTREATED("0", "未处理","wk.png"),
    /**
     * 有意向
     */
    INTENTION("1", "有意向","wk.png"),
    /**
     * 已拒绝
     */
    REJECT("2", "已拒绝","wk.png"),
    /**
     * 其它
     */
    OTHER("3", "其它","wk.png");


    private final String key;
    private final String desc;
    private final String icon;

    private BoardEnums(String key, String desc,String icon) {
        this.key = key;
        this.desc = desc;
        this.icon=icon;
    }

    public static BoardEnums getIdType(String idType) {
        BoardEnums[] its = values();
        BoardEnums[] arr$ = its;
        int len$ = its.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            BoardEnums it = arr$[i$];
            if (it.getKey() .equals( idType)) {
                return it;
            }
        }
        return OTHER;
    }

    public String getKey() {
        return this.key;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getIcon() {
        return icon;
    }
}
