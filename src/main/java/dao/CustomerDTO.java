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
import models.Warehouse;

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
	//inserts that the payment was done into the database
	public void AddPayment(int InvoiceID) {
		
		Invoice I = em.find(Invoice.class, InvoiceID);

		I.setStatus("Payed");
		em.persist(I);
	}
	//adds a new client into the database
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
    //list all clients from database
    public List<Client> allClients()
    {
    	List<Client> listClients = em.createNamedQuery("Client.findAll", Client.class).getResultList();
 
    	return listClients;
    }
    //inserts user address into database
    public void insertuseraddress(int ClientId, String addressline1,String addressline2,String addressline3,String addressline4,String postcode,String city) {
		Client c = em.find(Client.class, ClientId);
		
		Address a = new Address();
		a.setAddressLine1(addressline1);
		a.setAddressLine2(addressline2);
		a.setAddressLine3(addressline3);
		a.setAddressLine4(addressline4);
		a.setPostCode(postcode);
		a.setCity(city);
		//gets the clientid and adds to relationship in database
		a.getClients().add(c);

		
		em.persist(a);
		

		Customerusage cu = new Customerusage();
		
		cu.setTimes_collection_made(0);
		cu.setTimes_delivery_made(0);
		
		Client cl = em.find(Client.class, ClientId);
		cu.setClient(cl);
		
		em.persist(cu);
	}
    //creates order and inserts it into the dabase.
    public void CreateOrder(int clientID, String itemType, String status, String collectionDate, String shelf,
			String statusCrate, String Time) {
		
    	//inserts order into database
		Order o = new Order();
		
		o.setClientID(clientID);
		o.setStatus(status);
		o.setCollectionDate(collectionDate);
		Date date = new Date();
		o.setDate(date);
		
		em.persist(o);
		//inserts crate into database
		Crate c = new Crate();
		
		c.setClientID(clientID);
		c.setItem_Type(itemType);
		c.setShelf(shelf);
		c.setStatus(statusCrate);
		
		em.persist(c);
		//makes cratehistory and links it to the crate created
		Cratehistory ch = new Cratehistory();
		
		ch.setCrate(c);
		ch.setDate_Stored(collectionDate);
		
		em.persist(ch);
		
		//makes customer usage in database as the order is created
	}
    //adds items to the database and links them to the crate of the client.
	public void AddItem(String item, int idCrate) {
		
		Crate c = em.find(Crate.class, idCrate);
		
		Itemslist il = new Itemslist();
		
		il.setItem(item);
		il.getCrates().add(c);
		
		em.persist(il);
		
		
	}
	
	public List<Crate> allCratesbyClientID(int clientID) {
		List<Crate> cratelist = em.createNamedQuery("Crate.findCrateByClientID", Crate.class)
		.setParameter("id", clientID)
		.getResultList();
		
		return cratelist;
	}
	public List<Order> allCustomerOrders(int clientID) {
    	List<Order> listOrders = em.createNamedQuery("Order.findOrderByClient", Order.class)
    			.setParameter("id", clientID)
				.getResultList();
		return listOrders;
	}
}
