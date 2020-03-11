package org.jeecg.common.system.base.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.system.base.entity.BaseEntity;
import org.jeecg.common.system.base.service.IBaseService;

/**
 * @Description: ServiceImpl基类
 * @Author: dangzhenghui@163.com
 * @Date: 2019-4-21 8:13
 * @Version: 1.0
 */
@Slf4j
public class IBaseServiceImpl<M extends BaseMapper<T>, T extends BaseEntity> extends ServiceImpl<M, T> implements IBaseService<T> {

}
