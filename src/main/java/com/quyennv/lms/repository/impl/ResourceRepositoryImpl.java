package com.quyennv.lms.repository.impl;

import com.quyennv.lms.entities.Resource;
import com.quyennv.lms.mappers.ResourceMapper;
import com.quyennv.lms.repository.ResourceRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class ResourceRepositoryImpl implements ResourceRepository {

    private final ResourceMapper resourceMapper;

    public ResourceRepositoryImpl(ResourceMapper resourceMapper) {
        this.resourceMapper = resourceMapper;
    }

    @Override
    public int save(Resource resource) {
        resource.setId(UUID.randomUUID());
        return resourceMapper.insert(resource);
    }

    @Override
    public Resource findById(UUID id) {
        return resourceMapper.selectByPrimaryKey(id);
    }
}
