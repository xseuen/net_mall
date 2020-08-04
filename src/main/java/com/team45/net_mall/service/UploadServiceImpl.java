package com.team45.net_mall.service;

import com.team45.net_mall.common.domain.ProductWithBLOBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class UploadServiceImpl implements UploadService{
    @Autowired
    ProductService productService;
    public String logUpload(MultipartFile file, Integer id)  {
            //获取文件名
            String fileName = file.getOriginalFilename();
            //获取上传路径
            String filePath = new File("E:/实训/suse/net_mall/src/main/resources/static/front-end/img/upload").getPath()+File.separator + fileName;
            //第一次运行的时候，这个文件所在的目录往往是不存在的，这里需要创建一下目录
            File fileUpload = new File(filePath);
           File fileUpload1 = new File(filePath);
            if(!fileUpload1.exists()){
                fileUpload.mkdirs();
                try {
                    //上传文件到希望的位置
                    file.transferTo(fileUpload);
                    ProductWithBLOBs pro = productService.selectByid(id);
                    pro.setPic("/front-end/img/upload/"+fileName);
                    productService.update(pro);
                    return "上传成功";
                } catch (IOException e) {
                    return "上传失败" ;
                }

            }else {
                ProductWithBLOBs pro = productService.selectByid(id);
                pro.setPic("/front-end/img/upload/"+fileName);
                productService.update(pro);
                return "图片已存在";
            }


    }


}
