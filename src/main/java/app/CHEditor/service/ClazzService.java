package app.CHEditor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import app.CHEditor.domain.Clazz;
import app.CHEditor.repositories.ClazzRepository;

public class ClazzService implements UserDetailsService{
	@Autowired
	ClazzRepository cRepo;
	
//	@Transactional
	public void saveClazz(Clazz c) {
		cRepo.save(c);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
