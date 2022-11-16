package com.blc.customerInterface.graphql.instance.query;

import com.blc.customerInterface.graphql.instance.domain.Instance;
import com.blc.customerInterface.graphql.instance.query.pagination.InstancePageable;
import com.blc.customerInterface.graphql.instance.repo.criteria.InstanceCriteria;
import com.blc.customerInterface.graphql.instance.service.InstanceService;
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
public class InstanceQueryResolver implements GraphQLQueryResolver {

    private final InstanceService instanceService;

    @Autowired
    public InstanceQueryResolver(InstanceService instanceService) {
        this.instanceService = instanceService;
    }

    public Instance instance(UUID id){
        return instanceService.findById(id).orElse(null);
    }

    public List<Instance> instances(InstanceCriteria criteria, List<SortBy> sortBy){
        return instanceService.filterWithSort(ObjectUtils.defaultIfNull(criteria,new InstanceCriteria()),
                Sort.by(ObjectUtils.defaultIfNull(sortBy,new ArrayList<SortBy>())
                        .stream()
                        .map(SortBy::toOrder)
                        .collect(Collectors.toList())));
    }
    public InstancePageable paginateInstances(Pagination pagination, InstanceCriteria criteria, List<SortBy> sortBy){
        return new InstancePageable(instanceService.filterWithPaginate(ObjectUtils.defaultIfNull(criteria,new InstanceCriteria()),
                Pagination.toPageRequest(pagination,sortBy)));

    }

}
