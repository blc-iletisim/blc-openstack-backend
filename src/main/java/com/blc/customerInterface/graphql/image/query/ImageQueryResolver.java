package com.blc.customerInterface.graphql.image.query;

import com.blc.customerInterface.graphql.image.domain.Image;
import com.blc.customerInterface.graphql.image.query.pagination.ImagePageable;
import com.blc.customerInterface.graphql.image.repo.criteria.ImageCriteria;
import com.blc.customerInterface.graphql.image.service.ImageService;
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
public class ImageQueryResolver implements GraphQLQueryResolver {
    private final ImageService imageService;

    @Autowired
    public ImageQueryResolver(ImageService imageService) {
        this.imageService = imageService;
    }

    public Image image(UUID id){
        return imageService.findById(id).orElse(null);
    }

    public List<Image> images(ImageCriteria criteria, List<SortBy> sortBy){
        return imageService.filterWithSort(ObjectUtils.defaultIfNull(criteria,new ImageCriteria()),
                Sort.by(ObjectUtils.defaultIfNull(sortBy,new ArrayList<SortBy>())
                        .stream()
                        .map(SortBy::toOrder)
                        .collect(Collectors.toList())));
    }
    public ImagePageable paginateImages(Pagination pagination, ImageCriteria criteria, List<SortBy> sortBy){
        return new ImagePageable(imageService.filterWithPaginate(ObjectUtils.defaultIfNull(criteria,new ImageCriteria()),
                Pagination.toPageRequest(pagination,sortBy)));

    }
}
