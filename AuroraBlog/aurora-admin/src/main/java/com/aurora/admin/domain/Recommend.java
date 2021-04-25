package com.aurora.admin.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/1/3
 * @E-mail 564559079@qq.com
 * @Version 1.0
 */

/**
 * 推荐
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "dbblog.recommend")
public class Recommend implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 推荐的文章Id
     */
    @TableField(value = "link_id")
    private Integer linkId;

    /**
     * 推荐类型
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 顺序
     */
    @TableField(value = "order_num")
    private Integer orderNum;

    /**
     * 标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 置顶
     */
    @TableField(value = "top")
    private Boolean top;

    private static final long serialVersionUID = 1L;
}
