package org.ant.modules.device.service.impl;

import org.ant.modules.device.entity.Device;
import org.ant.modules.device.mapper.DeviceMapper;
import org.ant.modules.device.service.IDeviceService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 设备基本信息
 * @Author: ant-boot
 * @Date:   2020-03-02
 * @Version: V1.0
 */
@Service
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device> implements IDeviceService {

}
