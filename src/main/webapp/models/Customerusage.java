package models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the customerusage database table.
 * 
 */
@Entity
@NamedQueries(
		{
			@NamedQuery(name="Customerusage.findAll", query="SELECT c FROM Customerusage c"),
			@NamedQuery(name="Customerusage.findusagebyclientID", query = "Select c from Customerusage c Where c.client=:id")
		})
public class Customerusage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="`Times collection made`")
	private int times_collection_made;

	@Column(name="`Times delivery made`")
	private int times_delivery_made;

	//bi-directional many-to-one association to Client
	@ManyToOne
	@JoinColumn(name="ClientID")
	private Client client;

	public Customerusage() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTimes_collection_made() {
		return this.times_collection_made;
	}

	public void setTimes_collection_made(int times_collection_made) {
		this.times_collection_made = times_collection_made;
	}

	public int getTimes_delivery_made() {
		return this.times_delivery_made;
	}

	public void setTimes_delivery_made(int times_delivery_made) {
		this.times_delivery_made = times_delivery_made;
	}

	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

}