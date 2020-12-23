package fr.stazi.epsi.spring.boot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.stazi.epsi.spring.boot.entity.Cell;
import fr.stazi.epsi.spring.boot.entity.user.User;
import fr.stazi.epsi.spring.boot.repository.UserRepository;

@Service
public class SecurityMethods {
	
	@Autowired
	private UserRepository userRepository;
	
	public boolean canManage(org.springframework.security.core.userdetails.User principal, Long cellId) {
		User user = userRepository.findByUsername(principal.getUsername()).orElseThrow(() -> new UsernameNotFoundException(principal.getUsername()));
		for (Cell cell : user.getCells()) {
			if (cell.getId().equals(cellId)) {
				return true;
			}
		}
		return false;
	}

}
