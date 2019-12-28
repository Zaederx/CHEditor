package app.CHEditor.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import app.CHEditor.domain.Clazz;

public interface ClazzRepository extends CrudRepository<Clazz, Integer> {

	List<Clazz> findListByPid(Integer pid);
	Clazz findByPid(Integer pid);
	Clazz findByCid(Integer cid);
	Clazz findByName(String name);
	List<Clazz> findBy_abstract(String _abstract);//has to be named this way to avoid errors
}
