package com.aurora.common.core.utils.bean;

/**
 * describe:
 *
 * @author Guo Huaijian
 * @date 2021/10/11
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
@FunctionalInterface
public interface BeanUtilCallBack<S, T> {

    void callBack(S t, T s);
}
