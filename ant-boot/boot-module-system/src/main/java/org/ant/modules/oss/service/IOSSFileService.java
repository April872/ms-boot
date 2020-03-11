package org.ant.modules.oss.service;

import java.io.IOException;

import com.baomidou.mybatisplus.extension.service.IService;
import org.ant.modules.oss.entity.OSSFile;
import org.springframework.web.multipart.MultipartFile;

public interface IOSSFileService extends IService<OSSFile> {

	void upload(MultipartFile multipartFile) throws IOException;

	boolean delete(OSSFile ossFile);

}
