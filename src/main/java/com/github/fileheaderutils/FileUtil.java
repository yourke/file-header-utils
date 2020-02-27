package com.github.fileheaderutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * 文件Util
 *
 * @author yuanke
 * @date 2020/2/27 19:47
 */
public class FileUtil {
    private static final Logger log = LoggerFactory.getLogger(FileUtil.class);

    private static final int BUFFER_SIZE = 8192;

    /**
     * File转MultipartFile
     *
     * @param file 文件对象
     * @return CommonsMultipartFile
     */
    public static MultipartFile getMultipartFile(File file) {
        FileItem fileItem = createFileItem(file);
        return new CommonsMultipartFile(fileItem);
    }

    /**
     * File转FileItem
     *
     * @param file 文件对象
     * @return FileItem
     */
    private static FileItem createFileItem(File file) {
        FileItemFactory factory = new DiskFileItemFactory(16, null);
        String textFieldName = "file";
        FileItem item = factory.createItem(textFieldName, "multipart/form-data", true, file.getName());
        int bytesRead;
        byte[] buffer = new byte[BUFFER_SIZE];
        FileInputStream fis = null;
        OutputStream os = null;
        try {
            fis = new FileInputStream(file);
            os = item.getOutputStream();
            while ((bytesRead = fis.read(buffer, 0, BUFFER_SIZE)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            log.error("createFileItem error!", e);
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                log.error("createFileItem error!", e);
            }

        }
        return item;
    }

}
