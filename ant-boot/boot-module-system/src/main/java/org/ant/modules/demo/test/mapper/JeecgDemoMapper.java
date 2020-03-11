package org.ant.modules.demo.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.ant.modules.demo.test.entity.BaseDemo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @Description: jeecg 测试demo
 * @Author: ant-boot
 * @Date:  2018-12-29
 * @Version: V1.0
 */
public interface JeecgDemoMapper extends BaseMapper<BaseDemo> {

	public List<BaseDemo> getDemoByName(@Param("name") String name);
	
	/**
	 * 查询列表数据 直接传数据权限的sql进行数据过滤
	 * @param page
	 * @param permissionSql
	 * @return
	 */
	public IPage<BaseDemo> queryListWithPermission(Page<BaseDemo> page, @Param("permissionSql")String permissionSql);

}
