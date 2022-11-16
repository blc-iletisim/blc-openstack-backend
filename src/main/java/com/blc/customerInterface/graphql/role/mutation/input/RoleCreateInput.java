package com.blc.customerInterface.graphql.role.mutation.input;
import com.blc.customerInterface.lib.dao.mutation.input.BaseCreateInput;

import java.util.*;

public class RoleCreateInput extends BaseCreateInput {

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
