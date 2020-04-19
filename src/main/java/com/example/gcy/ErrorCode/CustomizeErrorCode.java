package com.example.gcy.ErrorCode;

/**
 *
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode {


    LOGIN_NULL(3001, "请输入用户名或密码！"),
    LOGIN_ERROR(3002, "用户名或密码错误！"),
    REGISTER_NICK_NULL(3003, "姓名不能为空！"),
    REGISTER_USERNAME_NULL(3004, "用户名不能为空！"),
    REGISTER_PASSWORD_NULL(3005, "密码不能为空！"),
    REGISTER_USER_EXIST(3006, "用户名已存在！"),
    REGISTER_ERROR(3007, "注册错误，请重试！"),
    REGISTER_UPDATE_ERROR(3008, "更新失败，请重试！"),
    DEL_ERROR(3009, "删除失败，请重试！"),
    NO_LOGIN(3010, "请先登录！"),
    TIME_ERROR(3011, "修改失败，请重试！"),
    ;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    private Integer code;
    private String message;

    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }
}
