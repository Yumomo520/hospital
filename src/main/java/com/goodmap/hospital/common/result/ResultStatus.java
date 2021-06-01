package com.goodmap.hospital.common.result;

/**
 * @author 付洪峰
 * @date 2020/5/27
 * @description 错误码
 */
public enum ResultStatus {
    SUCCESS("200", "正常"),

    /**
     * 一级宏观异常
     */
    USER_SIDE_ERROR("A0001", "用户端错误"),
    /**
     * 一级宏观异常
     */
    SERVER_SIDE_ERROR("A0002", "业务繁忙，请稍后"),
    /**
     * 二级宏观异常
     */
    USER_SIGN_IN_ERROR("A0100", "用户注册错误"),
    USERNAME_VERIFY_ERROR("A0110", "用户名校验失败"),
    USER_EXIST("A0111", "用户名已存在"),
    USERNAME_INCLUDE_SPECIAL_CHARACTER("A0113", "用户名包含特殊字符"),
    PASSWORD_VERIFY_ERROR("A0120", "密码校验失败"),
    PASSWORD_LENGTH_SHORT("A0121", "密码长度不够"),

    /**
     * 二级宏观错误码
     */
    USER_LOGIN_EXCEPTION("A0200", "用户登陆异常"),
    USER_ACCOUNT_NOT_EXIST("A0201", "用户账户不存在"),
    USER_ACCOUNT_FREEZE("A0202", "用户账户被冻结"),
    USER_NOT_LOGIN("A0203", "未登录"),
    USER_CANT_LOGIN_APP("A0204", "未有APP使用权，登录失败"),
    USER_PASSWORD_ERROR("A0210", "用户密码错误"),
    VISIT_PERMISSION_EXCEPTION("A0300", "访问权限异常"),
    NOT_PERMISSION_EMPLOY_API("A0312", "无操作权限"),

    /**
     * 二级宏观错误码
     */
    USER_REQUEST_PARAM_ERROR("A0400", "用户请求参数错误"),

    /**
     * 二级宏观错误码
     */
    ADD_ENTITY_ERROR("A0500", "添加失败"),
    EDIT_ENTITY_ERROR("A0501", "修改失败"),
    DELETE_ENTITY_ERROR("A0502", "删除失败"),
    RESET_PASSWORD_ERROR("A0505", "重置密码失败"),

    /**
     * 二级宏观错误码
     */
    USER_UPLOAD_FILE_ERROR("A0700", "用户上传文件异常"),
    USER_UPLOAD_FILE_TYPE_NOT_MATCHING("A0701", "用户上传文件类型不匹配"),
    USER_UPLOAD_FILE_OVERSIZE("A0702", "用户上传文件太大"),
    USER_UPLOAD_IMAGE_OVERSIZE("A0703", "用户上传图片太大"),
    USER_UPLOAD_VIDEO_OVERSIZE("A0704", "用户上传视频太大"),
    USER_UPLOAD_ZIP_OVERSIZE("A0705", "用户上传压缩文件太大"),
    FILE_NOT_EXIT("A0706", "文件不存在"),
    USER_DOWNLOAD_FILE_ERROR("A0707", "用户下载文件异常"),
    NAME_CANNOT_BE_REPEATED("A0708", "名称不可重复"),


    /**
     * 二级宏观错误码
     */
    DATABASE_SERVICE_TIMEOUT("C0250", "数据库服务超时"),
    DATABASE_SERVICE_EXCEPTION("C0300", "数据库服务出错"),
    REDIS_EXCEPTION("C0350", "缓存数据库异常"),
    DATA_NOT_FOUND("C0404", "数据未找到"),
    GEODB_DATA_NOT_FOUND("CG404", "地图数据未找到"),

    REQUEST_METHOD_NOT_SUPPORT("W0001", "请求方法不被支持，请检查");


    private final String code;
    private final String message;

    ResultStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
