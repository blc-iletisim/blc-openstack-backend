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
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Component
public class InstanceMapper extends BaseCreateUpdateMapper<Instance, InstanceCreateInput, InstanceUpdateInput> {
    private final FlavorService flavorService;
    private final ImageService imageService;
    private final UserService userService;
    private final CategoryService categoryService;
    private final PemRepository pemRepository;

    @Autowired
    @Value("${storage.json-path}")
    private String jsonPath;
    @Autowired
    @Value("${storage.shell-path}")
    private String shellPath;

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

        Pem pem = pemRepository.findById(input.getPem()).orElseThrow(new Supplier<Throwable>() {
            @Override
            public Throwable get() {
                return new RuntimeException();
            }
        });
        entity.setPemName(pem.getName());
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

        Set<Category> categories = new HashSet<>();
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


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", input.getName());
        jsonObject.put("pem", pem.getName());
        jsonObject.put("flavor", flavor.getName());
        jsonObject.put("userName", user.getName());
        jsonObject.put("image", image.getName());
        jsonObject.put("category", categories.stream().map(category -> category.getName()).collect(Collectors.toList()));
        try {

            FileWriter file = new FileWriter(jsonPath+"/deneme.json");
            file.write(jsonObject.toString());
            file.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("JSON file created: "+jsonObject);

        ProcessBuilder pb = new ProcessBuilder("src/main/java/com/blc/customerInterface/shell/instance.sh");
        Process p = pb.start();

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

        Set<Category> categories = new HashSet<>();
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
