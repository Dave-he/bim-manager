package com.mjwise.demo.service;

import com.mjwise.demo.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class BaseService<T, ID extends Serializable> {

	@Autowired
	BaseRepository<T, ID> repository;


	public List<T> findAll() {
		return repository.findAll();
	}


	public List<T> findAll(Sort sort) {
		return repository.findAll(sort);
	}


	public Page<T> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}


	public List<T> findAllById(Iterable<ID> iterable) {
		return repository.findAllById(iterable);
	}


	public long count() {
		return repository.count();
	}


	public void deleteById(ID id) {
		repository.deleteById(id);
	}


	public void delete(T t) {
		repository.delete(t);
	}


	public void deleteAll(Iterable<? extends T> iterable) {
		repository.deleteAll(iterable);
	}


	public void deleteAll() {
		repository.deleteAll();
	}


	public <S extends T> S save(S s) {
		return repository.save(s);
	}


	public <S extends T> List<S> saveAll(Iterable<S> iterable) {
		return repository.saveAll(iterable);
	}


	public Optional<T> findById(ID id) {
		return repository.findById(id);
	}


	public boolean existsById(ID id) {
		return repository.existsById(id);
	}


	public void flush() {
		repository.flush();
	}


	public <S extends T> S saveAndFlush(S s) {
		return repository.saveAndFlush(s);
	}


	public void deleteInBatch(Iterable<T> iterable) {
		repository.deleteInBatch(iterable);
	}


	public void deleteAllInBatch() {
		repository.deleteAllInBatch();
	}


	public T getOne(ID id) {
		return repository.getOne(id);
	}


	public <S extends T> Optional<S> findOne(Example<S> example) {
		return repository.findOne(example);
	}


	public <S extends T> List<S> findAll(Example<S> example) {
		return repository.findAll(example);
	}


	public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
		return repository.findAll(example, sort);
	}


	public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
		return repository.findAll(example, pageable);
	}


	public <S extends T> long count(Example<S> example) {
		return repository.count(example);
	}


	public <S extends T> boolean exists(Example<S> example) {
		return repository.exists(example);
	}


	public Optional<T> findOne(Specification<T> specification) {
		return repository.findOne(specification);
	}


	public List<T> findAll(Specification<T> specification) {
		return repository.findAll(specification);
	}


	public Page<T> findAll(Specification<T> specification, Pageable pageable) {
		return repository.findAll(specification, pageable);
	}


	public List<T> findAll(Specification<T> specification, Sort sort) {
		return repository.findAll(specification, sort);
	}

	public long count(Specification<T> specification) {
		return repository.count(specification);
	}

}
