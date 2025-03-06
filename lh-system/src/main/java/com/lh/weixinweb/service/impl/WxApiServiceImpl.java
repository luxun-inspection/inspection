package com.lh.weixinweb.service.impl;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.lh.weixinweb.controller.req.CreateQRCodeRequest;
import com.lh.weixinweb.controller.req.WxApiQueryRequest;
import com.lh.weixinweb.service.WxApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

@Service
public class WxApiServiceImpl implements WxApiService {

    @Value("${wx.appId}")
    private String appId;
    @Value("${wx.appSecret}")
    private String appSecret;

    @Autowired
    private RestTemplate restTemplate;

    String getAccessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={0}&secret={1}";

    @Override
    public String getAccessToken() {
        String accessToken = "";
        try {
            String url = MessageFormat.format(getAccessTokenUrl, appId, appSecret);
            String json = HttpUtil.get(url);
            JSONObject object = JSONObject.parseObject(json);
            accessToken = String.valueOf(object.get("access_token"));
        } catch (Exception e) {

        }
        return accessToken;
    }

    @Override
    public String getWeeklyRetain(WxApiQueryRequest request) {
        String accessToken = getAccessToken();
        String url = weeklyRetainUrl + accessToken;
        String result = HttpUtil.post(url, JSONObject.toJSONString(request));
        return result;
    }

    @Override
    public String getMonthlyRetain(WxApiQueryRequest request) {
        String accessToken = getAccessToken();
        String url = monthlyRetainUrl + accessToken;
        String result = HttpUtil.post(url, JSONObject.toJSONString(request));
        return result;
    }

    @Override
    public String getDailyRetain(WxApiQueryRequest request) {
        String accessToken = getAccessToken();
        String url = dailyRetainUrl + accessToken;
        String result = HttpUtil.post(url, JSONObject.toJSONString(request));
        return result;
    }

    @Override
    public String getMonthlyVisitTrend(WxApiQueryRequest request) {
        String accessToken = getAccessToken();
        String url = monthlyVisitTrendUrl + accessToken;
        String result = HttpUtil.post(url, JSONObject.toJSONString(request));
        return result;
    }

    @Override
    public String getDailyVisitTrend(WxApiQueryRequest request) {
        String accessToken = getAccessToken();
        String url = dailyVisitTrendUrl + accessToken;
        String result = HttpUtil.post(url, JSONObject.toJSONString(request));
        return result;
    }

    @Override
    public String getWeeklyVisitTrend(WxApiQueryRequest request) {
        String accessToken = getAccessToken();
        String url = weeklyVisitTrendUrl + accessToken;
        String result = HttpUtil.post(url, JSONObject.toJSONString(request));
        return result;
    }

    @Override
    public String getDailySummary(WxApiQueryRequest request) {
        String accessToken = getAccessToken();
        String url = getDailySummaryUrl + accessToken;
        String result = HttpUtil.post(url, JSONObject.toJSONString(request));
        return result;
    }

    @Override
    public String getVisitPage(WxApiQueryRequest request) {
        String accessToken = getAccessToken();
        String url = getVisitPageUrl + accessToken;
        String result = HttpUtil.post(url, JSONObject.toJSONString(request));
        return result;
    }

    @Override
    public String getUserPortrait(WxApiQueryRequest request) {
        String accessToken = getAccessToken();
        String url = getUserPortraitUrl + accessToken;
        String result = HttpUtil.post(url, JSONObject.toJSONString(request));
        return result;
    }

    @Override
    public String getVisitDistribution(WxApiQueryRequest request) {
        String accessToken = getAccessToken();
        String url = getVisitDistributionUrl + accessToken;
        String result = HttpUtil.post(url, JSONObject.toJSONString(request));
        return result;
    }

    @Override
    public byte[] createQRCode(CreateQRCodeRequest request) {
        String url = createQRCodeUrl + getAccessToken();
        Map<String, Object> map = new HashMap<>();
        map.put("path", request.getPath());
        byte[] bytes = getWechatQrcodeByRestTemplate(url, map);
        return bytes;
    }

    @Override
    public byte[] getQRCode(CreateQRCodeRequest request) {
        String url = getQRCodeUrl + getAccessToken();
        Map<String, Object> map = new HashMap<>();
        map.put("path", request.getPath());
        byte[] bytes = getWechatQrcodeByRestTemplate(url, map);
        return bytes;
    }

    public byte[] getWechatQrcodeByRestTemplate(String url, Map<String, Object> body) {
        return restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(body, null), byte[].class).getBody();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
