package fr.stazi.epsi.spring.boot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.stazi.epsi.spring.boot.entity.DangerLevel;
import fr.stazi.epsi.spring.boot.entity.Prisoner;
import fr.stazi.epsi.spring.boot.exception.AlreadyExistsException;
import fr.stazi.epsi.spring.boot.exception.NotFoundException;
import fr.stazi.epsi.spring.boot.repository.PrisonerJpaRepository;

@RestController
@RequestMapping("/api/prisoners")
public class PrisonerController {

	@Autowired
	private PrisonerJpaRepository prisonerRepository;
	
	@GetMapping
	public List<Prisoner> getPrisoners() {
		return prisonerRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Prisoner getPrisoner(@PathVariable Long id) {
		return prisonerRepository.findById(id).orElse(null);
	}
	
	@PreAuthorize("hasAuthority('PRISONER_CREATE')")
	@PostMapping
	public Prisoner createPrisoner(@RequestBody Prisoner prisoner) throws AlreadyExistsException {
		if (prisoner.getId() == null) {
			return prisonerRepository.save(prisoner);
		} 
		
		throw new AlreadyExistsException();
	}

	@PreAuthorize("hasAuthority('PRISONER_SEIZE_DRUGS')")
	@PutMapping("/{id}")
	public Prisoner updatePrisoner(@PathVariable Long id, @RequestBody Prisoner prisoner) throws NotFoundException {
		Optional<Prisoner> existingPrisoner = prisonerRepository.findById(id);
		if (existingPrisoner.isPresent()) {
			prisoner.setId(id);
			return prisonerRepository.save(prisoner);
		}
		
		throw new NotFoundException();
	}

	@PreAuthorize("hasAuthority('PRISONER_RELEASE')")
	@DeleteMapping("/{id}")
	public void deletePrisoner(@PathVariable Long id) throws NotFoundException {
		Prisoner existingPrisoner = prisonerRepository.findById(id).orElseThrow(() -> new NotFoundException());
		prisonerRepository.delete(existingPrisoner);
	}
	
	@GetMapping("/search")
	public List<Prisoner> searchPrisoners(
			@RequestParam(required = false) String dangerLevel, 
			@RequestParam(required = false) String firstName, 
			@RequestParam(required = false) String lastName) {
		return prisonerRepository.searchPrisoners(DangerLevel.valueOf(dangerLevel), firstName, lastName);
	}
	
	@GetMapping("/search/jpql")
	public List<Prisoner> searchPrisonersJpql(
			@RequestParam(required = false) String dangerLevel, 
			@RequestParam(required = false) String firstName, 
			@RequestParam(required = false) String lastName) {
		return prisonerRepository.searchPrisonersJpql(DangerLevel.valueOf(dangerLevel), firstName, lastName);
	}
	
	@GetMapping("/firstName/{firstName}")
	public List<Prisoner> findByFirstName(@PathVariable String firstName) {
		return prisonerRepository.findByFirstNameOrderById(firstName);
	}
	
}
