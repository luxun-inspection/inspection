package com.lh.winxinapp.controller;



import com.alibaba.fastjson.JSON;
import com.lh.baseconfig.domain.UserInfo;
import com.lh.baseconfig.service.UserInfoService;
import com.lh.common.core.controller.BaseController;
import com.lh.common.core.domain.AjaxResult;
import com.lh.common.core.domain.entity.SysUser;
import com.lh.common.core.domain.model.LoginUser;
import com.lh.common.utils.SecurityUtils;
import com.lh.system.service.ISysUserService;
import io.swagger.annotations.ApiOperation;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.Security;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * 小程序端接口
 * @author hotpower
 * @date 2021-07-21
 */

@RestController
@RequestMapping("/api/wxAuthorization")
public class WxAuthorizationController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(WxAuthorizationController.class);

    private String appId;
    private String appSecret;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private ISysUserService sysUserService;





    /**
     * 更新用户信息
     */
    @ApiOperation(value = "获取用户授权")
    @PostMapping("/updateUserInfo")
    @ResponseBody
    public AjaxResult updateUserInfo(@RequestBody UserInfo userInfo){
        LoginUser user= SecurityUtils.getLoginUser();
        UserInfo userInfo1 = userInfoService.getUserInfoById(user.getUserId());
        userInfo1.setNickName(userInfo.getNickName());
        userInfo1.setAvatar(userInfo.getAvatar());
        int result = userInfoService.updateUserInfo(userInfo1);
        if(result > 0){
            return AjaxResult.success("更新成功!");
        }
        return AjaxResult.success("更新成功!");
    }

    @ApiOperation(value = "申请认证")
    @PostMapping("/userInfoAuthApply")
    @ResponseBody
    public AjaxResult userInfoAuthApply(@RequestBody UserInfo userInfo){
        LoginUser user= SecurityUtils.getLoginUser();
        UserInfo userInfo1 = userInfoService.getUserInfoById(user.getUserId());
        if("02".equals(userInfo1.getAuthStatus())){
            return AjaxResult.error("您已经认证通过");
        }
        userInfo1.setUserName(userInfo.getUserName());
        userInfo1.setPhone(userInfo.getPhone());
        userInfo1.setAddress(userInfo.getAddress());
        userInfo1.setAuthStatus("02");
        int result = userInfoService.updateUserInfo(userInfo1);
        if(result > 0){
            return AjaxResult.success("申请成功!");
        }
        return AjaxResult.success("申请成功!");
    }

    //获取手机号码
    @ApiOperation(value = "解析手机号")
    @PostMapping("/savePhoneNumber")
    @ResponseBody
    public AjaxResult savePhoneNumber(@RequestBody UserInfo userInfo)
    {
        LoginUser user= SecurityUtils.getLoginUser();
        String encryptedData = userInfo.getEncryptedData();
        String iv = userInfo.getIv();
        String  sessionKey = null;
        //获取当前用户的sessionKey的值
        UserInfo userInfo1 = userInfoService.getUserInfoById(user.getUserId());
        if(userInfo1!=null){
            sessionKey = userInfo1.getSessionKey();
        }
        String obj=getPhoneNumber(sessionKey,encryptedData,iv);//解密电话号码
        //获取手机号码
        com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(obj);
        String phoneNumber = jsonObject.getString("phoneNumber");
        //保存用户手机号码
        userInfo1.setPhone(phoneNumber);
        //查询后台人员手机号,判断当前用户的级别
        SysUser sysUser = sysUserService.selectSysUserByPhone(phoneNumber);
        if(sysUser!=null){
            userInfo1.setUserType("3");
        }
        userInfoService.updateUserInfo(userInfo1);
        Map map = new HashMap();
        map.put("phone",phoneNumber);
        return AjaxResult.success(map);
    }


    public String getPhoneNumber(String session_key, String encryptedData, String iv) {
        byte[] dataByte = cn.hutool.core.codec.Base64.decode(encryptedData);

        byte[] keyByte = cn.hutool.core.codec.Base64.decode(session_key);

        byte[] ivByte = cn.hutool.core.codec.Base64.decode(iv);
        try {

            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
            // 初始化
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, "UTF-8");
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }


}
