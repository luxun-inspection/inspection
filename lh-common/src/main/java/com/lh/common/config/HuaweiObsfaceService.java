package com.lh.common.config;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.obs.services.ObsClient;
import com.obs.services.model.PutObjectRequest;
import com.obs.services.model.PutObjectResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 汽车之家接口调用
 * @author MaRongFu
 * @since 2021-03-29
 */
@Service
public class HuaweiObsfaceService {
    @Value("${huaweicloud.obs.end_point}")
    String endPoint;

    @Value("${huaweicloud.obs.accessible_domain_name}")
    String accessibleDomainName;

    @Value("${huaweicloud.obs.access_key_id}")
    String accessKeyId;

    @Value("${huaweicloud.obs.secret_access_key}")
    String secretAccessKey;

    @Value("${huaweicloud.obs.bucket_name}")
    String bucketName;

    @Value("${huaweicloud.obs.local_file_path}")
    String localFilePath;

    @Value("${huaweicloud.obs.cloud_file_path}")
    String cloudFilePath;

    @Value("${huaweicloud.obs.name}")
    String name;

    @Value("${huaweicloud.obs.password}")
    String password;

    @Value("${huaweicloud.obs.tagsNum}")
    String tagsNum;

    public String upload(InputStream input, String fullName)throws Exception {
        // 创建ObsClient实例
        ObsClient obsClient = new ObsClient(accessKeyId, secretAccessKey, endPoint);
        //文件目录
        SimpleDateFormat dateFormat = new SimpleDateFormat("/yyyy/MM/dd");
        //文件名称格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy_MM_dd_HH_mm");
        String fileSufexx=fullName.substring(fullName.lastIndexOf(".")+1);
        //云存储目录位置
        String toFilePath = cloudFilePath+"/"+fileSufexx+dateFormat.format(new Date());
        //存储对象
        String objectName = toFilePath+"/"+fullName;
        File file = new File(localFilePath);
        //用来测试此路径名表示的文件或目录是否存在
        if (!file.exists()) {
            //创建目录
            System.out.print("进入创建目录");
            file.mkdirs();
        }else{

        }
        System.out.print("111");
        PutObjectRequest request = new PutObjectRequest(bucketName, objectName);
        // localfile为待上传的本地文件路径，需要指定到具体的文件名
        request.setFile(new File(localFilePath+"/"+fullName));
        // 上传对象时，设置对象30天后过期
        //request.setExpires(30);
        //设置对象生命周期，本地文件上传
        //obsClient.putObject(request);
        // 设置对象生命周期，本地文件上传
       /* BASE64Decoder decode = new BASE64Decoder();
        byte[] b = null;
        try {
            b = decode.decodeBuffer(content);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        //上传网络流
        PutObjectResult putObjectResult = obsClient.putObject(bucketName, objectName, input);
        return accessibleDomainName+"/"+putObjectResult.getObjectKey();
    }

    public List<Map> getimageTagsList(String fileUrl)throws Exception{
        String token= getToken(name,password,name);
        System.out.println(token);
        String ocr="https://image.cn-north-4.myhuaweicloud.com/v2/0bb3e8c6c20026f12f95c00b8a51bc11/image/tagging";
        String param = "{\"url\":\""+fileUrl+"\",\"language\":\"zh\",\"limit\":"+tagsNum+"}";
        PrintWriter out;
        BufferedReader in = null;
        String response = "";
        List<Map> resultList=new ArrayList<>();
        try {
            //需要请求的url
            URL url = new URL(ocr);
            //打开和URL之间的连接
            URLConnection connection = url.openConnection();
            //设置通用的请求属性，请求头部分
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("X-Auth-Token", token);
            // 发送POST请求必须设置如下两行
            connection.setDoInput(true);
            connection.setDoOutput(true);
            // 建立实际的连接
            connection.connect();
            ///获取URLConnection对象对应的输出流
            out = new PrintWriter(connection.getOutputStream());
            //发送请求参数
            out.write(param);
            //flush输出流的缓冲
            out.flush();
            //获取相应头中的token信息
            //定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                //换行打印获取结果
                response += "\n" + line;
            }
            JSONObject result=JSONObject.parseObject(response);
            JSONObject tagResult= result.getJSONObject("result");
            JSONArray tagArray=tagResult.getJSONArray("tags");
            for(int i=0;i<tagArray.size();i++){
                JSONObject tag=(JSONObject)tagArray.get(i);
                Map store=new HashMap();
                store.put("tagsName",tag.getString("tag"));
                store.put("type",tag.getString("type"));
                resultList.add(store);
            }
            // System.out.println(response);
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        //返回所要用到的token信息
        return resultList;
    }

    public static void main(String[] args) {
        try {
            HuaweiObsfaceService d=new HuaweiObsfaceService();
            System.out.println(d.getimageTagsList(""));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取请求链接中用户的token信息
    public static String getToken(String username,String password,String userName) throws IOException {
        String iam = "https://iam.cn-north-4.myhuaweicloud.com/v3/auth/tokens";
        String param = "{\"auth\":{\"identity\":{\"methods\":[\"password\"],\"password\":{\"user\":{\"name\":\"" + username + "\",\"password\":\"" + password + "\",\"domain\":{\"name\":\"" + userName + "\"}}}},\"scope\":{\"project\":{\"name\":\"cn-north-4\"}}}}";
        PrintWriter out;
        BufferedReader in = null;
        String token = "";
        String response = "";
        try {
            //需要请求的url
            URL url = new URL(iam);
            //打开和URL之间的连接
            URLConnection connection = url.openConnection();
            //设置通用的请求属性，请求头部分
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0");
            connection.setRequestProperty("Content-Type", "application/json");
            // 发送POST请求必须设置如下两行
            connection.setDoInput(true);
            connection.setDoOutput(true);
            // 建立实际的连接
            connection.connect();
            ///获取URLConnection对象对应的输出流
            out = new PrintWriter(connection.getOutputStream());
            //发送请求参数
            out.write(param);
            //flush输出流的缓冲
            out.flush();
            //获取相应头中的token信息
            token = connection.getHeaderField("X-Subject-Token");
            //定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                //换行打印获取结果
                response += "\n" + line;
            }
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
           /* for (String key : map.keySet()) {
                //System.out.println(key + "--->" + map.get(key));
            }*/
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        //返回所要用到的token信息
        return token;
    }

}
