package org.ant.modules.eyeah.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.ant.modules.eyeah.entity.EyContacter;
import org.ant.modules.ant.service.IEyContacterService;
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
 * @Description: ey_contacter
 * @Author: ant-boot
 * @Date:   2020-04-07
 * @Version: V1.0
 */
@Api(tags="ey_contacter")
@RestController
@RequestMapping("/ant/eyContacter")
@Slf4j
public class EyContacterController extends BaseController<EyContacter, IEyContacterService> {
	@Autowired
	private IEyContacterService eyContacterService;
	
	/**
	 * 分页列表查询
	 *
	 * @param eyContacter
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "ey_contacter-分页列表查询")
	@ApiOperation(value="ey_contacter-分页列表查询", notes="ey_contacter-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(EyContacter eyContacter,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<EyContacter> queryWrapper = QueryGenerator.initQueryWrapper(eyContacter, req.getParameterMap());
		Page<EyContacter> page = new Page<EyContacter>(pageNo, pageSize);
		IPage<EyContacter> pageList = eyContacterService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param eyContacter
	 * @return
	 */
	@AutoLog(value = "ey_contacter-添加")
	@ApiOperation(value="ey_contacter-添加", notes="ey_contacter-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody EyContacter eyContacter) {
		eyContacterService.save(eyContacter);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param eyContacter
	 * @return
	 */
	@AutoLog(value = "ey_contacter-编辑")
	@ApiOperation(value="ey_contacter-编辑", notes="ey_contacter-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody EyContacter eyContacter) {
		eyContacterService.updateById(eyContacter);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "ey_contacter-通过id删除")
	@ApiOperation(value="ey_contacter-通过id删除", notes="ey_contacter-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		eyContacterService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "ey_contacter-批量删除")
	@ApiOperation(value="ey_contacter-批量删除", notes="ey_contacter-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.eyContacterService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "ey_contacter-通过id查询")
	@ApiOperation(value="ey_contacter-通过id查询", notes="ey_contacter-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		EyContacter eyContacter = eyContacterService.getById(id);
		if(eyContacter==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(eyContacter);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param eyContacter
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, EyContacter eyContacter) {
        return super.exportXls(request, eyContacter, EyContacter.class, "ey_contacter");
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
        return super.importExcel(request, response, EyContacter.class);
    }

}
