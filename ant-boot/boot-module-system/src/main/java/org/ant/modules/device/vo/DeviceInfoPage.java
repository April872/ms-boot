package org.ant.modules.device.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.ant.modules.device.entity.DeviceDataPoint;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;

/**
 * @Description: device_info
 * @Author: ant-boot
 * @Date:   2020-03-12
 * @Version: V1.0
 */
@Data
@ApiModel(value="device_infoPage对象", description="device_info")
public class DeviceInfoPage {
	
	/**ID*/
	@ApiModelProperty(value = "ID")
	private java.lang.String id;
	/**设备编号*/
	@Excel(name = "设备编号", width = 15)
	@ApiModelProperty(value = "设备编号")
	private java.lang.String deviceId;
	/**设备名称*/
	@Excel(name = "设备名称", width = 15)
	@ApiModelProperty(value = "设备名称")
	private java.lang.String deviceName;
	/**所在位置*/
	@Excel(name = "所在位置", width = 15)
	@ApiModelProperty(value = "所在位置")
	private java.lang.String location;
	/**设备类型*/
	@Excel(name = "设备类型", width = 15)
	@ApiModelProperty(value = "设备类型")
	private java.lang.String deviceType;
	/**设备图片*/
	@Excel(name = "设备图片", width = 15)
	@ApiModelProperty(value = "设备图片")
	private java.lang.String deviceImage;
	/**所属部门*/
	@Excel(name = "所属部门", width = 15)
	@ApiModelProperty(value = "所属部门")
	private java.lang.String depatmentId;
	/**备注*/
	@Excel(name = "备注", width = 15)
	@ApiModelProperty(value = "备注")
	private java.lang.String mark;
	/**状态*/
	@Excel(name = "状态", width = 15)
	@ApiModelProperty(value = "状态")
	private java.lang.String status;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
	@ApiModelProperty(value = "创建人")
	private java.lang.String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "创建时间")
	private java.util.Date createTime;
	/**修改人*/
	@Excel(name = "修改人", width = 15)
	@ApiModelProperty(value = "修改人")
	private java.lang.String updateBy;
	/**修改时间*/
	@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "修改时间")
	private java.util.Date updateTime;
	
	@ExcelCollection(name="device_data_point")
	@ApiModelProperty(value = "device_data_point")
	private List<DeviceDataPoint> deviceDataPointList;
	
}
