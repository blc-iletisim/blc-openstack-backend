package com.blc.customerInterface.graphql.instance.domain;

import com.blc.customerInterface.graphql.category.domain.Category;
import com.blc.customerInterface.graphql.flavor.domain.Flavor;
import com.blc.customerInterface.graphql.image.domain.Image;
import com.blc.customerInterface.graphql.user.domain.User;
import com.blc.customerInterface.lib.dao.domain.BaseDomain;
import com.blc.customerInterface.pem.Pem;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "instance")
public class Instance extends BaseDomain {
    //private static final long serialVersionUID = 1L;
    private String name;
    private String pemName;
    private Pem pem;
    private Flavor flavor;
    private User user;
    private Image image;
    private Collection<Category> categories= new ArrayList<>();

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false)
    public String getPemName() {
        return pemName;
    }

    public void setPemName(String pemName) {
        this.pemName = pemName;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    public Pem getPem() {
        return pem;
    }

    public void setPem(Pem pem) {
        this.pem = pem;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    public Flavor getFlavor() {
        return flavor;
    }

    public void setFlavor(Flavor flavor) {
        this.flavor = flavor;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    public Collection<Category> getCategories() {
        return categories;
    }

    public void setCategories(Collection<Category> categories) {
        this.categories = categories;
    }
}
