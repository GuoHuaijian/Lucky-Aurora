package com.aurora.common.core.utils;

import cn.hutool.core.convert.Convert;
import org.apache.commons.lang3.StringUtils;

/**
 * describe:
 *
 * @author Guo
 * @since 2022/6/13 14:50
 */
public class LongUtil {

    /**
     * Long类型非空判断
     *
     * @param value
     * @return
     */
    public static boolean isNotEmpty(Long value){
        return StringUtils.isNotBlank(Convert.toStr(value)) && value != 0;
    }

    /**
     * Long类型为空判断
     *
     * @param value
     * @return
     */
    public static boolean isEmpty(Long value){
        return StringUtils.isBlank(Convert.toStr(value)) || value == 0;
    }

}
