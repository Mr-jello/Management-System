package top.mrjello.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.mrjello.pojo.Result;
import top.mrjello.utils.AliOSSUtils;

import java.io.IOException;

/**
 * @author jason@mrjello.top
 * @date 2023/7/2 19:15
 */
@Slf4j
@RestController
public class UploadController {

    @Autowired
    private AliOSSUtils aliOssUtils;

    /**
     * 上传员工头像
     * @param image 头像图片
     */
    @PostMapping("/upload")
    public Result uploadEmpImage(MultipartFile image) throws Exception {
        log.info("上传员工头像，image: {}", image.getOriginalFilename());
        //调用oss工具类方法上传员工头像
        String url = aliOssUtils.upload(image);
        log.info("上传员工头像成功，url: {}", url);
        return Result.success(url);



    }
}
