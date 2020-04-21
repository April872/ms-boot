package org.ant.modules.device.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.ant.modules.device.entity.DeviceDataPoint;
import org.ant.modules.device.service.IDeviceDataPointService;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.BaseController;
import org.jeecg.common.system.query.QueryGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: device_data_point
 * @Author: ant-boot
 * @Date:   2020-03-16
 * @Version: V1.0
 */
@Api(tags="device_data_point")
@RestController
@RequestMapping("/device/deviceDataPoint")
@Slf4j
public class DeviceDataPointController extends BaseController<DeviceDataPoint, IDeviceDataPointService> {
	@Autowired
	private IDeviceDataPointService deviceDataPointService;
	
	/**
	 * 分页列表查询
	 *
	 * @param deviceDataPoint
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "device_data_point-分页列表查询")
	@ApiOperation(value="device_data_point-分页列表查询", notes="device_data_point-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(DeviceDataPoint deviceDataPoint,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<DeviceDataPoint> queryWrapper = QueryGenerator.initQueryWrapper(deviceDataPoint, req.getParameterMap());
		Page<DeviceDataPoint> page = new Page<DeviceDataPoint>(pageNo, pageSize);
		IPage<DeviceDataPoint> pageList = deviceDataPointService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 查询数据 查出设备当前采集点数据响应给前端
	 *
	 * @return
	 */
	@AutoLog(value = "device_data_point-列表查询")
	@ApiOperation(value="device_data_point-列表查询", notes="device_data_point-列表查询")
	@RequestMapping(value = "/queryList", method = RequestMethod.GET)
	public Result<List<DeviceDataPoint>> queryList() {
		Result<List<DeviceDataPoint>> result = new Result<>();
		try {
			List<DeviceDataPoint> list = deviceDataPointService.list();
			result.setResult(list);
			result.setSuccess(true);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return result;
	}

	/**
	 * 查询数据 查出设备当前采集点数据响应给前端
	 *
	 * @return
	 */
	@AutoLog(value = "device_data_point-按设备列表查询")
	@ApiOperation(value="device_data_point-按设备列表查询", notes="device_data_point-按设备列表查询")
	@RequestMapping(value = "/listByDevice", method = RequestMethod.GET)
	public Result<List<DeviceDataPoint>> listByDevice(@RequestParam(name = "deviceid", required = true) String deviceid) {
		Result<List<DeviceDataPoint>> result = new Result<>();
		LambdaQueryWrapper<DeviceDataPoint> queryWrapper =new QueryWrapper<DeviceDataPoint>().lambda().eq(DeviceDataPoint::getDeviceId, deviceid);
		try {
			List<DeviceDataPoint> list = deviceDataPointService.list(queryWrapper);
			result.setResult(list);
			result.setSuccess(true);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return result;
	}



	/**
	 *   添加
	 *
	 * @param deviceDataPoint
	 * @return
	 */
	@AutoLog(value = "device_data_point-添加")
	@ApiOperation(value="device_data_point-添加", notes="device_data_point-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody DeviceDataPoint deviceDataPoint) {
		deviceDataPointService.save(deviceDataPoint);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param deviceDataPoint
	 * @return
	 */
	@AutoLog(value = "device_data_point-编辑")
	@ApiOperation(value="device_data_point-编辑", notes="device_data_point-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody DeviceDataPoint deviceDataPoint) {
		deviceDataPointService.updateById(deviceDataPoint);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "device_data_point-通过id删除")
	@ApiOperation(value="device_data_point-通过id删除", notes="device_data_point-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		deviceDataPointService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "device_data_point-批量删除")
	@ApiOperation(value="device_data_point-批量删除", notes="device_data_point-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.deviceDataPointService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "device_data_point-通过id查询")
	@ApiOperation(value="device_data_point-通过id查询", notes="device_data_point-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		DeviceDataPoint deviceDataPoint = deviceDataPointService.getById(id);
		if(deviceDataPoint==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(deviceDataPoint);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param deviceDataPoint
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, DeviceDataPoint deviceDataPoint) {
        return super.exportXls(request, deviceDataPoint, DeviceDataPoint.class, "device_data_point");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, DeviceDataPoint.class);
    }

}
