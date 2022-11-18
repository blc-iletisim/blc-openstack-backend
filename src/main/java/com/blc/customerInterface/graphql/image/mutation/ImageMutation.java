package com.blc.customerInterface.graphql.image.mutation;

import com.blc.customerInterface.graphql.image.domain.Image;
import com.blc.customerInterface.graphql.image.mutation.input.ImageCreateInput;
import com.blc.customerInterface.graphql.image.mutation.input.ImageUpdateInput;
import com.blc.customerInterface.graphql.image.mutation.mapper.ImageMapper;
import com.blc.customerInterface.graphql.image.service.ImageService;
import com.blc.customerInterface.graphql.permission.domain.PermissionName;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.UUID;

@Validated
@Component
public class ImageMutation implements GraphQLMutationResolver {

    private final ImageService imageService;
    private final ImageMapper imageMapper;

    @Autowired
    public ImageMutation(ImageService imageService, ImageMapper imageMapper) {
        this.imageService = imageService;
        this.imageMapper = imageMapper;
    }
    @PreAuthorize("hasAuthority('"+ PermissionName.IMAGE_CREATE +"')")
    public Image createImage(@Valid ImageCreateInput input){
        return imageService.save(imageMapper.toEntity(input));
    }
    @PreAuthorize("hasAuthority('"+ PermissionName.IMAGE_UPDATE +"')")
    public Image updateImage(UUID id, @Valid ImageUpdateInput input){
        return imageService.findById(id).map(image ->imageService.update(imageMapper.updateEntity(image,input))).orElseThrow(RuntimeException::new);
    }
    @PreAuthorize("hasAuthority('"+ PermissionName.IMAGE_DELETE +"')")
    public UUID deleteImage(UUID id){
        return imageService.findById(id).map(imageService::delete).orElseThrow(RuntimeException::new);
    }
    @PreAuthorize("hasAuthority('"+ PermissionName.IMAGE_UNDELETE +"')")
    public Image undeleteImage(UUID id){
        return imageService.findById(id).map(imageService::undelete).orElseThrow(RuntimeException::new);
    }
}
