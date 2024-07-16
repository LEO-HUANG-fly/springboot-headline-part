package com.atguigu.pojo.vo;

import lombok.Data;

/**
 * ClassName: PortalVo
 * Package: com.atguigu.pojo.vo
 * Description：
 *
 * @Author LeoHuang
 * @Create 2024/7/8 11:02
 * @Version 1.0
 */
@Data
public class PortalVo {
    private String keyWords;
    private int type=0;
    private int pageNum=1;
    private int pageSize=10;
}
