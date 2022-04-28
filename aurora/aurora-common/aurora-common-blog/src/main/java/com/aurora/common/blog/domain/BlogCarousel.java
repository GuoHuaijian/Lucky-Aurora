package com.aurora.common.blog.domain;

import com.aurora.common.core.web.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * describe: 轮播图
 *
 * @author Guo Huaijian
 * @date 2021/10/16
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "blog_carousel")
public class BlogCarousel extends BaseEntity implements Serializable {
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
    @TableField(value = "`type`")
    private Integer type;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    private static final long serialVersionUID = 1L;
}
