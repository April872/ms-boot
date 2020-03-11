package org.ant.modules.message.service.impl;

import org.jeecg.common.system.base.service.impl.IBaseServiceImpl;
import org.ant.modules.message.entity.SysMessage;
import org.ant.modules.message.mapper.SysMessageMapper;
import org.ant.modules.message.service.ISysMessageService;
import org.springframework.stereotype.Service;

/**
 * @Description: 消息
 * @Author: ant-boot
 * @Date:  2019-04-09
 * @Version: V1.0
 */
@Service
public class SysMessageServiceImpl extends IBaseServiceImpl<SysMessageMapper, SysMessage> implements ISysMessageService {

}
