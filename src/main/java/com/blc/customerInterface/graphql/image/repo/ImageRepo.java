package com.blc.customerInterface.graphql.image.repo;

import com.blc.customerInterface.graphql.image.domain.Image;
import com.blc.customerInterface.lib.dao.repo.BaseRepo;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepo extends BaseRepo<Image> {
}
