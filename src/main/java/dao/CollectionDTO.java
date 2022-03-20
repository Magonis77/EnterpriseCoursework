package dao;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import models.Client;
import models.Collection;
import models.CollectionItem;
import models.Crate;
import models.Customerusage;
import models.Order;

/**
 * Session Bean implementation class CollectionDTO
 */
@Stateless
@LocalBean
public class CollectionDTO {
	@PersistenceContext(unitName="EnterpriseCourseWork")
	EntityManager em;
	@EJB
	private EmployeeDTO eDTO;
    /**
     * Default constructor. 
     */
    public CollectionDTO() {
        // TODO Auto-generated constructor stub
    }

	public void AddItem(String item, int collectionID) {

		CollectionItem ci = new CollectionItem();
		Collection c = em.find(Collection.class, collectionID);
		ci.setItem(item);
		ci.getCollections().add(c);
		c.getCollectionItems().add(ci);
		em.persist(c);
		em.persist(ci);
	
		
	}

	public void createCollection(String date, String time, String address, String Frequency, int clientID) {
		
		Collection c = new Collection();
		
		c.setDate(date);
		c.setTime(time);
		c.setCollection_Address(address);
		c.setJourney("Not set yet");
		c.setFrequency(Frequency);
		Client cu = this.getCustomerUsageByClientID(clientID);
		int TimesCollectionMade = 
		
		em.persist(c);
		
	}

	private Client getCustomerUsageByClientID(int clientID) {
		Client queryResult = em.createNamedQuery("Client.findusagebyclientID", Client.class)
				.setParameter("id", clientID)
				.getSingleResult();
		return queryResult;
	}

	public Integer getlatestcollectionadd() {
		Integer col = em.createQuery("select max(c.id) from Collection c", Integer.class).getSingleResult();
		
		return col;
		
	}

	public void assigncratecollection(int clientID, int crate) {
		int collection = this.getlatestcollectionadd();
		Collection c = em.find(Collection.class, collection);
		Crate cr = em.find(Crate.class, crate);
		
		cr.getCollections().add(c);
		c.getCrates().add(cr);
		cr.setStatus("in Transit from client");
		
		em.persist(cr);
		em.persist(c);
	}

	public void createCrate(int clientID, String itemType) {
		Crate c = new Crate();
		int collection = this.getlatestcollectionadd();
		Collection co = em.find(Collection.class, collection);
		c.setClientID(clientID);
		c.setItem_Type(itemType);
		c.setShelf("Not Assigned yet");
		c.setStatus("Not collected yet / In transit");
		co.getCrates().add(c);
		c.getCollections().add(co);
		em.persist(c);
		em.persist(co);
	}

}
