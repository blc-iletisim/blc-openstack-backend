package com.blc.customerInterface.graphql.category.service;

import com.blc.customerInterface.graphql.category.domain.Category;
import com.blc.customerInterface.graphql.category.repo.CategoryRepo;
import com.blc.customerInterface.graphql.category.repo.criteria.CategoryCriteria;
import com.blc.customerInterface.graphql.category.repo.criteria.spec.CategoryCriteriaSpec;
import com.blc.customerInterface.lib.dao.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends BaseService<Category, CategoryRepo, CategoryCriteria, CategoryCriteriaSpec> {
    @Autowired
    public CategoryService(CategoryRepo repository, CategoryCriteriaSpec criteriaSpec) {
        super(repository, criteriaSpec);
    }
}
