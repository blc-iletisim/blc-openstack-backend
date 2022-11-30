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
import com.blc.customerInterface.graphql.user.domain.User;
import com.blc.customerInterface.graphql.user.service.UserService;
import com.blc.customerInterface.lib.dao.mutation.mapper.BaseCreateUpdateMapper;
import com.blc.customerInterface.pem.Pem;
import com.blc.customerInterface.pem.PemRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

@Component
public class InstanceMapper extends BaseCreateUpdateMapper<Instance, InstanceCreateInput, InstanceUpdateInput> {
    private final FlavorService flavorService;
    private final ImageService imageService;
    private final UserService userService;
    private final CategoryService categoryService;
    private final PemRepository pemRepository;

    @Autowired
    public InstanceMapper(FlavorService flavorService, ImageService imageService, UserService userService, CategoryService categoryService, PemRepository pemRepository) {
        this.flavorService = flavorService;
        this.imageService = imageService;
        this.userService = userService;
        this.categoryService = categoryService;
        this.pemRepository = pemRepository;
    }

    @Override
    public Instance toEntity(InstanceCreateInput input) throws Throwable {

        Instance entity = new Instance();

        entity.setName(input.getName());

        Pem pem = pemRepository.findById(input.getPem()).orElse(null);
        entity.setPem(pem);

        Flavor flavor = flavorService.findById(input.getFlavor()).orElseThrow(new Supplier<Throwable>() {
            @Override
            public Throwable get() {
                return new RuntimeException();
            }
        });
        entity.setFlavor(flavor);

        User user = userService.findById(input.getUser()).orElseThrow(new Supplier<Throwable>() {
            @Override
            public Throwable get() {
                return new RuntimeException();
            }
        });
        entity.setUser(user);

        Image image = imageService.findById(input.getImage()).orElseThrow(new Supplier<Throwable>() {
            @Override
            public Throwable get() {
                return new RuntimeException();
            }
        });
        entity.setImage(image);

        List<Category> categories = new ArrayList<>();
        for (int i=0; i<input.getCategories().size(); i++){
            Category category = categoryService.findById(input.getCategories().get(i)).orElseThrow(new Supplier<Throwable>() {
                @Override
                public Throwable get() {
                    return new RuntimeException();
                }
            });
            categories.add(category);
        }
        entity.setCategories(categories);

        return entity;
    }

    @SneakyThrows
    @Override
    public Instance updateEntity(Instance entity, InstanceUpdateInput input)  {
        entity.setName(input.getName());

        Flavor flavor = flavorService.findById(input.getFlavor()).orElseThrow(new Supplier<Throwable>() {
            @Override
            public Throwable get() {
                return new RuntimeException();
            }
        });
        if (flavor.getCpu_size()>entity.getFlavor().getCpu_size()){
            entity.setFlavor(flavor);
        }

        Collection<Category> categories = new ArrayList<>();
        for (int i=0; i<input.getCategories().size(); i++){
            Category category = categoryService.findById(input.getCategories().get(i)).orElseThrow(new Supplier<Throwable>() {
                @Override
                public Throwable get() {
                    return new RuntimeException();
                }
            });
            categories.add(category);
        }
        entity.setCategories(categories);

        return entity;
    }
}
