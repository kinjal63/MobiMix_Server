package com.taqnihome.service;

import java.io.Serializable;
import java.util.List;

/**
 *
 * This is interface for generic service
 * Created by dhiren on 29/6/16.
 * @author dhiren
 * @since 29/06/2016
 * @param <PK>
 * @param <T>
 */
public interface GenericService<PK extends Serializable, T> {

	T save(T entity) throws Exception;

	void delete(T entity) throws Exception;

	T getById(PK key) throws Exception;

	List<T> getAll() throws Exception;

	void delete(PK pk) throws Exception;
}