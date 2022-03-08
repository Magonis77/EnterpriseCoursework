package dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import models.Address;
import models.Client;

/**
 * Session Bean implementation class CustomerDTO
 */
@Stateless
@LocalBean
public class CustomerDTO {
	@PersistenceContext(unitName="EnterpriseCourseWork")
	EntityManager em;
    /**
     * Default constructor. 
     */
    public CustomerDTO() {
        // TODO Auto-generated constructor stub
    }
    public void insertClient(String firstname,String LastName,String Email,int PhoneNumber)
    {
    	Client c = new Client();
    	c.setFirstName(firstname);
    	c.setLastName(LastName);
    	c.setEmailaddress(Email);
    	c.setPhoneNumber(PhoneNumber);
    	em.persist(c);
    }
    public List<Client> allClients()
    {
    	List<Client> listClients = em.createNamedQuery("Client.findAll", Client.class).getResultList();
    	
    	//List queryResults = em.createQuery("SELECT c FROM Client c").getResultList();
    	//List<Client> listClients = new ArrayList<Client>();
    	//for(int i = 0; i < queryResults.size(); i++)
    	//{
    	//	Client c = new Client();
    	//	u = (Client)queryResults.get(i);
    	//	listClients.add(u);
    	//}
    	
    	return listClients;
    }
    public void insertuseraddress(int ClientId, String addressline1,String addressline2,String addressline3,String addressline4,String postcode,String city) {
		Client c = em.find(Client.class, ClientId);
		
		Address a = new Address();
		a.setAddressLine1(addressline1);
		a.setAddressLine2(addressline2);
		a.setAddressLine3(addressline3);
		a.setAddressLine4(addressline4);
		a.setPostCode(postcode);
		a.setCity(city);
		a.getClients().add(c);

		
		em.persist(a);
	}

}
