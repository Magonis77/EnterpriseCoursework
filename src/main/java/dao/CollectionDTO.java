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
import models.Cratehistory;
import models.Customerusage;
import models.Invoice;
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
		Client client = em.find(Client.class, clientID);
		c.setDate(date);
		c.setTime(time);
		c.setCollection_Address(address);
		c.setJourney("Not set yet");
		c.setFrequency(Frequency);
		c.setClient(client);
		Client cl = em.find(Client.class, clientID);
		
		int TimesCollectionMade = cl.getCustomerusages().get(0).getTimes_collection_made();
		int Times = TimesCollectionMade +1;
		cl.getCustomerusages().get(0).setTimes_collection_made(Times);
		
		em.persist(c);
		em.persist(cl);
		
	}

	private Customerusage getCustomerUsageByClientID(int clientID) {
		Customerusage queryResult = em.createNamedQuery("Customerusage.findusagebyclientID", Customerusage.class)
				.setParameter("id", clientID)
				.getSingleResult();
		return queryResult;
	}

	public Integer getlatestcollectionadd() {
		Integer col = em.createQuery("select max(c.id) from Collection c", Integer.class).getSingleResult();
		
		return col;
		
	}

	public void assigncratecollection(int clientID, int crate, String date) {
		int collection = this.getlatestcollectionadd();
		Collection c = em.find(Collection.class, collection);
		Crate cr = em.find(Crate.class, crate);
		
		cr.getCollections().add(c);
		c.getCrates().add(cr);
		cr.setStatus("in Transit from client");
		cr.getCratehistories().get(0).setDate_Stored(date);
		em.persist(cr);
		em.persist(c);
	}

	public void createCrate(int clientID, String itemType, String date) {
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
		Cratehistory ch = new Cratehistory();
		
		ch.setCrate(c);
		ch.setDate_Stored(date);
		
		em.persist(ch);
	
	
	}

	public List<Collection> allCollections() {
		List<Collection> Collectionlist = em.createNamedQuery("Collection.findAll", Collection.class).getResultList();
		return Collectionlist;
	}

}
