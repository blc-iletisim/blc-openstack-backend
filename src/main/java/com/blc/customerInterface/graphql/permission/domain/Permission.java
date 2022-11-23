package com.blc.customerInterface.graphql.permission.domain;

import com.blc.customerInterface.graphql.role.domain.Role;
import com.blc.customerInterface.lib.dao.domain.BaseDomain;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "permission")
public class Permission extends BaseDomain implements GrantedAuthority {

    private String name;

    private List<Role> roles = new ArrayList<>();

    @ManyToMany(mappedBy = "permissions",fetch = FetchType.EAGER)
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Transient
    @Override
    public String getAuthority() {
        return name;
    }
}
