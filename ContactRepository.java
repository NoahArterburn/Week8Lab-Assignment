package Dmacc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Dmacc.Beans.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Long>{

}
