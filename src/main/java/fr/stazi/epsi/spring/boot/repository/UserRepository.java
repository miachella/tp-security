package fr.stazi.epsi.spring.boot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.stazi.epsi.spring.boot.entity.user.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public Optional<User> findByUsername(String username);
}
