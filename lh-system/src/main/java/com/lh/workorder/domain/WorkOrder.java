package com.lh.workorder.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lh.baseconfig.domain.UserInfo;
import com.lh.baseconfig.domain.WorkOrderEngineer;
import com.lh.common.core.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class WorkOrder extends BaseEntity {
    private static final long serialVersionUID = -9041650004782151763L;

    private Long id;

    private int categoryId;

    /**
     * 问题种类（词典）
     */
    private String problemCategory;

    /**
     *问题描述
     */
    private String problemDescribe;

    /**
     * 上传图片
     */
    private String pic;

    private String pic1;

    private String pic2;

    /**
     * 客户联系电话
     */
    private String phone;

    /**
     * 工单状态(01等待处理 02处理中 03 处理完成)
     */
    private String status;

    /**
     * 联系人
     */
    private String contacts;

    /**
     * 工作单位
     */
    private String workUnit;

    /**
     * 工作单位
     */
    private String workAddress;

    /**
     * 约定的时间
     */
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date appointedTime;

    /**
     * 工程师问题描述
     */
    private String orderDescribe;

    /**
     * 工费
     */
    private BigDecimal price;

    /**
     * 提交人Id
     */
    private Long userId;

    /**
     * 工程师Id
     */
    private String engineerId;

    /**
     * 完成时的图片
     */
    private String finishPic;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date createTime;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date updateTime;

    /**
     * 工单完成时间
     */
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date finishTime;

    /**
     * 参与工程师集合
     */
    private List<UserInfo> userInfoList;

    /**
     * 工单详情
     */
    private List<OrderDetail> orderDetailList;

    /**
     * 当天工单用户信息
     */
    private UserInfo userInfo;

    /**
     * 阅读状态
     */
    private String readStatus;

    /**
     * 客服人员
     */
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private  Long serviceStaff;

    /**
     * 创建时间(string)
     */
    private String createDate;

    /**
     * 工程师名字汇总
     */
    private String engineerNameList;

    /**
     * 工单单号
     */
    private String orderNumber;

    /**
     *实际收款金额
     */
    private BigDecimal actualAmount;

    private String orderType;

    private String dispatchStatus;

    /**
     * 派单电话
     */
    private String dispatchPhone;

    /**
     * 提交人
     */
    private String submitter;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date startTime;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date endTime;

    private int deviceNum;//设备数目
    private String  deviceBrand;//设备品牌
    private double   deviceLimit;//设备额定负荷
    private int  deviceYear;//设备使用年限

    private String dispatchFeedback;
    private String dispatchImg;

    private String  deviceType;//设备类型


    private Long manageUserId;
    private String manageUserName;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date manageDealTime;
    private Long engineerUserId;
    private String engineerUserName;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date engineerDealTime;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date manageConfirmTime;

    private List<WorkOrderEngineer> engineerList;

    private String imageUrl;

    private String serviceName;
    private String serviceDesc;
    private String serviceImage;
    private String manageDealDesc;
    private String manageDealImage;


}
