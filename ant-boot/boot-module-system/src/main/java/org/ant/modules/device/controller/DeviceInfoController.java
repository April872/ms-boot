package org.ant.modules.device.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.ant.modules.device.entity.DeviceInfo;
import org.ant.modules.device.service.IDeviceInfoService;
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
 * @Description: device_info
 * @Author: ant-boot
 * @Date:   2020-03-12
 * @Version: V1.0
 */
@Api(tags="device_info")
@RestController
@RequestMapping("/device/deviceInfo")
@Slf4j
public class DeviceInfoController extends BaseController<DeviceInfo, IDeviceInfoService> {
	@Autowired
	private IDeviceInfoService deviceInfoService;


	/**
	 * 分页列表查询
	 *
	 * @param deviceInfo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "device_info-分页列表查询")
	@ApiOperation(value="device_info-分页列表查询", notes="device_info-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(DeviceInfo deviceInfo,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<DeviceInfo> queryWrapper = QueryGenerator.initQueryWrapper(deviceInfo, req.getParameterMap());
		Page<DeviceInfo> page = new Page<DeviceInfo>(pageNo, pageSize);
		IPage<DeviceInfo> pageList = deviceInfoService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 查询数据 查出所有设备数据格式响应给前端
	 *
	 * @return
	 */
	@AutoLog(value = "device_info-列表查询")
	@ApiOperation(value="device_info-列表查询", notes="device_info-列表查询")
	@RequestMapping(value = "/queryList", method = RequestMethod.GET)
	public Result<List<DeviceInfo>> queryList() {
		Result<List<DeviceInfo>> result = new Result<>();
		try {
			List<DeviceInfo> list = deviceInfoService.list();
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
	 * @param deviceInfo
	 * @return
	 */
	@AutoLog(value = "device_info-添加")
	@ApiOperation(value="device_info-添加", notes="device_info-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody DeviceInfo deviceInfo) {
		deviceInfoService.save(deviceInfo);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param deviceInfo
	 * @return
	 */
	@AutoLog(value = "device_info-编辑")
	@ApiOperation(value="device_info-编辑", notes="device_info-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody DeviceInfo deviceInfo) {
		deviceInfoService.updateById(deviceInfo);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "device_info-通过id删除")
	@ApiOperation(value="device_info-通过id删除", notes="device_info-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		deviceInfoService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "device_info-批量删除")
	@ApiOperation(value="device_info-批量删除", notes="device_info-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.deviceInfoService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "device_info-通过id查询")
	@ApiOperation(value="device_info-通过id查询", notes="device_info-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		DeviceInfo deviceInfo = deviceInfoService.getById(id);
		if(deviceInfo==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(deviceInfo);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param deviceInfo
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, DeviceInfo deviceInfo) {
        return super.exportXls(request, deviceInfo, DeviceInfo.class, "device_info");
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
        return super.importExcel(request, response, DeviceInfo.class);
    }

}
