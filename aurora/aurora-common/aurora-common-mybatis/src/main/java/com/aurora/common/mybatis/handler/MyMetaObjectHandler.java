package com.aurora.common.mybatis.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * describe: 自动填充时间
 *
 * @author Guo Huaijian
 * @date 2021/10/24
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        this.strictInsertFill(metaObject, "createTime", Date::new, Date.class);
        this.strictUpdateFill(metaObject, "updateTime", Date::new, Date.class);
        this.strictUpdateFill(metaObject, "loginTime", Date::new, Date.class);
        this.strictUpdateFill(metaObject, "visitTime", Date::new, Date.class);
        this.strictUpdateFill(metaObject, "operateTime", Date::new, Date.class);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        this.strictUpdateFill(metaObject, "updateTime", Date::new, Date.class);
    }
}
