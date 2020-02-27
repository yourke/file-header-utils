package com.github.fileheaderutils;

import java.io.File;

import org.apache.commons.io.FilenameUtils;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件头工具类Test
 *
 * @author yuanke
 * @date 2020/2/27 19:37
 */
public class FileHeaderUtilTest {

    private final static Logger logger = LoggerFactory.getLogger(FileHeaderUtilTest.class);


    /**
     * 判断文件头对应的类型是否与扩展名一致
     */
    @Test
    public void testIsConsistentType() {
        //指定文件，github.jpg
        String testRootPath = this.getClass().getResource("/").getPath();
        //原始文件jpg，有效
        File validFile = new File(testRootPath + "static/github.jpg");
        Assert.assertTrue(testSingleIsConsistentType(validFile));
        //变更后缀名后文件png，无效
        File invalidFile = new File(testRootPath + "static/github.png");
        Assert.assertFalse(testSingleIsConsistentType(invalidFile));
    }

    private boolean testSingleIsConsistentType(File file) {
        MultipartFile multipartFile = FileUtil.getMultipartFile(file);
        // 文件头
        String fileHeader = FileHeaderUtil.getFileHeader(multipartFile);
        logger.info("文件头：" + fileHeader);
        // 文件类型
        String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        logger.info("文件类型：" + extension);
        // 文件头是否和类型一致
        return FileHeaderUtil.isConsistentType(multipartFile);
    }
}
