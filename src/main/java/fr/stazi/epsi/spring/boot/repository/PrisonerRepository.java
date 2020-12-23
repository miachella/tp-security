package fr.stazi.epsi.spring.boot.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import fr.stazi.epsi.spring.boot.entity.DangerLevel;
import fr.stazi.epsi.spring.boot.entity.Prisoner;

@Repository
public interface PrisonerRepository {
	
	public List<Prisoner> searchPrisoners(DangerLevel dangerLevel, String firstName, String lastName);
	public List<Prisoner> searchPrisonersJpql(DangerLevel dangerLevel, String firstName, String lastName);
}
