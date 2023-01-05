package com.blc.customerInterface.graphql.company.domain;

import com.blc.customerInterface.graphql.user.domain.User;
import com.blc.customerInterface.lib.dao.domain.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "company")
@Builder
@AllArgsConstructor
public class Company extends BaseDomain {

    private String name;
    private List<User> users= new ArrayList<>();

    public Company() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @OneToMany(mappedBy = "company")
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}

