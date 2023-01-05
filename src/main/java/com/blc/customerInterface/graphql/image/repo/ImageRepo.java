package com.blc.customerInterface.graphql.image.repo;

import com.blc.customerInterface.graphql.image.domain.Image;
import com.blc.customerInterface.lib.dao.repo.BaseRepo;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepo extends BaseRepo<Image> {
    Optional<Image> findByName(String name);
}
