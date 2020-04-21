package org.ant.modules.eyeah.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.ant.modules.eyeah.entity.EyEnterprises;
import org.ant.modules.eyeah.service.IEyEnterprisesService;
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
 * @Description: ey_enterprises
 * @Author: ant-boot
 * @Date:   2020-04-07
 * @Version: V1.0
 */
@Api(tags="ey_enterprises")
@RestController
@RequestMapping("/ant/eyEnterprises")
@Slf4j
public class EyEnterprisesController extends BaseController<EyEnterprises, IEyEnterprisesService> {
	@Autowired
	private IEyEnterprisesService eyEnterprisesService;
	
	/**
	 * 分页列表查询
	 *
	 * @param eyEnterprises
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "ey_enterprises-分页列表查询")
	@ApiOperation(value="ey_enterprises-分页列表查询", notes="ey_enterprises-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(EyEnterprises eyEnterprises,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<EyEnterprises> queryWrapper = QueryGenerator.initQueryWrapper(eyEnterprises, req.getParameterMap());
		Page<EyEnterprises> page = new Page<EyEnterprises>(pageNo, pageSize);
		IPage<EyEnterprises> pageList = eyEnterprisesService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param eyEnterprises
	 * @return
	 */
	@AutoLog(value = "ey_enterprises-添加")
	@ApiOperation(value="ey_enterprises-添加", notes="ey_enterprises-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody EyEnterprises eyEnterprises) {
		eyEnterprisesService.save(eyEnterprises);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param eyEnterprises
	 * @return
	 */
	@AutoLog(value = "ey_enterprises-编辑")
	@ApiOperation(value="ey_enterprises-编辑", notes="ey_enterprises-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody EyEnterprises eyEnterprises) {
		eyEnterprisesService.updateById(eyEnterprises);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "ey_enterprises-通过id删除")
	@ApiOperation(value="ey_enterprises-通过id删除", notes="ey_enterprises-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		eyEnterprisesService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "ey_enterprises-批量删除")
	@ApiOperation(value="ey_enterprises-批量删除", notes="ey_enterprises-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.eyEnterprisesService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "ey_enterprises-通过id查询")
	@ApiOperation(value="ey_enterprises-通过id查询", notes="ey_enterprises-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		EyEnterprises eyEnterprises = eyEnterprisesService.getById(id);
		if(eyEnterprises==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(eyEnterprises);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param eyEnterprises
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, EyEnterprises eyEnterprises) {
        return super.exportXls(request, eyEnterprises, EyEnterprises.class, "ey_enterprises");
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
        return super.importExcel(request, response, EyEnterprises.class);
    }

}
