package com.blc.customerInterface.graphql.role.mutation.input;

import com.blc.customerInterface.lib.dao.mutation.input.BaseUpdateInput;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RoleUpdateInput extends BaseUpdateInput {

    private String name;
    private List<UUID> permissions = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UUID> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<UUID> permissions) {
        this.permissions = permissions;
    }
}
