package com.sunchaser.uploader.core.support;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author sunchaser admin@lilu.org.cn
 * @since JDK8 2021/10/22
 */
public interface FileUriGenerator {

    String generateFileUri(MultipartFile multipartFile, FileNameGenerator fileNameGenerator) throws Exception;
}
