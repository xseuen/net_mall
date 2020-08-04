package com.team45.net_mall.service;



import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
     String logUpload(MultipartFile file,Integer id);
}
