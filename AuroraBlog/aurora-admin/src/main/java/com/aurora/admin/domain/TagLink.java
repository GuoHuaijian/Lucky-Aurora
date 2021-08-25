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

/**
 * describe:标签多对多维护表
 *
 * @Author Guo Huaijian
 * @Date 2021/1/3
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "dbblog.tag_link")
public class TagLink implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 标签Id
     */
    @TableField(value = "tag_id")
    private Integer tagId;

    /**
     * 关联Id
     */
    @TableField(value = "link_id")
    private Integer linkId;

    /**
     * 所属类别：0文章，1阅读
     */
    @TableField(value = "type")
    private Integer type;

    private static final long serialVersionUID = 1L;
}
