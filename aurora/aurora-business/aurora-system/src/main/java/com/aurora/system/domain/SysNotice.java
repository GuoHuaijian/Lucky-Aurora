package com.aurora.system.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * describe: 通知公告表
 *
 * @Author Guo Huaijian
 * @Date 2021/10/10
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_notice")
public class SysNotice implements Serializable {
    /**
     * 公告ID
     */
    @TableId(value = "notice_id", type = IdType.AUTO)
    private Integer noticeId;

    /**
     * 公告标题
     */
    @TableField(value = "notice_title")
    private String noticeTitle;

    /**
     * 公告类型（1通知 2公告）
     */
    @TableField(value = "notice_type")
    private String noticeType;

    /**
     * 公告内容
     */
    @TableField(value = "notice_content")
    private byte[] noticeContent;

    /**
     * 公告状态（0正常 1关闭）
     */
    @TableField(value = "`status`")
    private String status;

    /**
     * 创建者
     */
    @TableField(value = "create_by")
    private String createBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新者
     */
    @TableField(value = "update_by")
    private String updateBy;

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

    private static final long serialVersionUID = 1L;
}
