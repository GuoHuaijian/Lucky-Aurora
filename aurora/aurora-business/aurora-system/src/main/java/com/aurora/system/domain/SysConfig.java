package com.aurora.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/10/10
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */

/**
 * 参数配置表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_config")
public class SysConfig implements Serializable {
    /**
     * 参数主键
     */
    @TableId(value = "config_id", type = IdType.AUTO)
    private Integer configId;

    /**
     * 参数名称
     */
    @TableField(value = "config_name")
    private String configName;

    /**
     * 参数键名
     */
    @TableField(value = "config_key")
    private String configKey;

    /**
     * 参数键值
     */
    @TableField(value = "config_value")
    private String configValue;

    /**
     * 系统内置（Y是 N否）
     */
    @TableField(value = "config_type")
    private String configType;

    /**
     * 创建者
     */
    @TableField(value = "create_by")
    private String createBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新者
     */
    @TableField(value = "update_by")
    private String updateBy;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    private static final long serialVersionUID = 1L;
}
