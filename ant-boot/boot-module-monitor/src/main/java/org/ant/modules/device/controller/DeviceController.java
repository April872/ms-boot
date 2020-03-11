package org.ant.modules.device.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.BaseController;
import org.jeecg.common.system.query.QueryGenerator;
import org.ant.modules.device.entity.Device;
import org.ant.modules.device.service.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

;

 /**
 * @Description: 设备基本信息
 * @Author: ant-boot
 * @Date:   2020-03-02
 * @Version: V1.0
 */
@Slf4j
@Api(tags="设备基本信息")
@RestController
@RequestMapping("/device")
public class DeviceController extends BaseController<Device, IDeviceService> {
	@Autowired
	private IDeviceService deviceService;
	
	/**
	 * 分页列表查询
	 *
	 * @param device
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "设备基本信息-分页列表查询")
	@ApiOperation(value="设备基本信息-分页列表查询", notes="设备基本信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Device device,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Device> queryWrapper = QueryGenerator.initQueryWrapper(device, req.getParameterMap());
		Page<Device> page = new Page<Device>(pageNo, pageSize);
		IPage<Device> pageList = deviceService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param device
	 * @return
	 */
	@AutoLog(value = "设备基本信息-添加")
	@ApiOperation(value="设备基本信息-添加", notes="设备基本信息-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Device device) {
		deviceService.save(device);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param device
	 * @return
	 */
	@AutoLog(value = "设备基本信息-编辑")
	@ApiOperation(value="设备基本信息-编辑", notes="设备基本信息-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Device device) {
		deviceService.updateById(device);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "设备基本信息-通过id删除")
	@ApiOperation(value="设备基本信息-通过id删除", notes="设备基本信息-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		deviceService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "设备基本信息-批量删除")
	@ApiOperation(value="设备基本信息-批量删除", notes="设备基本信息-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.deviceService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "设备基本信息-通过id查询")
	@ApiOperation(value="设备基本信息-通过id查询", notes="设备基本信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Device device = deviceService.getById(id);
		return Result.ok(device);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param device
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Device device) {
      return super.exportXls(request, device, Device.class, "设备基本信息");
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
      return super.importExcel(request, response, Device.class);
  }

}
