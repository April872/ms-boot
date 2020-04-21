package org.ant.modules.eyeah.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.ant.modules.eyeah.entity.EyDevice;
import org.ant.modules.ant.service.IEyDeviceService;
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

 /**
 * @Description: ey_device
 * @Author: ant-boot
 * @Date:   2020-04-07
 * @Version: V1.0
 */
@Api(tags="ey_device")
@RestController
@RequestMapping("/ant/eyDevice")
@Slf4j
public class EyDeviceController extends BaseController<EyDevice, IEyDeviceService> {
	@Autowired
	private IEyDeviceService eyDeviceService;
	
	/**
	 * 分页列表查询
	 *
	 * @param eyDevice
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "ey_device-分页列表查询")
	@ApiOperation(value="ey_device-分页列表查询", notes="ey_device-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(EyDevice eyDevice,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<EyDevice> queryWrapper = QueryGenerator.initQueryWrapper(eyDevice, req.getParameterMap());
		Page<EyDevice> page = new Page<EyDevice>(pageNo, pageSize);
		IPage<EyDevice> pageList = eyDeviceService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param eyDevice
	 * @return
	 */
	@AutoLog(value = "ey_device-添加")
	@ApiOperation(value="ey_device-添加", notes="ey_device-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody EyDevice eyDevice) {
		eyDeviceService.save(eyDevice);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param eyDevice
	 * @return
	 */
	@AutoLog(value = "ey_device-编辑")
	@ApiOperation(value="ey_device-编辑", notes="ey_device-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody EyDevice eyDevice) {
		eyDeviceService.updateById(eyDevice);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "ey_device-通过id删除")
	@ApiOperation(value="ey_device-通过id删除", notes="ey_device-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		eyDeviceService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "ey_device-批量删除")
	@ApiOperation(value="ey_device-批量删除", notes="ey_device-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.eyDeviceService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "ey_device-通过id查询")
	@ApiOperation(value="ey_device-通过id查询", notes="ey_device-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		EyDevice eyDevice = eyDeviceService.getById(id);
		if(eyDevice==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(eyDevice);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param eyDevice
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, EyDevice eyDevice) {
        return super.exportXls(request, eyDevice, EyDevice.class, "ey_device");
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
        return super.importExcel(request, response, EyDevice.class);
    }

}
