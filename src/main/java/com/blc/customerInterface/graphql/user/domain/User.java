package com.blc.customerInterface.graphql.user.domain;

import com.blc.customerInterface.graphql.company.domain.Company;
import com.blc.customerInterface.graphql.instance.domain.Instance;
import com.blc.customerInterface.graphql.refreshToken.domain.RefreshToken;
import com.blc.customerInterface.graphql.role.domain.Role;
import com.blc.customerInterface.lib.dao.domain.BaseDomain;
import com.blc.customerInterface.pem.Pem;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "user")
public class User extends BaseDomain {

    private String name;
    private String email;
    private String password;
    private Company company;
    private Role role;
    private List<Instance> instances= new ArrayList<>();
    private List<Pem> pems = new ArrayList<>();

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    public List<Instance> getInstances() {
        return instances;
    }

    public void setInstances(List<Instance> instances) {
        this.instances = instances;
    }

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
   // @LazyCollection(LazyCollectionOption.FALSE)
    public List<Pem> getPems() {
        return pems;
    }

    public void setPems(List<Pem> pems) {
        this.pems = pems;
    }

}
