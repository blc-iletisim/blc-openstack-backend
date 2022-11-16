package com.blc.customerInterface.lib.dao.service;

import com.blc.customerInterface.lib.dao.domain.BaseDomain;
import com.blc.customerInterface.lib.dao.repo.BaseRepo;
import com.blc.customerInterface.lib.dao.repo.criteria.BaseCriteria;
import com.blc.customerInterface.lib.dao.repo.criteria.spec.BaseCriteriaSpec;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class BaseService<E extends BaseDomain, R extends BaseRepo<E>, C extends BaseCriteria, CS extends BaseCriteriaSpec<E, C>>  {
    private final R repository;
    private final CS criteriaSpec;

    public BaseService(R repository, CS criteriaSpec) {
        this.repository = repository;
        this.criteriaSpec = criteriaSpec;
    }

    public E save(E entity) {
        return repository.save(entity);
    }

    public List<E> saveAll(List<E> entities) {
        return repository.saveAll(entities);
    }

    public E update(E entity) {
        return repository.save(entity);
    }

    public UUID delete(E entity) {
        repository.delete(entity);
        return entity.getId();
    }

    public int deleteAll(List<E> entities){
        repository.deleteAll(entities);
        return entities.size();
    }

    public E undelete(E entity) {
        entity.setDeletedDateTime(null);
        return repository.save(entity);
    }

    public Optional<E> findById(UUID id) {
        return repository.findById(id);
    }

    public List<E> findAll() {
        return repository.findAll();
    }

    public List<E> findAllById(List<UUID> ids) {
        return repository.findAllById(ids);
    }

    public List<E> filterWithSort(C criteria, Sort sort) {
        return repository.findAll(criteriaSpec.createForAll(criteria), sort);
    }

    public Page<E> filterWithPaginate(C criteria, Pageable pageable) {
        return repository.findAll(criteriaSpec.createForAll(criteria), pageable);
    }

    public R getRepository() {
        return repository;
    }
}
