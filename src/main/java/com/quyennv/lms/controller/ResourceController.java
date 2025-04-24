package com.quyennv.lms.controller;

import com.quyennv.lms.annotations.CurrentUser;
import com.quyennv.lms.dto.BaseResponse;
import com.quyennv.lms.dto.resource.GetResourceResponse;
import com.quyennv.lms.entities.Resource;
import com.quyennv.lms.security.UserPrincipal;
import com.quyennv.lms.service.BaseService;
import com.quyennv.lms.service.ResourceService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/resources")
public class ResourceController {

    private final BaseService baseService;

    private final ResourceService resourceService;

    public ResourceController(BaseService baseService, ResourceService resourceService) {
        this.baseService = baseService;
        this.resourceService = resourceService;
    }

    @PostMapping()
    public BaseResponse<Resource> uploadResource(
            @CurrentUser UserPrincipal requester,
            @RequestParam("file") MultipartFile file
    ) {
        return baseService.ofSucceeded(resourceService.uploadResource(file, requester));
    }

    @GetMapping("/{id}")
    public BaseResponse<GetResourceResponse> getResource(
            @CurrentUser UserPrincipal requester,
            @PathVariable String id
    ) {
        return baseService.ofSucceeded(resourceService.getResource(id, requester));
    }

}
