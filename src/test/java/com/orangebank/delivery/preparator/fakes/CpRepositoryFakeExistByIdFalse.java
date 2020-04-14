package com.orangebank.delivery.preparator.fakes;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.orangebank.delivery.preparator.entity.CpData;
import com.orangebank.delivery.preparator.repository.CpRepository;

/**
 * Clase fake con la implementacion del método existsById con retorno false.
 * @author abarreda
 *
 */
public class CpRepositoryFakeExistByIdFalse implements CpRepository {

	@Override
	public List<CpData> findAll() {
		return null;
	}

	@Override
	public List<CpData> findAll(Sort sort) {
		return null;
	}

	@Override
	public List<CpData> findAllById(Iterable<String> ids) {
		return null;
	}

	@Override
	public <S extends CpData> List<S> saveAll(Iterable<S> entities) {
		return null;
	}

	@Override
	public void flush() {
	}

	@Override
	public <S extends CpData> S saveAndFlush(S entity) {
		return null;
	}

	@Override
	public void deleteInBatch(Iterable<CpData> entities) {
	}

	@Override
	public void deleteAllInBatch() {
	}

	@Override
	public CpData getOne(String id) {
		return null;
	}

	@Override
	public <S extends CpData> List<S> findAll(Example<S> example) {
		return null;
	}

	@Override
	public <S extends CpData> List<S> findAll(Example<S> example, Sort sort) {
		return null;
	}

	@Override
	public Page<CpData> findAll(Pageable pageable) {
		return null;
	}

	@Override
	public <S extends CpData> S save(S entity) {
		return null;
	}

	@Override
	public Optional<CpData> findById(String id) {
		return null;
	}

	@Override
	public boolean existsById(String id) {
		return false;
	}

	@Override
	public long count() {
		return 0;
	}

	@Override
	public void deleteById(String id) {
	}

	@Override
	public void delete(CpData entity) {
	}

	@Override
	public void deleteAll(Iterable<? extends CpData> entities) {
	}

	@Override
	public void deleteAll() {
	}

	@Override
	public <S extends CpData> Optional<S> findOne(Example<S> example) {
		return null;
	}

	@Override
	public <S extends CpData> Page<S> findAll(Example<S> example, Pageable pageable) {
		return null;
	}

	@Override
	public <S extends CpData> long count(Example<S> example) {
		return 0;
	}

	@Override
	public <S extends CpData> boolean exists(Example<S> example) {
		return false;
	}
}
