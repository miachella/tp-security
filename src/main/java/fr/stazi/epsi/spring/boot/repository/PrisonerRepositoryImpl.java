package fr.stazi.epsi.spring.boot.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import fr.stazi.epsi.spring.boot.entity.DangerLevel;
import fr.stazi.epsi.spring.boot.entity.Prisoner;

public class PrisonerRepositoryImpl implements PrisonerRepository {
	@PersistenceContext
	private EntityManager em;
	
	public List<Prisoner> searchPrisoners(DangerLevel dangerLevel, String firstName, String lastName) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Prisoner> cq = cb.createQuery(Prisoner.class);
 
        Root<Prisoner> prisoner = cq.from(Prisoner.class);
        List<Predicate> predicates = new ArrayList<>();
        
        if (dangerLevel != null) {
        	predicates.add(cb.equal(prisoner.get("dangerLevel"), dangerLevel));
        }
        if (firstName != null) {
        	predicates.add(cb.like(prisoner.get("firstName"), "%" + firstName + "%"));
        }
        if (lastName != null) {
        	predicates.add(cb.like(prisoner.get("lastName"), "%" + lastName + "%"));
        }
        cq.where(predicates.toArray(new Predicate[]{}));
         
        TypedQuery<Prisoner> query = em.createQuery(cq);
        return query.getResultList();
	}
	
	public List<Prisoner> searchPrisonersJpql(DangerLevel dangerLevel, String firstName, String lastName) {
		String prisonerSearchQuery = "from Prisoner p where 1=1";
		if (dangerLevel != null) {
			prisonerSearchQuery += " and p.dangerLevel = :dangerLevel";
		}
		if (firstName != null) {
			prisonerSearchQuery += " and firstName like :firstName";
		}
		if (lastName != null) {
			prisonerSearchQuery += " and lastName like :lastName";
		}

		TypedQuery<Prisoner> query = em.createQuery(prisonerSearchQuery, Prisoner.class);
		
		if (dangerLevel != null) {
			query.setParameter("dangerLevel", dangerLevel);
		}
		if (firstName != null) {
			query.setParameter("firstName", "%" + firstName + "%");
		}
		if (lastName != null) {
			query.setParameter("lastName", "%" + lastName + "%");
		}
		
        return query.getResultList();
	}
	
}
