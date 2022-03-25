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

    //adds items to collection Items and links them to the collection.
	public void AddItem(String item, int collectionID) {

		CollectionItem ci = new CollectionItem();
		Collection c = em.find(Collection.class, collectionID);
		ci.setItem(item);
		ci.getCollections().add(c);
		c.getCollectionItems().add(ci);
		em.persist(c);
		em.persist(ci);
	
		
	}
//creates collection request and adds customer usage +1 to times collection made.
	public void createCollection(String date, String time, String Frequency, int clientID) {
		
		Collection c = new Collection();
		Client client = em.find(Client.class, clientID);
		c.setDate(date);
		c.setTime(time);
		c.setStatus("In Progress");
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
//gets customer usage by client ID.
	private Customerusage getCustomerUsageByClientID(int clientID) {
		Customerusage queryResult = em.createNamedQuery("Customerusage.findusagebyclientID", Customerusage.class)
				.setParameter("id", clientID)
				.getSingleResult();
		return queryResult;
	}
//gets latest collection request ID for other uses.
	public Integer getlatestcollectionadd() {
		Integer col = em.createQuery("select max(c.id) from Collection c", Integer.class).getSingleResult();
		
		return col;
		
	}
//assigns a crate to the collection when the whole crate is requested to be collected.
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
//creates new crate in case the customer wants the items be collected and stored in the new crate.
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
//gets all collections from the database.
	public List<Collection> allCollections() {
		List<Collection> Collectionlist = em.createNamedQuery("Collection.findAll", Collection.class).getResultList();
		return Collectionlist;
	}
//creates collection request when client wants existing crate to be collected.
	public void createCollectioncrate(String date, String time, String frequency, int clientID) {
		Collection c = new Collection();
		Client client = em.find(Client.class, clientID);
		c.setDate(date);
		c.setTime(time);
		c.setStatus("Existing Crate collection In Progress");
		c.setJourney("Not set yet");
		c.setFrequency(frequency);
		c.setClient(client);
		Client cl = em.find(Client.class, clientID);
		
		int TimesCollectionMade = cl.getCustomerusages().get(0).getTimes_collection_made();
		int Times = TimesCollectionMade +1;
		cl.getCustomerusages().get(0).setTimes_collection_made(Times);
		
		em.persist(c);
		em.persist(cl);
		
	}
//creates collection request when Client requests specific item collection.
	public void createCollectionItems(String date, String time, String frequency, int clientID) {
		Collection c = new Collection();
		Client client = em.find(Client.class, clientID);
		c.setDate(date);
		c.setTime(time);
		c.setStatus("Item collection In Progress");
		c.setJourney("Not set yet");
		c.setFrequency(frequency);
		c.setClient(client);
		Client cl = em.find(Client.class, clientID);
		
		int TimesCollectionMade = cl.getCustomerusages().get(0).getTimes_collection_made();
		int Times = TimesCollectionMade +1;
		cl.getCustomerusages().get(0).setTimes_collection_made(Times);
		
		em.persist(c);
		em.persist(cl);
		
		
	}
 //Creates collection request when client wants new crate to be collected.
	public void createCollectionnewcrate(String date, String time, String frequency, int clientID) {
		Collection c = new Collection();
		Client client = em.find(Client.class, clientID);
		c.setDate(date);
		c.setTime(time);
		c.setStatus("New Crate collection In Progress");
		c.setJourney("Not set yet");
		c.setFrequency(frequency);
		c.setClient(client);
		Client cl = em.find(Client.class, clientID);
		
		int TimesCollectionMade = cl.getCustomerusages().get(0).getTimes_collection_made();
		int Times = TimesCollectionMade +1;
		cl.getCustomerusages().get(0).setTimes_collection_made(Times);
		
		em.persist(c);
		em.persist(cl);
		
		
	}

}
