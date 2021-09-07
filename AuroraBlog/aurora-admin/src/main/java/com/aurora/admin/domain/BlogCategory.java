package com.aurora.admin.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * describe:
 *
 * @Author Guo
 * @Date 2021/9/7 13:20
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "aurora_blog.blog_category")
public class BlogCategory implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "category_id", type = IdType.AUTO)
    private Integer categoryId;

    /**
     * 名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 级别
     */
    @TableField(value = "rank")
    private Integer rank;

    /**
     * 父主键
     */
    @TableField(value = "parent_id")
    private Integer parentId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 修改时间
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
