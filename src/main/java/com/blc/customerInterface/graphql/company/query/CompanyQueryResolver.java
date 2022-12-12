package com.blc.customerInterface.graphql.company.query;

import com.blc.customerInterface.graphql.company.domain.Company;
import com.blc.customerInterface.graphql.company.query.pagination.CompanyPageable;
import com.blc.customerInterface.graphql.company.repo.criteria.CompanyCriteria;
import com.blc.customerInterface.graphql.company.service.CompanyService;
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
public class CompanyQueryResolver implements GraphQLQueryResolver {
    private final CompanyService companyService;

    @Autowired
    public CompanyQueryResolver(CompanyService companyService) {
        this.companyService = companyService;
    }

    public Company company(UUID id){
        return companyService.findById(id).orElse(null);
    }

    public List<Company> companies(CompanyCriteria criteria, List<SortBy> sortBy){
        return companyService.filterWithSort(ObjectUtils.defaultIfNull(criteria,new CompanyCriteria()),
                Sort.by(ObjectUtils.defaultIfNull(sortBy,new ArrayList<SortBy>())
                        .stream()
                        .map(SortBy::toOrder)
                        .collect(Collectors.toList())));
    }
    public CompanyPageable paginateCompanies(Pagination pagination, CompanyCriteria criteria, List<SortBy> sortBy){
        return new CompanyPageable(companyService.filterWithPaginate(ObjectUtils.defaultIfNull(criteria,new CompanyCriteria()),
                Pagination.toPageRequest(pagination,sortBy)));

    }
}

