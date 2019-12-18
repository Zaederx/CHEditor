package app.CHEditor.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import app.CHEditor.domain.Clazz;

public interface ClazzRepository extends CrudRepository<Clazz, Integer> {

	List<Clazz> findByPID(int pid);
	Clazz findByCID(int cid);
	Clazz findByName(String name);
	List<Clazz> findByAbstract_(String abstract_);
}
