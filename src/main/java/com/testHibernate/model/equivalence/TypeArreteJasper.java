package com.testHibernate.model.equivalence;
 
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;  


@Entity
public class TypeArreteJasper {
	
	public TypeArreteJasper() {}
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
	private String typeArrete;
	
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTypeArrete() {
		return typeArrete;
	}

	public void setTypeArrete(String typeArrete) {
		this.typeArrete = typeArrete;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
		
}
