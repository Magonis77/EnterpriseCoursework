package dao;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import models.Address;
import models.Branch;
import models.Client;
import models.Crate;
import models.Cratehistory;
import models.Customerusage;
import models.Itemslist;
import models.Order;

/**
 * Session Bean implementation class BranchDTO
 */
@Stateless
@LocalBean
public class BranchDTO {
	@PersistenceContext(unitName="EnterpriseCourseWork")
	EntityManager em;
    /**
     * Default constructor. 
     */
    public BranchDTO() {
        // TODO Auto-generated constructor stub
    }
    //gets all orders to process from database.
    public List<Order> allProccessOrders()
    {
    	String Status = "In Process";
    	List<Order> listOrders = em.createNamedQuery("Order.findOrderByStatus", Order.class)
    			.setParameter("Status", Status)
				.getResultList();
 
    	return listOrders;
    }
    
    //sets orders status as crate ordered = processed order.
	public void processOrder(int orderNumber) {
		
		Order o = em.find(Order.class, orderNumber);
		Branch b = new Branch();
		o.setStatus("Crate Ordered");
		//b.getOrders().set(orderNumber, o);   Not implemented Yet Error fixing needed
		
		em.persist(b);
		em.persist(o);
		
	}
	
	public void SendOrderNote(int number) {
		//In future work this will take care of sending the order note to the employees as email or notifications, to notify
		//at the exact moment when the order is processed.
	}
}
