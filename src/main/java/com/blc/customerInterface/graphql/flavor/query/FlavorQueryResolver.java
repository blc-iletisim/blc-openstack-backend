package com.blc.customerInterface.graphql.flavor.query;

import com.blc.customerInterface.graphql.flavor.domain.Flavor;
import com.blc.customerInterface.graphql.flavor.query.pagination.FlavorPageable;
import com.blc.customerInterface.graphql.flavor.repo.criteria.FlavorCriteria;
import com.blc.customerInterface.graphql.flavor.service.FlavorService;
import com.blc.customerInterface.lib.dao.query.pagination.Pagination;
import com.blc.customerInterface.lib.dao.query.sort.SortBy;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class FlavorQueryResolver implements GraphQLQueryResolver {
    private final FlavorService flavorService;

    @Autowired
    public FlavorQueryResolver(FlavorService flavorService) {
        this.flavorService = flavorService;
    }

    public Flavor flavor(UUID id){
        return flavorService.findById(id).orElse(null);
    }

    public List<Flavor> flavors(FlavorCriteria criteria, List<SortBy> sortBy){
        return flavorService.filterWithSort(ObjectUtils.defaultIfNull(criteria,new FlavorCriteria()),
                Sort.by(ObjectUtils.defaultIfNull(sortBy,new ArrayList<SortBy>())
                        .stream()
                        .map(SortBy::toOrder)
                        .collect(Collectors.toList())));
    }
    public FlavorPageable paginateFlavors(Pagination pagination, FlavorCriteria criteria, List<SortBy> sortBy){
        return new FlavorPageable(flavorService.filterWithPaginate(ObjectUtils.defaultIfNull(criteria,new FlavorCriteria()),
                Pagination.toPageRequest(pagination,sortBy)));

    }
}
