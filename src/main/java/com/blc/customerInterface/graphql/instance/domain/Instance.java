package com.blc.customerInterface.graphql.instance.domain;

import com.blc.customerInterface.graphql.category.domain.Category;
import com.blc.customerInterface.graphql.flavor.domain.Flavor;
import com.blc.customerInterface.graphql.image.domain.Image;
import com.blc.customerInterface.graphql.pem.domain.Pem;
import com.blc.customerInterface.graphql.user.domain.User;
import com.blc.customerInterface.lib.dao.domain.BaseDomain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "instance")
public class Instance extends BaseDomain {

    private String name;
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

    @OneToOne(fetch = FetchType.EAGER)
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

    @OneToMany(mappedBy = "instance",fetch = FetchType.EAGER)
    public Collection<Category> getCategories() {
        return categories;
    }

    public void setCategories(Collection<Category> categories) {
        this.categories = categories;
    }
}
