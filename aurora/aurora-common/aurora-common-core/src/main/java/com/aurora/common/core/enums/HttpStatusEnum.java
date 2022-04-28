package com.aurora.common.core.enums;

/**
 * http状态枚举
 * describe: 返回状态码及信息
 *
 * @author Guo Huaijian
 * @date 2020/12/31
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
public enum HttpStatusEnum {

    /**
     * 继续
     */
    CONTINUE(100, "Continue"),
    /**
     * 交换协议
     */
    SWITCHING_PROTOCOLS(101, "Switching Protocols"),
    /**
     * 处理
     */
    PROCESSING(102, "Processing"),
    /**
     * 检查点
     */
    CHECKPOINT(103, "Checkpoint"),
    /**
     * 成功
     */
    OK(200, "OK"),
    /**
     * 创建
     */
    CREATED(201, "Created"),
    /**
     * 接受
     */
    ACCEPTED(202, "Accepted"),
    /**
     * 非权威的信息
     */
    NON_AUTHORITATIVE_INFORMATION(203, "Non-Authoritative Information"),
    /**
     * 没有内容
     */
    NO_CONTENT(204, "No Content"),
    /**
     * 重置内容
     */
    RESET_CONTENT(205, "Reset Content"),
    /**
     * 部分内容
     */
    PARTIAL_CONTENT(206, "Partial Content"),
    /**
     * 多状态
     */
    MULTI_STATUS(207, "Multi-Status"),
    /**
     * 已经报道的
     */
    ALREADY_REPORTED(208, "Already Reported"),
    /**
     * 我使用
     */
    IM_USED(226, "IM Used"),
    /**
     * 多个选择
     */
    MULTIPLE_CHOICES(300, "Multiple Choices"),
    /**
     * 搬到永久
     */
    MOVED_PERMANENTLY(301, "Moved Permanently"),
    /**
     * 发现
     */
    FOUND(302, "Found"),
    /**
     * 看到其他
     */
    SEE_OTHER(303, "See Other"),
    /**
     * 不修改
     */
    NOT_MODIFIED(304, "Not Modified"),
    /**
     * 临时重定向
     */
    TEMPORARY_REDIRECT(307, "Temporary Redirect"),
    /**
     * 永久重定向
     */
    PERMANENT_REDIRECT(308, "Permanent Redirect"),
    /**
     * 坏的请求
     */
    BAD_REQUEST(400, "Bad Request"),
    /**
     * 未经授权的
     */
    UNAUTHORIZED(401, "Unauthorized"),
    /**
     * 付款要求
     */
    PAYMENT_REQUIRED(402, "Payment Required"),
    /**
     * 被禁止的
     */
    FORBIDDEN(403, "Forbidden"),
    /**
     * 没有找到
     */
    NOT_FOUND(404, "Not Found"),
    /**
     * 方法不允许
     */
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    /**
     * 不能接受的
     */
    NOT_ACCEPTABLE(406, "Not Acceptable"),
    /**
     * 代理身份验证要求
     */
    PROXY_AUTHENTICATION_REQUIRED(407, "Proxy Authentication Required"),
    /**
     * 请求超时
     */
    REQUEST_TIMEOUT(408, "Request Timeout"),
    /**
     * 冲突
     */
    CONFLICT(409, "Conflict"),
    /**
     * 走了
     */
    GONE(410, "Gone"),
    /**
     * 长度要求
     */
    LENGTH_REQUIRED(411, "Length Required"),
    /**
     * 失败的前提
     */
    PRECONDITION_FAILED(412, "Precondition Failed"),
    /**
     * 负荷太大
     */
    PAYLOAD_TOO_LARGE(413, "Payload Too Large"),
    /**
     * uri的时间太长
     */
    URI_TOO_LONG(414, "URI Too Long"),
    /**
     * 不支持的媒体类型
     */
    UNSUPPORTED_MEDIA_TYPE(415, "Unsupported Media Type"),
    /**
     * 不可以满足的要求范围
     */
    REQUESTED_RANGE_NOT_SATISFIABLE(416, "Requested range not satisfiable"),
    /**
     * 期望失败
     */
    EXPECTATION_FAILED(417, "Expectation Failed"),
    /**
     * 我是一个茶壶
     */
    I_AM_A_TEAPOT(418, "I'm a teapot"),
    /**
     * unprocessable实体
     */
    UNPROCESSABLE_ENTITY(422, "Unprocessable Entity"),
    /**
     * 锁着的
     */
    LOCKED(423, "Locked"),
    /**
     * 失败的依赖
     */
    FAILED_DEPENDENCY(424, "Failed Dependency"),
    /**
     * 言之过早
     */
    TOO_EARLY(425, "Too Early"),
    /**
     * 升级需要
     */
    UPGRADE_REQUIRED(426, "Upgrade Required"),
    /**
     * 先决条件要求
     */
    PRECONDITION_REQUIRED(428, "Precondition Required"),
    /**
     *
     */
    TOO_MANY_REQUESTS(429, "Too Many Requests"),
    /**
     *
     */
    REQUEST_HEADER_FIELDS_TOO_LARGE(431, "Request Header Fields Too Large"),
    /**
     *
     */
    UNAVAILABLE_FOR_LEGAL_REASONS(451, "Unavailable For Legal Reasons"),
    /**
     *
     */
    ERROR(500, "Internal Server Error"),
    /**
     * 没有实现
     */
    NOT_IMPLEMENTED(501, "Not Implemented"),
    /**
     * 坏的网关
     */
    BAD_GATEWAY(502, "Bad Gateway"),
    /**
     * 服务不可用
     */
    SERVICE_UNAVAILABLE(503, "Service Unavailable"),
    /**
     * 网关超时
     */
    GATEWAY_TIMEOUT(504, "Gateway Timeout"),
    /**
     * http版本不支持
     */
    HTTP_VERSION_NOT_SUPPORTED(505, "HTTP Version not supported"),
    /**
     * 变体也协商
     */
    VARIANT_ALSO_NEGOTIATES(506, "Variant Also Negotiates"),
    /**
     * 存储不足
     */
    INSUFFICIENT_STORAGE(507, "Insufficient Storage"),
    /**
     * 循环检测
     */
    LOOP_DETECTED(508, "Loop Detected"),
    /**
     * 带宽限制超过
     */
    BANDWIDTH_LIMIT_EXCEEDED(509, "Bandwidth Limit Exceeded"),
    /**
     * 不延长
     */
    NOT_EXTENDED(510, "Not Extended"),
    /**
     * 网络身份验证要求
     */
    NETWORK_AUTHENTICATION_REQUIRED(511, "Network Authentication Required");

    private final int code;

    private final String msg;

    HttpStatusEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "HttpStatusEnum{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
