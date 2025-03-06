package com.lh.common.utils;

import com.lh.common.core.domain.AjaxResult;
import com.lh.common.core.redis.RedisCache;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;


/**
 * @author 蜗奇卧龙在此
 * @date 2020/10/9 14:45
 */
public class SMSCode {

    public static String dispatchMsg="【供热壹院】您好！您有一份系统工单请及时处理，工单号为：";



    public static AjaxResult sunHaosendSMS(RedisCache redisCache, String phone, String temlate)throws Exception {
        AjaxResult ajaxResult=AjaxResult.success();
        Integer code = (int) ((Math.random() * 9 + 1) * 100000);//生成6位随机数
        String postData = "uid=9407&pw=F9D6C5E3&mb="+phone+"&ms="+java.net.URLEncoder.encode("【"+temlate+"】验证码为："+code+",您正在进行登录校验，切勿将验证码泄露于他人，验证码10分钟内有效。","utf-8");

        String postUrl="http://59.110.24.249:8090/send.do";

        try {

            //发送POST请求
            URL url = new URL(postUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setUseCaches(false);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Length", "" + postData.length());
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            out.write(postData);
            out.flush();
            out.close();
            //获取响应状态
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                ajaxResult=AjaxResult.error("连接失败");
                return ajaxResult;
            }
            //获取响应内容体
            String line, result = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            while ((line = in.readLine()) != null) {
                result += line + "\n";
            }
            in.close();
            System.out.println("connect success!");
            String key = "phone_verify_code" + phone;
            redisCache.setCacheObject(key, code,600, TimeUnit.SECONDS);
            return ajaxResult;
        } catch (IOException e) {
            e.printStackTrace(System.out);
            ajaxResult=AjaxResult.error(e.getMessage());
        }
        return ajaxResult;
    }


    public static AjaxResult sunHaosendNoticeSMS(String phone, String temlate)throws Exception {
        AjaxResult ajaxResult=AjaxResult.success();
        String postData = "uid=9407&pw=F9D6C5E3&mb="+phone+"&ms="+java.net.URLEncoder.encode(temlate,"utf-8");

       // Integer code = (int) ((Math.random() * 9 + 1) * 100000);//生成6位随机数
        //String postData = "uid=13416&pw=A3E7D3A3&mb="+phone+"&ms="+java.net.URLEncoder.encode("【"+temlate+"】短信验证码为："+code+"。此验证码只用于验证，5分钟内有效","utf-8");


        String postUrl="http://59.110.24.249:8090/send.do";

        try {

            //发送POST请求
            URL url = new URL(postUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setUseCaches(false);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Length", "" + postData.length());
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            out.write(postData);
            out.flush();
            out.close();
            //获取响应状态
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                ajaxResult=AjaxResult.error("连接失败");
                return ajaxResult;
            }
            //获取响应内容体
            String line, result = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            while ((line = in.readLine()) != null) {
                result += line + "\n";
            }
            in.close();
            System.out.println("connect success!");
            return ajaxResult;
        } catch (IOException e) {
            e.printStackTrace(System.out);
            ajaxResult=AjaxResult.error(e.getMessage());
        }
        return ajaxResult;
    }



}
