package dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import models.Crate;
import models.Employee;

/**
 * Session Bean implementation class adminDTO
 */
@Stateless
@LocalBean
public class adminDTO {
	@PersistenceContext(unitName="EnterpriseCourseWork")
	EntityManager em;
    /**
     * Default constructor. 
     */
    public adminDTO() {
        // TODO Auto-generated constructor stub
    }
    //gets the list of all employees from database
    public List<Employee> allEmployee()
    {
    	List<Employee> listEmployee = em.createNamedQuery("Employee.findAll", Employee.class).getResultList();
    	
   
    	return listEmployee;
    }
}
