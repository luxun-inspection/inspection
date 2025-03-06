package com.lh.weixinweb.controller.req;

import lombok.Data;

@Data
public class CreateQRCodeRequest {

    /**
     * 扫码进入的小程序页面路径
     */
    private String path;

    /**
     * 二维码的宽度，单位 px。最小 280px，最大 1280px;默认是430
     */
    private Integer width;
}
