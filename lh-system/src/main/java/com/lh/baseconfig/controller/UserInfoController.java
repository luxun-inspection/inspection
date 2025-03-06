package com.lh.baseconfig.controller;

import com.lh.baseconfig.domain.HomePage;
import com.lh.baseconfig.domain.Permission;
import com.lh.baseconfig.domain.UserInfo;
import com.lh.baseconfig.service.HomePageService;
import com.lh.baseconfig.service.PermissionService;
import com.lh.baseconfig.service.PostPermissionService;
import com.lh.baseconfig.service.UserInfoService;
import com.lh.common.annotation.Log;
import com.lh.common.constant.UserConstants;
import com.lh.common.core.controller.BaseController;
import com.lh.common.core.domain.AjaxResult;
import com.lh.common.core.domain.entity.SysUser;
import com.lh.common.core.page.TableDataInfo;
import com.lh.common.enums.BusinessType;
import com.lh.common.utils.SecurityUtils;
import com.lh.common.utils.StringUtils;
import com.lh.system.domain.SysPost;
import com.lh.system.service.ISysPostService;
import com.lh.system.service.ISysRoleService;
import com.lh.system.service.ISysUserService;
import com.lh.workorder.domain.WorkOrder;
import com.lh.workorder.service.WorkOrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/ht/userInfo")
public class UserInfoController extends BaseController {

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private WorkOrderService workOrderService;
    @Autowired
    private HomePageService homePageService;
    @Autowired
    private ISysUserService userService;
    @Autowired
    private ISysRoleService roleService;
    @Autowired
    private ISysPostService postService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private PostPermissionService postPermissionService;
    @Autowired
    private ISysPostService sysPostService;

    @ApiOperation(value = "获取用户列表")
    @GetMapping("/list")
    public TableDataInfo list(UserInfo userInfo) {
        startPage();
        List<UserInfo> list = userInfoService.getUserInfoByParms(userInfo);
        list.stream().forEach(userInfo1 -> {
            Long userId = userInfo1.getId();
            int number = workOrderService.getOrderNumberByUserId(userId);
            userInfo1.setOrderNumber(number);
        });
        return getDataTable(list);
    }

    @ApiOperation(value = "获取工单记录")
    @GetMapping("/getWorkOrderRecord")
    public AjaxResult getWorkOrderRecord(WorkOrder workOrder) {
        //查询当前用户所属的工单记录
        List<WorkOrder> workOrderList = workOrderService.getCustomerWorkOrder(workOrder.getId());
        workOrderList.stream().forEach(workOrder1 -> {
            String engineerId = workOrder1.getEngineerId();
            String[] engineerIds = null;
            if (engineerId != null && !"".equals(engineerId)) {
                engineerIds = engineerId.split(",");
                //查询工程师名字
                List<String> stringList = userInfoService.getEngineerName(engineerIds);
                String str = StringUtils.join(stringList, ",");
                workOrder.setEngineerNameList(str);
            }
        });
        return AjaxResult.success(workOrderList);
    }

    @ApiOperation(value = "获取用户列表")
    @GetMapping("/getSysUserList")
    public TableDataInfo getSysUserList(SysUser user) {
        startPage();
        List<SysUser> list = userService.selectSysUserListByPostId(user);
        list.stream().forEach(sysUser -> {
            Long userId = sysUser.getUserId();
            SysPost sysPost = postService.selectPostNameByUserId(userId);
            if (sysPost != null) {
                sysUser.setPostName(sysPost.getPostName());
                sysUser.setPostId(sysPost.getPostId());
            }
        });
        return getDataTable(list);
    }

