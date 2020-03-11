package org.ant.modules.message.service;

import org.jeecg.common.system.base.service.IBaseService;
import org.ant.modules.message.entity.SysMessageTemplate;

import java.util.List;

/**
 * @Description: 消息模板
 * @Author: ant-boot
 * @Date:  2019-04-09
 * @Version: V1.0
 */
public interface ISysMessageTemplateService extends IBaseService<SysMessageTemplate> {
    List<SysMessageTemplate> selectByCode(String code);
}
