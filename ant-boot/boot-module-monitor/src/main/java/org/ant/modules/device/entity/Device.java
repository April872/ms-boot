package org.ant.modules.device.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 设备基本信息
 * @Author: ant-boot
 * @Date:   2020-03-02
 * @Version: V1.0
 */
@Data
@TableName("device_info")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="device_info对象", description="设备基本信息")
public class Device {
    
	/**设备编号*/
	@Excel(name = "设备编号", width = 15)
    @ApiModelProperty(value = "设备编号")
	private String deviceId;
	/**设备名称*/
	@Excel(name = "设备名称", width = 15)
    @ApiModelProperty(value = "设备名称")
	private String deviceName;
	/**所在位置*/
	@Excel(name = "所在位置", width = 15)
    @ApiModelProperty(value = "所在位置")
	private String location;
	/**deviceType*/
	@Excel(name = "deviceType", width = 15)
    @ApiModelProperty(value = "deviceType")
	private String deviceType;
	/**设备图片*/
	@Excel(name = "设备图片", width = 15)
    @ApiModelProperty(value = "设备图片")
	private String deviceImage;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String mark;
	/**所属部门*/
	@Excel(name = "所属部门", width = 15)
    @ApiModelProperty(value = "所属部门")
	private String depatmentId;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**修改人*/
	@Excel(name = "修改人", width = 15)
    @ApiModelProperty(value = "修改人")
	private String updateBy;
	/**修改时间*/
	@Excel(name = "修改时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "修改时间")
	private Date updateTime;
}
