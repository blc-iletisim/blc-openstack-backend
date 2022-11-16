package com.blc.customerInterface.graphql.image.service;

import com.blc.customerInterface.graphql.image.domain.Image;
import com.blc.customerInterface.graphql.image.repo.ImageRepo;
import com.blc.customerInterface.graphql.image.repo.criteria.ImageCriteria;
import com.blc.customerInterface.graphql.image.repo.criteria.spec.ImageCriteriaSpec;
import com.blc.customerInterface.lib.dao.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService extends BaseService<Image, ImageRepo, ImageCriteria, ImageCriteriaSpec> {
    @Autowired
    public ImageService(ImageRepo repository, ImageCriteriaSpec criteriaSpec) {
        super(repository, criteriaSpec);
    }
}
