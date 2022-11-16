package com.blc.customerInterface.graphql.pem.mutation.input;

import com.blc.customerInterface.lib.dao.mutation.input.BaseCreateInput;

public class PemCreateInput extends BaseCreateInput {

    private String name;
    private String pem_url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPem_url() {
        return pem_url;
    }

    public void setPem_url(String pem_url) {
        this.pem_url = pem_url;
    }
}
