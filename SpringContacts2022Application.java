package Dmacc.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import Dmacc.Beans.Address;
import Dmacc.Beans.Contact;
import Dmacc.controller.BeanConfiguration;
import Dmacc.repository.ContactRepository;

@SpringBootApplication
public class SpringContacts2022Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringContacts2022Application.class, args);
		
		/*
		 * ApplicationContext appContext = new
		 * AnnotationConfigApplicationContext(BeanConfiguration.class);
		 * 
		 * Contact c = appContext.getBean("contact", Contact.class);
		 * 
		 * System.out.println(c.toString());
		 */
	}
	
	@Autowired
	ContactRepository repo;

	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		ApplicationContext appContext = new AnnotationConfigApplicationContext(BeanConfiguration.class);
		
		//Using an existing bean
		Contact c = appContext.getBean("contact", Contact.class);
		c.setRelationship("best friend");
		repo.save(c);
		
		//Create a bean to use - not managed by Spring
		Contact d = new Contact("Sandra Boyton", "555-555-5556", "friend");
		Address a = new Address("987 Elm Court", "Altoona", "IA");
		d.setAddress(a);
		repo.save(d);
		
		List<Contact> allMyContacts = repo.findAll();
		for(Contact people: allMyContacts) {
			System.out.println(people.toString());
		}
		
		//closes the ApplicationContext resource leak - not imperative to add but nice to clean it up
		((AbstractApplicationContext) appContext).close();
	
	}

}