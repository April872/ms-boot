package org.ant.modules.device.service.impl;

import org.ant.modules.device.entity.DeviceDataPoint;
import org.ant.modules.device.mapper.DeviceDataPointMapper;
import org.ant.modules.device.service.IDeviceDataPointService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: device_data_point
 * @Author: ant-boot
 * @Date:   2020-03-16
 * @Version: V1.0
 */
@Service
public class DeviceDataPointServiceImpl extends ServiceImpl<DeviceDataPointMapper, DeviceDataPoint> implements IDeviceDataPointService {

}
