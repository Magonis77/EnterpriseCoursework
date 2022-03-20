package models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the deliveryitems database table.
 * 
 */
@Entity
@Table(name="deliveryitems")
@NamedQuery(name="Deliveryitem.findAll", query="SELECT d FROM Deliveryitem d")
public class Deliveryitem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String item;

	//bi-directional many-to-many association to Delivery
	@ManyToMany(mappedBy="deliveryitems")
	private List<Delivery> deliveries;

	public Deliveryitem() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItem() {
		return this.item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public List<Delivery> getDeliveries() {
		return this.deliveries;
	}

	public void setDeliveries(List<Delivery> deliveries) {
		this.deliveries = deliveries;
	}

}