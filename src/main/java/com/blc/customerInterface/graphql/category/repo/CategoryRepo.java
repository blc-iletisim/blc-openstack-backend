package com.blc.customerInterface.graphql.category.repo;

import com.blc.customerInterface.graphql.category.domain.Category;
import com.blc.customerInterface.lib.dao.repo.BaseRepo;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepo extends BaseRepo<Category> {
    Optional<Category> findByName(String deneme);
}
