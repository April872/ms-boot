package org.ant.modules.demo.test.service;

import org.jeecg.common.system.base.service.IBaseService;
import org.ant.modules.demo.test.entity.BaseDemo;

import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * @Description: jeecg 测试demo
 * @Author: ant-boot
 * @Date:  2018-12-29
 * @Version: V1.0
 */
public interface IJeecgDemoService extends IBaseService<BaseDemo> {
	
	public void testTran();
	
	public BaseDemo getByIdCacheable(String id);
	
	/**
	 * 查询列表数据 在service中获取数据权限sql信息
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	IPage<BaseDemo> queryListWithPermission(int pageSize, int pageNo);
}
