package dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import models.Client;
import models.Collection;
import models.Crate;
import models.Delivery;
import models.Deliveryitem;
import models.Itemslist;

/**
 * Session Bean implementation class DeliveryDTO
 */
@Stateless
@LocalBean
public class DeliveryDTO {
	@PersistenceContext(unitName="EnterpriseCourseWork")
	EntityManager em;
    /**
     * Default constructor. 
     */
    public DeliveryDTO() {
        
    }

	public void createDeliveycrate(String date, String time, String frequency, int clientID) {
		Delivery d = new Delivery();
		Client client = em.find(Client.class, clientID);
		d.setDate(date);
		d.setTime(time);
		d.setStatus("Existing Crate Delivery In Progress");
		d.setJourney("Not recorded yet");
		d.setFrequency(frequency);
		d.setClient(client);
		Client cl = em.find(Client.class, clientID);
		
		int TimesCollectionMade = cl.getCustomerusages().get(0).getTimes_delivery_made();
		int Times = TimesCollectionMade +1;
		cl.getCustomerusages().get(0).setTimes_delivery_made(Times);
		
		em.persist(d);
		em.persist(cl);
		
	}

	public void assigncratedelivery(int clientID, int crate, String date) {
		int delivery = this.getlatestdeliveryadd();
		Delivery d = em.find(Delivery.class, delivery);
		Crate cr = em.find(Crate.class, crate);
		
		cr.getDeliveries().add(d);
		d.getCrates().add(cr);
		cr.setStatus("in Transit from client");
		cr.getCratehistories().get(0).setDate_Accessed(date);
		em.persist(cr);
		em.persist(d);
	}

	public int getlatestdeliveryadd() {
		Integer col = em.createQuery("select max(d.id) from Delivery d", Integer.class).getSingleResult();
		
		return col;
	}

	public void createDeliveyspecificitems(String date, String time, String frequency, int clientID) {
		Delivery d = new Delivery();
		Client client = em.find(Client.class, clientID);
		d.setDate(date);
		d.setTime(time);
		d.setStatus("Specific item Delivery In Progress");
		d.setJourney("Not recorded yet");
		d.setFrequency(frequency);
		d.setClient(client);
		Client cl = em.find(Client.class, clientID);
		
		int TimesCollectionMade = cl.getCustomerusages().get(0).getTimes_delivery_made();
		int Times = TimesCollectionMade +1;
		cl.getCustomerusages().get(0).setTimes_delivery_made(Times);
		
		em.persist(d);
		em.persist(cl);
	}

	public List<Itemslist> getItemsbyCrateID(int crate) {
		
		 List<Itemslist> itemslist = em.createNamedQuery("Itemslist.findallitemsbycrateID", Itemslist.class).setParameter("id", crate).getResultList();
		return itemslist;
	}

	public void adddeliveryitems(int itemID, int deliveryID) {
		
		Itemslist it = em.find(Itemslist.class, itemID);
		String item = it.getItem().toString();
		Deliveryitem Di = new Deliveryitem();
		
		Di.setItem(item);
		
		Delivery d = em.find(Delivery.class, deliveryID);
		
		Di.getDeliveries().add(d);
		em.persist(Di);
		d.getDeliveryitems().add(Di);
		em.persist(d);

		
	}

	public List<Delivery> allDeliveries() {
		List<Delivery> Deliverylist = em.createNamedQuery("Delivery.findAll", Delivery.class).getResultList();
		return Deliverylist;
	}

	public List<Delivery> getalldeliverycrates() {
		List<Delivery> deliverylist = em.createNamedQuery("Delivery.getallcrates", Delivery.class).getResultList();
		return deliverylist;
	}

}
