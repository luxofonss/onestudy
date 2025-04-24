package com.quyennv.lms.service;

import com.quyennv.lms.dto.resource.GetResourceResponse;
import com.quyennv.lms.entities.Resource;
import com.quyennv.lms.security.UserPrincipal;
import org.springframework.web.multipart.MultipartFile;

public interface ResourceService {

    Resource uploadResource(MultipartFile file, UserPrincipal requester);

    GetResourceResponse getResource(String id, UserPrincipal requester);

}
