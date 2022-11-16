package com.blc.customerInterface.lib.dao.mutation.mapper;


import com.blc.customerInterface.lib.dao.mutation.input.BaseCreateInput;
import com.blc.customerInterface.lib.dao.mutation.input.BaseUpdateInput;

import java.io.Serializable;

public abstract class BaseCreateUpdateMapper<E extends Serializable, CI extends BaseCreateInput, UI extends BaseUpdateInput> extends BaseMapper<E> {

    public abstract E toEntity(CI input) throws Throwable;

    public abstract E updateEntity(E entity, UI input) throws Throwable;
}
