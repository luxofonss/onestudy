package com.quyennv.lms.repository;

import com.quyennv.lms.entities.Resource;

import java.util.UUID;

public interface ResourceRepository {

    int save(Resource resource);

    Resource findById(UUID id);

}
