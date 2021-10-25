package com.aurora.common.core.utils.bean;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/10/11
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@FunctionalInterface
public interface BeanUtilCallBack<S, T> {

    void callBack(S t, T s);
}
