package org.ant.modules.device.service.impl;

import org.ant.modules.device.entity.DeviceInfo;
import org.ant.modules.device.mapper.DeviceInfoMapper;
import org.ant.modules.device.service.IDeviceInfoService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: device_info
 * @Author: ant-boot
 * @Date:   2020-03-12
 * @Version: V1.0
 */
@Service
public class DeviceInfoServiceImpl extends ServiceImpl<DeviceInfoMapper, DeviceInfo> implements IDeviceInfoService {

}
