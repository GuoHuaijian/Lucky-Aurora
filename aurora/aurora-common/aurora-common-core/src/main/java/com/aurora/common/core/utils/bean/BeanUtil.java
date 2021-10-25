package com.aurora.common.core.utils.bean;

import com.aurora.common.core.utils.bean.BeanUtilCallBack;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/10/11
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
public class BeanUtil extends BeanUtils {

    public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target) {
        return copyListProperties(sources, target, null);
    }


    public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target, BeanUtilCallBack<S, T> callBack) {
        List<T> list = new ArrayList<>(sources.size());
        for (S source : sources) {
            T t = target.get();
            copyProperties(source, t);
            if (callBack != null) {
                // 回调
                callBack.callBack(source, t);
            }
            list.add(t);
        }
        return list;
    }
}
