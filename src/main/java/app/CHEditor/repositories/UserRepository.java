package app.CHEditor.repositories;

import org.springframework.data.repository.CrudRepository;

import app.CHEditor.domain.CHEUser;

public interface UserRepository extends CrudRepository<CHEUser, Integer> {

	CHEUser findByUsername(String username);
}
