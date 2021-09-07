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

/**
 * 轮播图
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "aurora_blog.blog_carousel")
public class BlogCarousel implements Serializable {
    /**
     * 轮播图id
     */
    @TableId(value = "carousel_id", type = IdType.AUTO)
    private Integer carouselId;

    /**
     * 轮播图url
     */
    @TableField(value = "img_url")
    private String imgUrl;

    /**
     * 轮播图标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 轮播图跳转地址
     */
    @TableField(value = "url")
    private String url;

    /**
     * 是否展示该轮播图，1展示，2不展示
     */
    @TableField(value = "display")
    private String display;

    /**
     * 1外链，2文章
     */
    @TableField(value = "type")
    private Boolean type;

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
