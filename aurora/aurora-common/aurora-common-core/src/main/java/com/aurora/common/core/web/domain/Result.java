package com.aurora.common.core.web.domain;

import com.alibaba.fastjson.JSON;
import com.aurora.common.core.constant.Constants;
import com.aurora.common.core.enums.HttpStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.HashMap;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2020/12/31
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@Data
@Slf4j
@EqualsAndHashCode(callSuper = false)
public class Result<T> extends HashMap<String, Object> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    public static final String CODE_TAG = "code";

    /**
     * 返回内容
     */
    public static final String MSG_TAG = "msg";

    /**
     * 数据对象
     */
    public static final String DATA_TAG = "data";

    /**
     * 初始化一个新创建的 Result 对象，使其表示一个空消息。
     */
    public Result() {
    }

    /**
     * 初始化一个新创建的 Result 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     */
    public Result(int code, String msg) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
    }

    /**
     * 初始化一个新创建的 BaseResult 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     * @param data 数据对象
     */
    public Result(int code, String msg, Object data) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        if (data != null) {
            super.put(DATA_TAG, data);
        }
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static Result success() {
        return Result.success(HttpStatusEnum.OK.getMsg());
    }

    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static Result success(Object data) {
        return Result.success(HttpStatusEnum.OK.getMsg(), data);
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @return 成功消息
     */
    public static Result success(String msg) {
        return Result.success(msg, null);
    }

    /**
     * 返回成功消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static Result success(String msg, Object data) {
        return new Result(HttpStatusEnum.OK.getCode(), msg, data);
    }

    /**
     * 返回成功消息
     *
     * @param msg  返回内容
     * @param code 状态码
     * @return 成功消息
     */
    public static Result success(String msg, int code) {
        return new Result(code, msg);
    }

    /**
     * 返回成功消息
     *
     * @param code 状态码
     * @param data 数据对象
     * @param msg  返回内容
     * @return
     */
    public static Result success(int code, String msg, Object data) {
        return new Result(code, msg, data);
    }


    /**
     * 返回成功消息
     *
     * @param statusEnum 状态码
     * @param data       数据对象
     * @return
     */
    public static Result success(HttpStatusEnum statusEnum, Object data) {
        return new Result(statusEnum.getCode(), statusEnum.getMsg(), data);
    }

    /**
     * 返回成功消息
     *
     * @param statusEnum 状态码
     * @param msg        返回内容
     * @param data       数据对象
     * @return
     */
    public static Result success(HttpStatusEnum statusEnum, String msg, Object data) {
        return new Result(statusEnum.getCode(), msg, data);
    }

    /**
     * 返回错误消息
     *
     * @return
     */
    public static Result error() {
        return Result.error(HttpStatusEnum.ERROR.getMsg());
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static Result error(String msg) {
        return Result.error(msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static Result error(String msg, Object data) {
        return new Result(HttpStatusEnum.ERROR.getCode(), msg, data);
    }

    /**
     * 返回错误消息
     *
     * @param code 状态码
     * @param msg  返回内容
     * @return 警告消息
     */
    public static Result error(int code, String msg) {
        return new Result(code, msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param status 状态码
     * @return 警告消息
     */
    public static Result error(HttpStatusEnum status) {
        return new Result(status.getCode(), status.getMsg(), null);
    }

    /**
     * 返回错误消息
     *
     * @param status 状态码
     * @param msg    返回内容
     * @return 警告消息
     */
    public static Result error(HttpStatusEnum status, String msg) {
        return new Result(status.getCode(), msg, null);
    }

    /**
     * Response输出Json格式
     *
     * @param response
     * @param data     返回数据
     */
    public static void responseJson(HttpServletResponse response, Object data) {
        PrintWriter out = null;
        try {
            response.setStatus(HttpStatusEnum.OK.getCode());
            response.setCharacterEncoding(Constants.UTF8);
            response.setContentType(Constants.RESPONSE_CONTENT_TYPE_JSON);
            out = response.getWriter();
            out.println(JSON.toJSONString(data));
            out.flush();
        } catch (Exception e) {
            log.error("Response输出Json异常：" + e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

}
