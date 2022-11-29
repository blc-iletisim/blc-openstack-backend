package com.blc.customerInterface.graphql.instance.mutation.input;
import com.blc.customerInterface.lib.dao.mutation.input.BaseCreateInput;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InstanceCreateInput extends BaseCreateInput {
    private String name;
    private UUID pem;
    private UUID flavor;
    private UUID user;
    private UUID image;
    private List<UUID> categories= new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getPem() {
        return pem;
    }

    public void setPem(UUID pem) {
        this.pem = pem;
    }

    public UUID getFlavor() {
        return flavor;
    }

    public void setFlavor(UUID flavor) {
        this.flavor = flavor;
    }

    public UUID getUser() {
        return user;
    }

    public void setUser(UUID user) {
        this.user = user;
    }

    public UUID getImage() {
        return image;
    }

    public void setImage(UUID image) {
        this.image = image;
    }

    public List<UUID> getCategories() {
        return categories;
    }

    public void setCategories(List<UUID> categories) {
        this.categories = categories;
    }
}
