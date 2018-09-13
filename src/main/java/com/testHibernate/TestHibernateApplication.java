package com.testHibernate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.testHibernate.model.cin.CIN;
import com.testHibernate.model.diplome.NiveauDiplome;
import com.testHibernate.repo.cin.CINJPARepository;
import com.testHibernate.service.diplome.TestEntityNiveauDiplome;


@SpringBootApplication
@EnableAutoConfiguration
public class TestHibernateApplication {

	@Autowired
	private static CINJPARepository cinjpaRepository;
	
	@Autowired
	public static void main(String[] args) {
		//SpringApplication.run(TestHibernateApplication.class, args);
		
		System.out.println("Hello world!!!");
		try {
			
		
		
			List<CIN> test = cinjpaRepository.findAllCIN();
			if(test .size()!=0) {
				for(CIN niv : test) {
					System.out.println("Niveau = "+niv);
				}
			}
			else {
				System.out.println("List is empty!!! ");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
