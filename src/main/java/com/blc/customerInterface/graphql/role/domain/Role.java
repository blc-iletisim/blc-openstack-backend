package com.blc.customerInterface.graphql.role.domain;

import com.blc.customerInterface.graphql.permission.domain.Permission;
import com.blc.customerInterface.graphql.user.domain.User;
import com.blc.customerInterface.lib.dao.domain.BaseDomain;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;

import java.util.*;

@Entity
@Table(name = "role")
public class Role extends BaseDomain {
    private String name;
    private List<Permission> permissions = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    @OneToMany(mappedBy = "role",fetch = FetchType.EAGER)
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}
