package com.odp.base;

/**
 * @author ChenHh
 * @time 2018/11/20 15:16
 * @des gank 数据类型
 **/
public enum GankTypeEnum {
    ANDROID("Android", 1), IOS("iOS", 2), WEAL("福利", 0), WEB("前端", 3);


    private final String type;
    private final int index;

    GankTypeEnum(String type, int index) {
        this.type = type;
        this.index = index;
    }

    public String getType(int index) {
        for (GankTypeEnum gankTypeEnum : GankTypeEnum.values()) {
            if (gankTypeEnum.index == index) {
                return gankTypeEnum.type;
            }
        }
        return "";
    }

    public String getType() {
        return type;
    }

    public int getIndex() {
        return index;
    }
}
