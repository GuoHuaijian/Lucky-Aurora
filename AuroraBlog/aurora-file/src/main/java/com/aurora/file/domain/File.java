package com.aurora.file.domain;

import lombok.Data;

/**
 * describe: 文件信息
 *
 * @Author Guo
 * @Date 2021/9/10
 * @Version 1.0
 */
@Data
public class File {

    /**
     * 文件名称
     */
    private String name;

    /**
     * 文件地址
     */
    private String url;
}
