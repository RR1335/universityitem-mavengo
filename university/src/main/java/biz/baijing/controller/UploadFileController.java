package biz.baijing.controller;

import biz.baijing.AliyunOSSFiles;
import biz.baijing.Result;
import com.aliyuncs.exceptions.ClientException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/upload")
public class UploadFileController {

//    /**
//     * 本地存储文件方式
//     * @param username
//     * @param age
//     * @param image
//     * @return
//     * @throws Exception
//     */
/*    @PostMapping("/upload")
    public Result upload(String username,Integer age,MultipartFile image) throws Exception {
        log.info("上传文件 {},{},{}", username, age, image);

        // 获取文件名
        String ofn = image.getOriginalFilename();
        // 构建唯一文件名 (这部分能通过通用方法解决)

        String ext = ofn.substring(ofn.lastIndexOf("."));
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String dtnow = dateFormat.format(LocalDateTime.now());
        // 文件名为 UUID + 当前时间戳 ，分割符为 - ，扩展名为原文件扩展名
        String newofn = UUID.randomUUID().toString().replace("-","") + '-' + dtnow + ext;

        log.info("新的文件名：{}", newofn);
        // 存储文件到本地
        image.transferTo(new File("/Users/ann/JavaDev/uploadfile/university/" + newofn));

        return Result.success();
    }*/

    @Autowired
    private AliyunOSSFiles aliyunOSSFiles;

    /**
     * 阿里云 OSS 存储文件的方式
     */
    @PostMapping
    public Result upload(MultipartFile image) throws ClientException, IOException {
        log.info("Upload File {}", image.getOriginalFilename());

        String url = aliyunOSSFiles.uploadOSSFile(image);

        log.info("文件上传完成 {}", url);

        return Result.success(url);
    }

}
