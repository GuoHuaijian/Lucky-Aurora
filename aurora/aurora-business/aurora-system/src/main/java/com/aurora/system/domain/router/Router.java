package com.aurora.system.domain.router;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * describe: 路由配置信息
 *
 * @Author Guo Huaijian
 * @Date 2021/10/9
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class Router {

    /**
     * 路由名字
     */
    private String name;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 是否隐藏路由，当设置 true 的时候该路由不会再侧边栏出现
     */
    private boolean hidden;

    /**
     * 重定向地址，当设置 noRedirect 的时候该路由在面包屑导航中不可被点击
     */
    private String redirect;

    /**
     * 组件地址
     */
    private String component;

    /**
     * 路由参数：如 {"id": 1, "name": "ry"}
     */
    private String query;

    /**
     * 当你一个路由下面的 children 声明的路由大于1个时，自动会变成嵌套的模式--如组件页面
     */
    private Boolean alwaysShow;

    /**
     * 其他元素
     */
    private Meta meta;

    /**
     * 子路由
     */
    private List<Router> children;
}
