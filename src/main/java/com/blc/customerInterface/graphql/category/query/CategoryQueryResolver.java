package com.blc.customerInterface.graphql.category.query;

import com.blc.customerInterface.graphql.category.domain.Category;
import com.blc.customerInterface.graphql.category.query.pagination.CategoryPageable;
import com.blc.customerInterface.graphql.category.repo.criteria.CategoryCriteria;
import com.blc.customerInterface.graphql.category.service.CategoryService;
import com.blc.customerInterface.lib.dao.query.pagination.Pagination;
import com.blc.customerInterface.lib.dao.query.sort.SortBy;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class CategoryQueryResolver implements GraphQLQueryResolver {
    private final CategoryService categoryService;

    @Autowired
    public CategoryQueryResolver(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public Category category(UUID id){
        return categoryService.findById(id).orElse(null);
    }

    public List<Category> categories(CategoryCriteria criteria, List<SortBy> sortBy){
        return categoryService.filterWithSort(ObjectUtils.defaultIfNull(criteria,new CategoryCriteria()),
                Sort.by(ObjectUtils.defaultIfNull(sortBy,new ArrayList<SortBy>())
                        .stream()
                        .map(SortBy::toOrder)
                        .collect(Collectors.toList())));
    }
    public CategoryPageable paginateCategories(Pagination pagination, CategoryCriteria criteria, List<SortBy> sortBy){
        return new CategoryPageable(categoryService.filterWithPaginate(ObjectUtils.defaultIfNull(criteria,new CategoryCriteria()),
                Pagination.toPageRequest(pagination,sortBy)));

    }
}
