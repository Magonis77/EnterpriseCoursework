package dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import models.Client;
import models.Employee;

/**
 * Session Bean implementation class EmployeeDTO
 */
@Stateless
@LocalBean
public class EmployeeDTO {
	@PersistenceContext(unitName="EnterpriseCourseWork")
	EntityManager em;
    /**
     * Default constructor. 
     */
    public EmployeeDTO() {
        // TODO Auto-generated constructor stub
    }
    public List<Employee> allEmployee()
    {
    	List<Employee> listEmployee = em.createNamedQuery("Employee.findAll", Employee.class).getResultList();
    	
    	//List queryResults = em.createQuery("SELECT c FROM Client c").getResultList();
    	//List<Client> listClients = new ArrayList<Client>();
    	//for(int i = 0; i < queryResults.size(); i++)
    	//{
    	//	Client c = new Client();
    	//	u = (Client)queryResults.get(i);
    	//	listClients.add(u);
    	//}
    	
    	return listEmployee;
    }
}
