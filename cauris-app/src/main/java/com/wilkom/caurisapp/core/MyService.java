/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wilkom.caurisapp.core;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.wilkom.caurisapp.security.model.entity.Role;

/**
 *
 * @author Wilson
 * @param <T>
 * @param <PK>
 */
@Transactional
public abstract class MyService<T, PK extends Serializable> implements IMyService<T, PK> {

	protected CrudRepository<T, PK> repository;

	public MyService() {
	}

	public MyService(CrudRepository<T, PK> repository) {
		this.repository = repository;
	}

	@Override
	public Long count() {
		return repository.count();
	}

	@Override
	public void delete(T t) {
		repository.delete(t);
	}

	@Override
	public T save(T entity) {
		return repository.save(entity);
	}

	@Override
	public Iterable<T> saveAll(Iterable<T> entities) {
		return repository.saveAll(entities);
	}

	@Override
	public Optional<T> findById(PK id) {
		return repository.findById(id);
	}

	@Override
	public boolean existsById(PK id) {
		return repository.existsById(id);
	}

	@Override
	public Iterable<T> findAll() {
		return repository.findAll();
	}

	
}
