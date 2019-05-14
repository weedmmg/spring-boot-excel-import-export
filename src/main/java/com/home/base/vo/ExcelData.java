package com.home.base.vo;

import lombok.Data;

import java.util.List;

/**
 * @author ：chenxf
 * @date ：Created in 2019/5/14 10:42
 * @description：
 * @modified By：
 * @version: $
 */
@Data
public class ExcelData {
    private String fileName;
    private String[] head;
    private List<String[]> data;
}
