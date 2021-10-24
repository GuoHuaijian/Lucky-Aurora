package com.aurora.common.config.config.mybatis;

import com.aurora.common.core.utils.DateUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * describe: 自动填充时间
 *
 * @Author Guo Huaijian
 * @Date 2021/10/24
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        this.strictInsertFill(metaObject, "createTime", () -> DateUtil.getNowDate(), Date.class);
        this.strictUpdateFill(metaObject, "updateTime", () -> DateUtil.getNowDate(), Date.class);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        this.strictUpdateFill(metaObject, "updateTime", () -> DateUtil.getNowDate(), Date.class);
    }
}
