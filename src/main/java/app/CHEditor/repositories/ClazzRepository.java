package app.CHEditor.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import app.CHEditor.domain.Clazz;

public interface ClazzRepository extends CrudRepository<Clazz, Integer> {

	List<Clazz> findListByPid(int pid);
	Clazz findByPid(int pid);
	Clazz findByCid(int cid);
	Clazz findByName(String name);
	List<Clazz> findBy_abstract(String _abstract);//has to be named this way to avoid errors
}
