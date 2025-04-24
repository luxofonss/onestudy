package com.quyennv.lms.thirdparty.storage;

import com.amazonaws.HttpMethod;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    String generateUrl(String fileName) throws Exception;

    String uploadFile(MultipartFile file);

    String getProviderName();

}
