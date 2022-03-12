package dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import models.Address;
import models.Client;
import models.Crate;
import models.Cratehistory;
import models.Customerusage;
import models.Employee;
import models.Invoice;
import models.Itemslist;
import models.Order;

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
    public void insertClient(String firstname,String LastName,String Email,int PhoneNumber,String Username, String Password)
    {
    	Client c = new Client();
    	c.setFirstName(firstname);
    	c.setLastName(LastName);
    	c.setEmailaddress(Email);
    	c.setPhoneNumber(PhoneNumber);
    	c.setUsername(Username);
    	c.setPassword(Password);
    	
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
    public List<Employee> allEmployee()
    {
    	List<Employee> listEmployee = em.createNamedQuery("Employee.findAll", Employee.class).getResultList();
    	
   
    	return listEmployee;
    }
    
	public void CreateOrder(int clientID, String itemType, String status, String collectionDate, String shelf,
			String statusCrate) {
		
		Order o = new Order();
		
		o.setClientID(clientID);
		o.setStatus(status);
		o.setCollectionDate(collectionDate);
		Date date = new Date();
		o.setDate(date);
		
		em.persist(o);
		Crate c = new Crate();
		
		c.setClientID(clientID);
		c.setItem_Type(itemType);
		c.setShelf(shelf);
		c.setStatus(statusCrate);
		
		em.persist(c);
		Cratehistory ch = new Cratehistory();
		
		ch.setCrate(c);
		ch.setDate_Stored(collectionDate);
		
		em.persist(ch);
		
		Customerusage cu = new Customerusage();
		
		cu.setTimes_collection_made(0);
		cu.setTimes_delivery_made(0);
		
		Client cl = em.find(Client.class, clientID);
		cu.setClient(cl);
		
		em.persist(cu);
	}
	public List<Crate> allCrates() {
		List<Crate> listCrate = em.createNamedQuery("Crate.findAll", Crate.class).getResultList();
    	
		   
    	return listCrate;
	}
	public void AddItem(String item, int idCrate) {
		
		Crate c = em.find(Crate.class, idCrate);
		
		Itemslist il = new Itemslist();
		
		il.setItem(item);
		il.getCrates().add(c);
		
		em.persist(il);
		
		
	}
	public List<Customerusage> allCustUsage() {
		
		List<Customerusage> custusagelist = em.createNamedQuery("Customerusage.findAll", Customerusage.class).getResultList();
		return custusagelist;
	}
	public Client getCustomerUsageByClientID(int clientID) {
		Client queryResult = em.createNamedQuery("Client.findCustUsagebyID", Client.class)
				.setParameter("id", clientID)
				.getSingleResult();
return queryResult;
	}
	public void CreateInvoice(int clientID, int ammount) {
		
		Invoice In = new Invoice();
		Client cl = em.find(Client.class, clientID);
		In.setClient(cl);
		In.setAmount(ammount);
		Date date = new Date();
		In.setDate(date);
		em.persist(In);
	
		
	}
}
