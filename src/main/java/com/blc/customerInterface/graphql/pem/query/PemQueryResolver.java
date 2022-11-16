package com.blc.customerInterface.graphql.pem.query;

import com.blc.customerInterface.graphql.pem.domain.Pem;
import com.blc.customerInterface.graphql.pem.query.pagination.PemPageable;
import com.blc.customerInterface.graphql.pem.repo.criteria.PemCriteria;
import com.blc.customerInterface.graphql.pem.service.PemService;
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
public class PemQueryResolver implements GraphQLQueryResolver {
    private final PemService pemService;

    @Autowired
    public PemQueryResolver(PemService pemService) {
        this.pemService = pemService;
    }

    public Pem pem(UUID id){
        return pemService.findById(id).orElse(null);
    }

    public List<Pem> pems(PemCriteria criteria, List<SortBy> sortBy){
        return pemService.filterWithSort(ObjectUtils.defaultIfNull(criteria,new PemCriteria()),
                Sort.by(ObjectUtils.defaultIfNull(sortBy,new ArrayList<SortBy>())
                        .stream()
                        .map(SortBy::toOrder)
                        .collect(Collectors.toList())));
    }
    public PemPageable paginatePems(Pagination pagination, PemCriteria criteria, List<SortBy> sortBy){
        return new PemPageable(pemService.filterWithPaginate(ObjectUtils.defaultIfNull(criteria,new PemCriteria()),
                Pagination.toPageRequest(pagination,sortBy)));

    }
    
}
