package app.CHEditor.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import app.CHEditor.domain.Clazz;
import app.CHEditor.domain.CHEUser;
import app.CHEditor.repositories.ClazzRepository;
import app.CHEditor.repositories.UserRepository;

public class CHEditorServices implements UserDetailsService{
	@Autowired
	ClazzRepository cRepo;
	
	@Autowired
	UserRepository uRepo;
//	@Transactional
	public void saveClazz(Clazz c) {
		cRepo.save(c);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		System.out.println("\n*********** LoadUserByName Called*********");
		System.out.println(username);
		CHEUser user = uRepo.findByUsername(username);
		
		
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNotLocked  = true;
		
		
		ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		if (user.getRole() == "User") {
			System.out.println("User Identified");
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		}
		
		return new User(
				username,
				user.getPassword(),
				enabled,
				accountNonExpired,
				credentialsNonExpired,
				accountNotLocked,
				authorities
				);
	}
	
}
