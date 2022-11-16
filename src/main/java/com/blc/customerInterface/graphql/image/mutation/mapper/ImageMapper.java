package com.blc.customerInterface.graphql.image.mutation.mapper;

import com.blc.customerInterface.graphql.image.domain.Image;
import com.blc.customerInterface.graphql.image.mutation.input.ImageCreateInput;
import com.blc.customerInterface.graphql.image.mutation.input.ImageUpdateInput;
import com.blc.customerInterface.lib.dao.mutation.mapper.BaseCreateUpdateMapper;
import org.springframework.stereotype.Component;

@Component
public class ImageMapper extends BaseCreateUpdateMapper<Image, ImageCreateInput, ImageUpdateInput> {
    @Override
    public Image toEntity(ImageCreateInput input)  {
        Image entity = new Image();
        entity.setName(input.getName());
        return entity;
    }

    @Override
    public Image updateEntity(Image entity, ImageUpdateInput input)  {
        entity.setName(input.getName());
        return entity;
    }
}
