package com.blc.customerInterface.lib.dao.query.sort;

import org.springframework.data.domain.Sort;

import java.io.Serializable;

public class SortBy implements Serializable {
    private Direction direction;
    private String field;

    public SortBy() {
    }

    public SortBy(Direction direction, String field) {
        this.direction = direction;
        this.field = field;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public static Sort.Order toOrder(SortBy sortBy) {
        return new Sort.Order(Sort.Direction.valueOf(sortBy.getDirection().toString()), sortBy.getField());
    }
}
