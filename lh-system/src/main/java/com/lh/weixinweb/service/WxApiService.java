package com.lh.weixinweb.service;

import com.lh.weixinweb.controller.req.CreateQRCodeRequest;
import com.lh.weixinweb.controller.req.WxApiQueryRequest;

public interface WxApiService {

    /**
     * 获取token
     *
     * @return
     */
    String getAccessToken();

    String weeklyRetainUrl = "https://api.weixin.qq.com/datacube/getweanalysisappidweeklyretaininfo?access_token=";

    /**
     * 获取用户访问小程序周留存
     *
     * @param request
     * @return
     */
    String getWeeklyRetain(WxApiQueryRequest request);

    String monthlyRetainUrl = "https://api.weixin.qq.com/datacube/getweanalysisappidmonthlyretaininfo?access_token=";

    /**
     * 获取用户访问小程序月留存
     *
     * @param request
     * @return
     */
    String getMonthlyRetain(WxApiQueryRequest request);

    String dailyRetainUrl = "https://api.weixin.qq.com/datacube/getweanalysisappiddailyretaininfo?access_token=";

    /**
     * 获取用户访问小程序日留存
     *
     * @param request
     * @return
     */
    String getDailyRetain(WxApiQueryRequest request);

    String monthlyVisitTrendUrl = "https://api.weixin.qq.com/datacube/getweanalysisappidmonthlyvisittrend?access_token=";

    /**
     * 获取用户访问小程序数据月趋势
     *
     * @param request
     * @return
     */
    String getMonthlyVisitTrend(WxApiQueryRequest request);

    String dailyVisitTrendUrl = "https://api.weixin.qq.com/datacube/getweanalysisappiddailyvisittrend?access_token=";

    /**
     * 获取用户访问小程序数据日趋势
     *
     * @param request
     * @return
     */
    String getDailyVisitTrend(WxApiQueryRequest request);

    String weeklyVisitTrendUrl = "https://api.weixin.qq.com/datacube/getweanalysisappidweeklyvisittrend?access_token=";

    /**
     * 获取用户访问小程序数据周趋势
     *
     * @param request
     * @return
     */
    String getWeeklyVisitTrend(WxApiQueryRequest request);

    String getDailySummaryUrl = "https://api.weixin.qq.com/datacube/getweanalysisappiddailysummarytrend?access_token=";

    /**
     * 获取用户访问小程序数据概况
     *
     * @param request
     * @return
     */
    String getDailySummary(WxApiQueryRequest request);

    String getVisitPageUrl = "https://api.weixin.qq.com/datacube/getweanalysisappidvisitpage?access_token=";

    /**
     * 获取访问页面数据
     *
     * @param request
     * @return
     */
    String getVisitPage(WxApiQueryRequest request);

    String getUserPortraitUrl = "https://api.weixin.qq.com/datacube/getweanalysisappiduserportrait?access_token=";

    /**
     * 获取小程序用户画像分布
     *
     * @param request
     * @return
     */
    String getUserPortrait(WxApiQueryRequest request);

    String getVisitDistributionUrl = "https://api.weixin.qq.com/datacube/getweanalysisappidvisitdistribution?access_token=";

    /**
     * 获取用户小程序访问分布数据
     *
     * @param request
     * @return
     */
    String getVisitDistribution(WxApiQueryRequest request);


    String createQRCodeUrl = "https://api.weixin.qq.com/cgi-bin/wxaapp/createwxaqrcode?access_token=";

    /**
     * 获取小程序二维码
     *
     * @param request
     * @return
     */
    byte[] createQRCode(CreateQRCodeRequest request);

    String getQRCodeUrl = "https://api.weixin.qq.com/wxa/getwxacode?access_token=";

    /**
     * 获取小程序码
     *
     * @param request
     * @return
     */
    byte[] getQRCode(CreateQRCodeRequest request);
}
