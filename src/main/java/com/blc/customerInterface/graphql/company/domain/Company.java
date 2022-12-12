package com.blc.customerInterface.graphql.company.domain;

import com.blc.customerInterface.graphql.user.domain.User;
import com.blc.customerInterface.lib.dao.domain.BaseDomain;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "company")
public class Company extends BaseDomain {

    private String name;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "company", cascade = CascadeType.ALL)
    private List<User> users= new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}

