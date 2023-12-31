package com.ligy.netty.enums;

/**
 * @Author lgy
 */
public enum ChatEnums {
    POPULAR(0,"普通聊天窗口"),
    SUPER(1,"VIP聊天窗口");
    private Integer code;
    private String desc;

    ChatEnums(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
