package org.ant.modules.eyeah.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * @Description: ey_enterprises
 * @Author: ant-boot
 * @Date:   2020-04-07
 * @Version: V1.0
 */
@Data
@TableName("ey_enterprises")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ey_enterprises对象", description="ey_enterprises")
public class EyEnterprises implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "id")
    private java.lang.String id;
	/**企业编号*/
	@Excel(name = "企业编号", width = 15)
    @ApiModelProperty(value = "企业编号")
    private java.lang.String entpId;
	/**企业名称*/
	@Excel(name = "企业名称", width = 15)
    @ApiModelProperty(value = "企业名称")
    private java.lang.String entpName;
	/**注册名称*/
	@Excel(name = "注册名称", width = 15)
    @ApiModelProperty(value = "注册名称")
    private java.lang.String entpNameReg;
	/**在用名称*/
	@Excel(name = "在用名称", width = 15)
    @ApiModelProperty(value = "在用名称")
    private java.lang.String entpNameUse;
	/**登记设备点数*/
	@Excel(name = "登记设备点数", width = 15)
    @ApiModelProperty(value = "登记设备点数")
    private java.lang.Integer deviceAmountReg;
	/**在用设备点数*/
	@Excel(name = "在用设备点数", width = 15)
    @ApiModelProperty(value = "在用设备点数")
    private java.lang.Integer deviceAmountUse;
	/**企业税号*/
	@Excel(name = "企业税号", width = 15)
    @ApiModelProperty(value = "企业税号")
    private java.lang.String taxNum;
	/**开户行*/
	@Excel(name = "开户行", width = 15)
    @ApiModelProperty(value = "开户行")
    private java.lang.String bank;
	/**账号*/
	@Excel(name = "账号", width = 15)
    @ApiModelProperty(value = "账号")
    private java.lang.String account;
	/**地址*/
	@Excel(name = "地址", width = 15)
    @ApiModelProperty(value = "地址")
    private java.lang.String address;
	/**邮编*/
	@Excel(name = "邮编", width = 15)
    @ApiModelProperty(value = "邮编")
    private java.lang.String postCode;
	/**电话*/
	@Excel(name = "电话", width = 15)
    @ApiModelProperty(value = "电话")
    private java.lang.String phone;
	/**短信账号*/
	@Excel(name = "短信账号", width = 15)
    @ApiModelProperty(value = "短信账号")
    private java.lang.String smsid;
	/**短信密码*/
	@Excel(name = "短信密码", width = 15)
    @ApiModelProperty(value = "短信密码")
    private java.lang.String smspwd;
	/**当前短信数目*/
	@Excel(name = "当前短信数目", width = 15)
    @ApiModelProperty(value = "当前短信数目")
    private java.lang.Integer smsAmount;
	/**会员数目*/
	@Excel(name = "会员数目", width = 15)
    @ApiModelProperty(value = "会员数目")
    private java.lang.Integer memberAmount;
	/**账户金额*/
	@Excel(name = "账户金额", width = 15)
    @ApiModelProperty(value = "账户金额")
    private java.math.BigDecimal accontMoney;
	/**维保费用*/
	@Excel(name = "维保费用", width = 15)
    @ApiModelProperty(value = "维保费用")
    private java.math.BigDecimal serviceFare;
	/**维保期限*/
	@Excel(name = "维保期限", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "维保期限")
    private java.util.Date servicePeriod;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private java.lang.String remark;
	/**最近登陆时间*/
	@Excel(name = "最近登陆时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "最近登陆时间")
    private java.util.Date latestTime;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
	/**创建日期*/
	@Excel(name = "创建日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
	/**更新人*/
	@Excel(name = "更新人", width = 15)
    @ApiModelProperty(value = "更新人")
    private java.lang.String updateBy;
	/**更新日期*/
	@Excel(name = "更新日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
    private java.util.Date updateTime;
}
