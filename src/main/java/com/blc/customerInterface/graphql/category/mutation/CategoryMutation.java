package com.blc.customerInterface.graphql.category.mutation;

import com.blc.customerInterface.graphql.category.domain.Category;
import com.blc.customerInterface.graphql.category.mutation.input.CategoryCreateInput;
import com.blc.customerInterface.graphql.category.mutation.input.CategoryUpdateInput;
import com.blc.customerInterface.graphql.category.mutation.mapper.CategoryMapper;
import com.blc.customerInterface.graphql.category.service.CategoryService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.UUID;

@Component
@Validated
public class CategoryMutation implements GraphQLMutationResolver {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryMutation(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }
    public Category createCategory(@Valid CategoryCreateInput input){
        return categoryService.save(categoryMapper.toEntity(input));
    }
    public Category updateCategory(UUID id, @Valid CategoryUpdateInput input){
        return categoryService.findById(id).map(category ->categoryService.update(categoryMapper.updateEntity(category,input))).orElseThrow(RuntimeException::new);
    }
    public UUID deleteCategory(UUID id){
        return categoryService.findById(id).map(categoryService::delete).orElseThrow(RuntimeException::new);
    }
    public Category undeleteCategory(UUID id){
        return categoryService.findById(id).map(categoryService::undelete).orElseThrow(RuntimeException::new);
    }

}
