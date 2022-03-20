package dao;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import models.Client;
import models.Collection;
import models.CollectionItem;
import models.Crate;
import models.Customerusage;
import models.Invoice;
import models.Itemslist;
import models.Order;
import models.Warehouse;

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
    //gets all customer usages from database
public List<Customerusage> allCustUsage() {
		
		List<Customerusage> custusagelist = em.createNamedQuery("Customerusage.findAll", Customerusage.class).getResultList();
		return custusagelist;
	}
	//gets all customer usages for specific clienID.
	public Client getCustomerUsageByClientID(int clientID) {
		Client queryResult = em.createNamedQuery("Client.findCustUsagebyID", Client.class)
				.setParameter("id", clientID)
				.getSingleResult();
		return queryResult;
	}
	//creates invoice in the database and links it to the client.
	public void CreateInvoice(int clientID, int ammount) {
		
		Invoice In = new Invoice();
		Client cl = em.find(Client.class, clientID);
		In.setClient(cl);
		In.setAmount(ammount);
		Date date = new Date();
		In.setDate(date);
		In.setStatus("Not Payed");
		em.persist(In);
	
		
	}
	//Gets all invoices from database.
	public List<Invoice> allInvoices() {
		List<Invoice> Invoicelist = em.createNamedQuery("Invoice.findAll", Invoice.class).getResultList();
		return Invoicelist;
	}
	//Assigns shelf and warehouse to the existing crate.
	public void AssignShelf(int crateID, int warehouseID, String shelf) {
		
		Crate c = em.find(Crate.class, crateID);
		
		Warehouse wh = em.find(Warehouse.class, warehouseID);
		
		c.setShelf(shelf);
		
		c.getWarehouses().add(wh);
		
		wh.getCrates().add(c);
		em.persist(wh);
		
		em.persist(c);
		
	}
	//gets all crates from database.
	public List<Crate> allCrates() {
		List<Crate> listCrate = em.createNamedQuery("Crate.findAll", Crate.class).getResultList();
    	
		   
    	return listCrate;
	}
	public List<Order> allProccessedOrders() {
		String Status = "Crate Ordered";
    	List<Order> listOrders = em.createNamedQuery("Order.findOrderByStatus", Order.class)
    			.setParameter("Status", Status)
				.getResultList();
		return listOrders;
	}
	public void AcceptOrder(int orderNumber) {
		Order o = em.find(Order.class, orderNumber);
		
		o.setStatus("Done");
		
		em.persist(o);
		
	}
	public List<Order> allOrders() {

		List<Order> listOrders = em.createNamedQuery("Order.findAll", Order.class).getResultList();
		return listOrders;
	}
	public List<CollectionItem> getItemsList(int collectionNumber) {
		
		List<CollectionItem> CollectionItem = em.createNamedQuery("CollectionItem.findallitemsbycollectionID", CollectionItem.class).setParameter("id", collectionNumber).getResultList();
		
		
		return CollectionItem;
	}
	public List<Itemslist> getcrateItemsList(int collectionNumber) {
		Collection c = this.getcratefromcollection(collectionNumber);
		int i = c.getCrates().get(0).getId();
		System.out.print(i);
	 List<Itemslist> itemslist =  em.createNamedQuery("Itemslist.findallitemsbycrateID", Itemslist.class)
				.setParameter("id", i).getResultList();
		System.out.print(itemslist);
		return itemslist;
	}
	public Collection getcratefromcollection(int collectionNumber) {
		Collection crate= em.createNamedQuery("Collection.findcratebycollectionID", Collection.class)
				.setParameter("id", collectionNumber)
				.getSingleResult();
		
		return crate;
	}
	
}
