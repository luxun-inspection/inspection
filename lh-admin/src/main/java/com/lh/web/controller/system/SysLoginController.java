package com.lh.web.controller.system;

import java.util.List;
import java.util.Map;
import java.util.Set;

import io.swagger.annotations.ApiOperation;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.lh.common.constant.Constants;
import com.lh.common.core.domain.AjaxResult;
import com.lh.common.core.domain.entity.SysMenu;
import com.lh.common.core.domain.entity.SysUser;
import com.lh.common.core.domain.model.LoginBody;
import com.lh.common.core.domain.model.LoginUser;
import com.lh.common.utils.SecurityUtils;
import com.lh.common.utils.ServletUtils;
import com.lh.framework.web.service.SysLoginService;
import com.lh.framework.web.service.SysPermissionService;
import com.lh.framework.web.service.TokenService;
import com.lh.system.service.ISysMenuService;

/**
 * 登录验证
 *
 * @author lh
 */
@RestController
public class SysLoginController
{
    @Value("${wx.appId}")
    private String appId;
    @Value("${wx.appSecret}")
    private String appSecret;
    @Autowired
    private SysLoginService loginService;

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private TokenService tokenService;

    /**
     * 登录方法
     *
     * @param loginBody 登录信息
     * @return 结果
     */
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody)
    {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                loginBody.getUuid());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public AjaxResult getInfo()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysUser user = loginUser.getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", user);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        return ajax;
    }

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public AjaxResult getRouters()
    {
        Long userId = SecurityUtils.getUserId();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(userId);
        return AjaxResult.success(menuService.buildMenus(menus));
    }

    @ApiOperation(value = "获取用户授权")
    @PostMapping("/getUserAuthorization")
    public AjaxResult getUserAuthorization(@RequestBody String code){
        if(code!=null){
            String [] codes = code.split("code_");
            code = codes[1];
        }
        if (code == null) {
            return AjaxResult.error("code为空");
        }

        //拼接url
        StringBuilder url = new StringBuilder("https://api.weixin.qq.com/sns/jscode2session?");
        url.append("appid=");//appid设置
        url.append(appId);
        url.append("&secret=");//secret设置
        url.append(appSecret);
        url.append("&js_code=");//code设置
        url.append(code);
        url.append("&grant_type=authorization_code");
        Map<String, Object> map = null;
        String content =null;
        String openId = null;
        String sessionKey = null;
        try {
            HttpClient client = HttpClientBuilder.create().build();//构建一个Client
            HttpGet get = new HttpGet(url.toString());    //构建一个GET请求
            HttpResponse response = client.execute(get);//提交GET请求
            HttpEntity ret = response.getEntity();
            content = EntityUtils.toString(ret);
            JSONObject jsonObj = new JSONObject(content);
            openId = jsonObj.getString("openid");
            sessionKey = jsonObj.getString("session_key");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (openId == null) {
            return AjaxResult.error("openid为空");
        }

        // 生成令牌
        Map<String,Object> result = loginService.loginWeiXin(openId,sessionKey);

        return AjaxResult.success(result);
    }

}
