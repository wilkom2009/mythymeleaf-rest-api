/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wilkom.caurisapp.core;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import com.wilkom.caurisapp.security.model.entity.Role;

/**
 *
 * @author Wilson
 * @param <T>
 * @param <PK>
 */
public interface IMyService<T, PK extends Serializable> {

	Long count();

	void delete(T t);

	T save(T entity);

	Iterable<T> saveAll(Iterable<T> entities);

	Optional<T> findById(PK id);

	boolean existsById(PK id);

	Iterable<T> findAll();
}
