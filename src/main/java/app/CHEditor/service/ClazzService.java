package app.CHEditor.service;

import org.springframework.beans.factory.annotation.Autowired;

import app.CHEditor.domain.Clazz;
import app.CHEditor.repositories.ClazzRepository;

public class ClazzService {
	@Autowired
	ClazzRepository cRepo;
	
//	@Transactional
	public void saveClazz(Clazz c) {
		cRepo.save(c);
	}
}
