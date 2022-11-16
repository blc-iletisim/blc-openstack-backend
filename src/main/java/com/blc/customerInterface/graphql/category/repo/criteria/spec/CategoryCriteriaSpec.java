package com.blc.customerInterface.graphql.category.repo.criteria.spec;

import com.blc.customerInterface.graphql.category.domain.Category;
import com.blc.customerInterface.graphql.category.repo.criteria.CategoryCriteria;
import com.blc.customerInterface.lib.dao.repo.criteria.spec.BaseCriteriaSpec;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class CategoryCriteriaSpec extends BaseCriteriaSpec<Category, CategoryCriteria> {
    @Override
    public Specification<Category> createForAll(CategoryCriteria criteria) {
        return null;
    }
}
