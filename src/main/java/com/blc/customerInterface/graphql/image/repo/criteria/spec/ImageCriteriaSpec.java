package com.blc.customerInterface.graphql.image.repo.criteria.spec;

import com.blc.customerInterface.graphql.image.domain.Image;
import com.blc.customerInterface.graphql.image.repo.criteria.ImageCriteria;
import com.blc.customerInterface.lib.dao.repo.criteria.spec.BaseCriteriaSpec;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ImageCriteriaSpec extends BaseCriteriaSpec<Image, ImageCriteria> {
    @Override
    public Specification<Image> createForAll(ImageCriteria criteria) {
        return null;
    }
}
