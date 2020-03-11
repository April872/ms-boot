package org.ant.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.ant.modules.system.entity.SysDataSource;
import org.ant.modules.system.mapper.SysDataSourceMapper;
import org.ant.modules.system.service.ISysDataSourceService;
import org.springframework.stereotype.Service;

/**
 * @Description: 多数据源管理
 * @Author: ant-boot
 * @Date: 2019-12-25
 * @Version: V1.0
 */
@Service
public class SysDataSourceServiceImpl extends ServiceImpl<SysDataSourceMapper, SysDataSource> implements ISysDataSourceService {

}
