package fr.stazi.epsi.spring.boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.stazi.epsi.spring.boot.entity.Prisoner;

@Repository
public interface PrisonerJpaRepository extends JpaRepository<Prisoner, Long>, PrisonerRepository {
	
	public List<Prisoner> findByFirstNameOrderById(String firstName);
}
