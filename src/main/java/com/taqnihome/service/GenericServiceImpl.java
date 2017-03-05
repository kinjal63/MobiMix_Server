package com.taqnihome.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * This is class generic service implementation
 * Created by dhiren on 29/6/16.
 * @author dhiren
 * @since 29/06/2016
 * @param <PK>
 * @param <T>
 */
@Service
@Transactional
public abstract class GenericServiceImpl<PK extends Serializable, T> implements GenericService<PK, T> {

	private JpaRepository<T, PK> repository;

	GenericServiceImpl(JpaRepository<T, PK> repository) {
		this.repository = repository;
	}

	GenericServiceImpl() {
	}

	@Override
	public T save(T entity) throws Exception {
		return repository.save(entity);
	}

	@Override
	public void delete(T entity) throws Exception {
		repository.delete(entity);
	}

	@Override
	public T getById(PK key) throws Exception {
		return repository.findOne(key);

	}

	@Override
	public List<T> getAll() throws Exception {
		return repository.findAll();
	}

    @Override
    public void delete(PK pk) throws Exception {
        repository.delete(pk);
    }
}
