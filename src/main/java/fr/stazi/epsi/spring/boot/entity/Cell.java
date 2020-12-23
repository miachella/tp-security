package fr.stazi.epsi.spring.boot.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import fr.stazi.epsi.spring.boot.entity.user.User;

@Entity
public class Cell {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String code;
	
	private short maxPrisoners;
	
	@JsonIgnore
	@OneToMany(mappedBy = "cell")
	private List<Prisoner> prisoners;
	
	@ManyToMany
	@JoinTable(name = "user__cell", inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<User> users;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public short getMaxPrisoners() {
		return maxPrisoners;
	}

	public void setMaxPrisoners(short maxPrisoners) {
		this.maxPrisoners = maxPrisoners;
	}

	public List<Prisoner> getPrisoners() {
		return prisoners;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public void setPrisoners(List<Prisoner> prisoners) {
		this.prisoners = prisoners;
	}

	@Override
	public String toString() {
		return "Cell [id=" + id + ", code=" + code + ", maxPrisoners=" + maxPrisoners + ", prisoners=" + prisoners
				+ "]";
	}

}