    /**
     * 新增用户
     */
    @ApiOperation(value = "新增用户")
    @PostMapping("/insertSysUser")
    public AjaxResult add(@Validated @RequestBody SysUser user) {
        if (UserConstants.NOT_UNIQUE.equals(userService.checkUserNameUnique(user.getUserName()))) {
            return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，登录账号已存在");
        } else if (StringUtils.isNotEmpty(user.getPhonenumber())
                && UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user))) {
            return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，手机号码已存在");
        } else if (StringUtils.isNotEmpty(user.getEmail())
                && UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user))) {
            return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }
        user.setNickName(user.getPhonenumber());
        user.setCreateBy(getUsername());
        user.setPassword(SecurityUtils.encryptPassword("123456"));
        userService.insertUserAndPost(user);
        return AjaxResult.success();
    }

    /**
     * 修改用户
     */
    @ApiOperation(value = "更新用户信息")
    @PostMapping("/updateSysUser")
    public AjaxResult edit(@Validated @RequestBody SysUser user) {
        userService.checkUserAllowed(user);
        if (StringUtils.isNotEmpty(user.getPhonenumber())
                && UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user))) {
            return AjaxResult.error("修改用户'" + user.getUserName() + "'失败，手机号码已存在");
        }

        return toAjax(userService.updateSysUser(user));
    }

    /**
     * 重置密码
     */
    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
    @PostMapping("/updatePwd")
    public AjaxResult updatePwd(@RequestBody SysUser sysUser) {
        Long userId = sysUser.getUserId();
        String password = "123456";
        if (userService.resetPwd(userId, SecurityUtils.encryptPassword(password)) > 0) {
            return AjaxResult.success("密码更新成功!");
        }
        return AjaxResult.error("修改密码异常，请联系管理员");
    }

    @ApiOperation(value = "获取岗位")
    @GetMapping("/getPostList")
    public TableDataInfo getPostList(SysPost sysPost) {
        sysPost.setStatus("0");
        List<SysPost> sysPostList = postService.selectPostList(sysPost);
        sysPostList.stream().forEach(sysPost1 -> {
            Long postId = sysPost1.getPostId();
            List<Permission> permissionList = permissionService.getPermissionListByPostId(postId);
            sysPost1.setPermissionList(permissionList);
        });
        return getDataTable(sysPostList);
    }

    @ApiOperation(value = "获取岗位详情")
    @GetMapping("/getPostDetail")
    public AjaxResult getPostDetail(Long postId) {
        List<Permission> permissionList = permissionService.getPermissionListByPostId(postId);
        return AjaxResult.success(permissionList);
    }

    @ApiOperation(value = "岗位的编辑")
    @PostMapping("/updatePost")
    public AjaxResult updatePost(@RequestBody SysPost sysPost) {
        sysPostService.updatePost(sysPost);
        return AjaxResult.success();
    }

    @ApiOperation(value = "岗位的编辑")
    @PostMapping("/insertPost")
    public AjaxResult insertPost(@RequestBody SysPost sysPost) {
        sysPost.setPostCode(sysPost.getPostName());
        //查询sort最大值
        int maxSort = sysPostService.getMaxSort();
        sysPost.setPostSort(String.valueOf(maxSort + 1));
        sysPost.setStatus("0");
        sysPostService.insertPost(sysPost);
        return AjaxResult.success();
    }

    @ApiOperation(value = "岗位的删除")
    @PostMapping("/deletePost")
    public AjaxResult deletePost(@RequestBody SysPost sysPost) {
        sysPostService.deletePostById(sysPost.getPostId());
        return AjaxResult.success();
    }


    @ApiOperation(value = "修改岗位权限")
    @PostMapping("/updatePostAuthority")
    public AjaxResult updatePostAuthority(@RequestBody SysPost sysPost) {
        //判断当前职务在数据库有没有
        String ids = sysPost.getIds();
        Long postId = sysPost.getPostId();
        String postName = sysPost.getPostName();
        SysPost sysPost1 = sysPostService.getSysPost(postName);
        if (sysPost1 != null) {
            sysPostService.updatePost(sysPost);
        } else {
            sysPost.setPostCode(sysPost.getPostName());
            //查询sort最大值
            int maxSort = sysPostService.getMaxSort();
            sysPost.setPostSort(String.valueOf(maxSort + 1));
            sysPost.setStatus("0");
            sysPostService.insertPost(sysPost);
            postId = sysPost.getPostId();
        }

        List<Permission> permissionList = permissionService.getPermissionListByPostId(postId);
        if (ids != null && !"".equals(ids)) {
            postPermissionService.deleteAuthorityByPostId(postId);
            postPermissionService.dealAuthorityByPostId(postId, ids);
        } else {
            //删除所有记录
            postPermissionService.deleteAuthorityByPostId(postId);
        }
        return AjaxResult.success();
    }

    @ApiOperation(value = "获取权限")
    @GetMapping("/getPermission")
    public AjaxResult getPermission() {
        List<Permission> permissionList = permissionService.getAllPermission();
        return AjaxResult.success(permissionList);
    }


    @ApiOperation(value = "轮播图")
    @GetMapping("/getRotationPic")
    public TableDataInfo getRotationPic() {
        startPage();
        List<HomePage> homePageList = homePageService.getRotationPic();
        return getDataTable(homePageList);
    }

    @ApiOperation(value = "编辑轮播图")
    @PostMapping("/updateRotationPic")
    public AjaxResult updateRotationPic(@RequestBody HomePage homePage) {
        homePage.setHomeType("1");
        int result = homePageService.updateHomePage(homePage);
        if (result > 0) {
            return AjaxResult.success();
        }
        return AjaxResult.success();
    }

    @ApiOperation(value = "获取详情")
    @GetMapping("/getHomePageById")
    public AjaxResult getHomePageById(HomePage homePage) {
        HomePage homePage1 = homePageService.getHomePageById(homePage.getId());
        return AjaxResult.success(homePage1);
    }


    @ApiOperation(value = "新增轮播图")
    @PostMapping("/insertRotationPic")
    public AjaxResult insertRotationPic(@RequestBody HomePage homePage) {
        homePage.setHomeType("1");
        homePage.setCreateTime(new Date());
        int result = homePageService.insertHomePage(homePage);
        if (result > 0) {
            return AjaxResult.success("新增成功!");
        }
        return AjaxResult.success();
    }

    @ApiOperation(value = "新增Bannner图")
    @PostMapping("/insertBanner")
    public AjaxResult insertBanner(@RequestBody HomePage homePage) {
        homePage.setHomeType("2");
        homePage.setCreateTime(new Date());
        int result = homePageService.insertHomePage(homePage);
        if (result > 0) {
            return AjaxResult.success("新增成功!");
        }
        return AjaxResult.success();
    }

    @ApiOperation(value = "编辑banner")
    @PostMapping("/updateBanner")
    public AjaxResult updateBanner(@RequestBody HomePage homePage) {
        homePage.setHomeType("2");
        int result = homePageService.updateHomePage(homePage);
        if (result > 0) {
            return AjaxResult.success("更新成功!");
        }
        return AjaxResult.success();
    }

    @ApiOperation(value = "获取banner列表")
    @GetMapping("/getBannerList")
    public TableDataInfo getBannerList() {
        startPage();
        List<HomePage> homePageList = homePageService.getHomePageBanner();
        return getDataTable(homePageList);
    }

    @ApiOperation(value = "修改banner排列位置")
    @PostMapping("/updateBannerPosition")
    public AjaxResult updateBannerPosition(@RequestBody HomePage homePage) {
        String ids = homePage.getIds();
        if (ids != null && !"".equals(ids)) {
            String[] idList = ids.split(",");
            for (int i = 0; i < idList.length; i++) {
                String id = idList[i];
                HomePage homePage1 = homePageService.getHomePageById(Long.valueOf(id));
                homePage1.setSort(i + 1);
                homePageService.updateHomePage(homePage1);
            }
        }
        return AjaxResult.success();
    }

    @ApiOperation(value = "删除banner")
    @PostMapping("/deleteHomePage")
    public AjaxResult deleteHomePage(@RequestBody HomePage homePage) {
        int result = homePageService.deleteHomePage(homePage.getId());
        if (result > 0) {
            return AjaxResult.success("删除成功!");
        }
        return AjaxResult.success();
    }
}
