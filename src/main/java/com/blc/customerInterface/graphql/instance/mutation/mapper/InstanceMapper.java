package com.blc.customerInterface.graphql.instance.mutation.mapper;

import com.blc.customerInterface.graphql.category.domain.Category;
import com.blc.customerInterface.graphql.category.service.CategoryService;
import com.blc.customerInterface.graphql.flavor.domain.Flavor;
import com.blc.customerInterface.graphql.flavor.service.FlavorService;
import com.blc.customerInterface.graphql.image.domain.Image;
import com.blc.customerInterface.graphql.image.service.ImageService;
import com.blc.customerInterface.graphql.instance.domain.Instance;
import com.blc.customerInterface.graphql.instance.mutation.input.InstanceCreateInput;
import com.blc.customerInterface.graphql.instance.mutation.input.InstanceUpdateInput;
import com.blc.customerInterface.graphql.pem.domain.Pem;
import com.blc.customerInterface.graphql.pem.service.PemService;
import com.blc.customerInterface.graphql.user.domain.User;
import com.blc.customerInterface.graphql.user.service.UserService;
import com.blc.customerInterface.lib.dao.mutation.mapper.BaseCreateUpdateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class InstanceMapper extends BaseCreateUpdateMapper<Instance, InstanceCreateInput, InstanceUpdateInput> {

    private final FlavorService flavorService;
    private final ImageService imageService;
    private final UserService userService;
    private final CategoryService categoryService;
    private final PemService pemService;

    @Autowired
    public InstanceMapper(FlavorService flavorService, ImageService imageService, UserService userService, CategoryService categoryService, PemService pemService) {
        this.flavorService = flavorService;
        this.imageService = imageService;
        this.userService = userService;
        this.categoryService = categoryService;
        this.pemService = pemService;
    }


    @Override
    public Instance toEntity(InstanceCreateInput input){

        Instance entity = new Instance();

        entity.setName(input.getName());

        Pem pem = new Pem();
        pem.setName(input.getPem().getName());
        pem.setPem_url(input.getPem().getPem_url());
        pemService.save(pem);
        entity.setPem(pem);

        Flavor flavor = flavorService.findById(input.getFlavor()).orElse(null);
        entity.setFlavor(flavor);

        User user = userService.findById(input.getUser()).orElse(null);
        entity.setUser(user);

        Image image = imageService.findById(input.getImage()).orElse(null);
        entity.setImage(image);

        Collection<Category> categories = new ArrayList<>();
        for (int i=0; i<input.getCategories().size(); i++){
            Category category = categoryService.findById(input.getCategories().get(i)).orElse(null);
            categories.add(category);
        }
        entity.setCategories(categories);

        return entity;
    }

    @Override
    public Instance updateEntity(Instance entity, InstanceUpdateInput input){
        entity.setName(input.getName());

        Pem pem = new Pem();
        pem.setName(input.getPem().getName());
        pem.setPem_url(input.getPem().getPem_url());
        pemService.save(pem);
        entity.setPem(pem);

        Flavor flavor = flavorService.findById(input.getFlavor()).orElse(null);
        entity.setFlavor(flavor);

        User user = userService.findById(input.getUser()).orElse(null);
        entity.setUser(user);

        Image image = imageService.findById(input.getImage()).orElse(null);
        entity.setImage(image);

        Collection<Category> categories = new ArrayList<>();
        for (int i=0; i<input.getCategories().size(); i++){
            Category category = categoryService.findById(input.getCategories().get(i)).orElse(null);
            categories.add(category);
        }
        entity.setCategories(categories);

        return entity;
    }
}
