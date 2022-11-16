package com.blc.customerInterface.graphql.image.query.pagination;

import com.blc.customerInterface.graphql.image.domain.Image;
import com.blc.customerInterface.lib.dao.query.pagination.PaginationResult;
import org.springframework.data.domain.Page;

public class ImagePageable extends PaginationResult<Image> {
    public ImagePageable(Page<Image> page) {
        super(page);
    }
}
