package com.blc.customerInterface.graphql.category.mutation.mapper;

import com.blc.customerInterface.graphql.category.domain.Category;
import com.blc.customerInterface.graphql.category.mutation.input.CategoryCreateInput;
import com.blc.customerInterface.graphql.category.mutation.input.CategoryUpdateInput;
import com.blc.customerInterface.graphql.instance.domain.Instance;
import com.blc.customerInterface.graphql.instance.service.InstanceService;
import com.blc.customerInterface.lib.dao.mutation.mapper.BaseCreateUpdateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper extends BaseCreateUpdateMapper<Category, CategoryCreateInput, CategoryUpdateInput> {
    private final InstanceService instanceService;

    @Autowired
    public CategoryMapper(InstanceService instanceService) {
        this.instanceService = instanceService;
    }

    @Override
    public Category toEntity(CategoryCreateInput input)  {
        Category entity = new Category();
        entity.setName(input.getName());

        return entity;
    }

    @Override
    public Category updateEntity(Category entity, CategoryUpdateInput input) {
        entity.setName(input.getName());

        return entity;
    }
}
