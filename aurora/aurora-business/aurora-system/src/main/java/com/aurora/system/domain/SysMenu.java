package com.aurora.system.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * describe: 菜单权限表
 *
 * @author Guo Huaijian
 * @date 2021/10/9
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_menu")
public class SysMenu implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 菜单ID
     */
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Long menuId;
    /**
     * 菜单名称
     */
    @TableField(value = "menu_name")
    private String menuName;
    /**
     * 父菜单ID
     */
    @TableField(value = "parent_id")
    private Long parentId;
    /**
     * 显示顺序
     */
    @TableField(value = "order_num")
    private Integer orderNum;
    /**
     * 路由地址
     */
    @TableField(value = "`path`")
    private String path;
    /**
     * 组件路径
     */
    @TableField(value = "component")
    private String component;
    /**
     * 是否为外链（0是 1否）
     */
    @TableField(value = "is_frame")
    private Integer isFrame;
    /**
     * 菜单类型（M目录 C菜单 F按钮）
     */
    @TableField(value = "menu_type")
    private String menuType;
    /**
     * 菜单状态（0显示 1隐藏）
     */
    @TableField(value = "visible")
    private String visible;
    /**
     * 路由参数
     */
    @TableField(value = "query")
    private String query;
    /**
     * 是否缓存（0缓存 1不缓存）
     */
    @TableField(value = "is_cache")
    private String isCache;
    /**
     * 菜单状态（0显示 1隐藏）
     */
    @TableField(value = "status")
    private String status;
    /**
     * 权限标识
     */
    @TableField(value = "perms")
    private String perms;
    /**
     * 菜单图标
     */
    @TableField(value = "icon")
    private String icon;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;
    /**
     * 子菜单
     */
    @TableField(exist = false)
    private List<SysMenu> children;

    /**
     * 请求参数
     */
    @TableField(exist = false)
    private Map<String, Object> params;
}
