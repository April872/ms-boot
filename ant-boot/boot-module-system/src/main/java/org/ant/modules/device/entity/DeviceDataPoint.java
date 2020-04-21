package org.ant.modules.device.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * @Description: device_data_point
 * @Author: ant-boot
 * @Date:   2020-03-16
 * @Version: V1.0
 */
@Data
@TableName("device_data_point")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="device_data_point对象", description="device_data_point")
public class DeviceDataPoint implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "id")
    private java.lang.String id;
	/**数据点编号*/
	@Excel(name = "数据点编号", width = 15)
    @ApiModelProperty(value = "数据点编号")
    private java.lang.String dataId;
	/**数据点名称*/
	@Excel(name = "数据点名称", width = 15)
    @ApiModelProperty(value = "数据点名称")
    private java.lang.String dataName;
	/**描述*/
	@Excel(name = "描述", width = 15)
    @ApiModelProperty(value = "描述")
    private java.lang.String mark;
	/**所属数据类型*/
	@Excel(name = "所属数据类型", width = 15, dicCode = "data_type")
	@Dict(dicCode = "data_type")
    @ApiModelProperty(value = "所属数据类型")
    private java.lang.String dataTypeId;
	/**所属设备*/
	@Excel(name = "所属设备", width = 15, dictTable = "device_info", dicText = "device_name", dicCode = "device_id")
	@Dict(dictTable = "device_info", dicText = "device_name", dicCode = "device_id")
    @ApiModelProperty(value = "所属设备")
    private java.lang.String deviceId;
	/**最小阈值*/
	@Excel(name = "最小阈值", width = 15)
    @ApiModelProperty(value = "最小阈值")
    private java.lang.String minValue;
	/**最大阈值*/
	@Excel(name = "最大阈值", width = 15)
    @ApiModelProperty(value = "最大阈值")
    private java.lang.String maxValue;
	/**单位*/
	@Excel(name = "单位", width = 15, dicCode = "unit")
	@Dict(dicCode = "unit")
    @ApiModelProperty(value = "单位")
    private java.lang.String unit;
	/**最大阈值*/
	@Excel(name = "当前值", width = 15)
	@ApiModelProperty(value = "当前值")
	private java.lang.String crnValue;
	/**故障分析*/
	@Excel(name = "故障分析", width = 15)
    @ApiModelProperty(value = "故障分析")
    private java.lang.String causes;
	/**状态(0-正常,1-告警)*/
	@Excel(name = "状态(0-正常,1-告警)", width = 15)
    @ApiModelProperty(value = "状态(0-正常,1-告警)")
    private java.lang.Integer status;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
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
}
