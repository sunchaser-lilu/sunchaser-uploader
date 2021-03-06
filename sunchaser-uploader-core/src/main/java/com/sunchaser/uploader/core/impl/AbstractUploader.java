package com.sunchaser.uploader.core.impl;

import cn.hutool.core.io.FileTypeUtil;
import com.google.common.base.Preconditions;
import com.sunchaser.uploader.core.FileTypeEnum;
import com.sunchaser.uploader.core.Uploader;
import com.sunchaser.uploader.core.support.FileNameGenerator;
import com.sunchaser.uploader.core.support.FileUriGenerator;
import org.springframework.web.multipart.MultipartFile;

/**
 * 抽象文件上传器
 *
 * @author sunchaser admin@lilu.org.cn
 * @since JDK8 2021/10/22
 */
public abstract class AbstractUploader implements Uploader {

    protected FileTypeEnum fileTypeEnum;

    private FileNameGenerator fileNameGenerator;

    private FileUriGenerator fileUriGenerator;

    public void setFileNameGenerator(FileNameGenerator fileNameGenerator) {
        this.fileNameGenerator = fileNameGenerator;
    }

    public void setFileUriGenerator(FileUriGenerator fileUriGenerator) {
        this.fileUriGenerator = fileUriGenerator;
    }

    public void setFileTypeEnum(FileTypeEnum fileTypeEnum) {
        this.fileTypeEnum = fileTypeEnum;
    }

    @Override
    public String upload(MultipartFile multipartFile) throws Exception {
        String type = FileTypeUtil.getType(multipartFile.getInputStream(), multipartFile.getOriginalFilename());
        Preconditions.checkArgument(this.getFileTypeEnum().getFileExtNameList().contains(type), "文件格式有误");
        String fileUri = this.fileUriGenerator.generateFileUri(multipartFile, this.fileNameGenerator);
        return doUpload(multipartFile, fileUri);
    }

    protected abstract String doUpload(MultipartFile multipartFile, String fileUri) throws Exception;
}
