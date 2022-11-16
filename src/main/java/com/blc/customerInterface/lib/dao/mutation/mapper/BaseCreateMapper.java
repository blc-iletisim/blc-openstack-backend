package com.blc.customerInterface.lib.dao.mutation.mapper;


import com.blc.customerInterface.lib.dao.mutation.input.BaseCreateInput;

import java.io.Serializable;

public abstract class BaseCreateMapper<E extends Serializable, CI extends BaseCreateInput> extends BaseMapper<E> {

    public abstract E toEntity(CI input);
}
