package models;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the crate database table.
 * 
 */
@Entity
@NamedQueries(
		{
			@NamedQuery(name="Crate.findAll", query="SELECT c FROM Crate c"),
			@NamedQuery(name="Crate.findCrateByClientID", query = "Select c from Crate c Where c.clientID=:id")
		})

public class Crate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int clientID;

	@Column(name="`Item Type`")
	private String item_Type;

	private String shelf;

	private String status;

	//bi-directional many-to-many association to Collection
	@ManyToMany(mappedBy="crates")
	private List<Collection> collections;

	//bi-directional many-to-one association to Cratehistory
	@OneToMany(mappedBy="crate")
	private List<Cratehistory> cratehistories;

	//bi-directional many-to-many association to Delivery
	@ManyToMany(mappedBy="crates")
	private List<Delivery> deliveries;

	//bi-directional many-to-many association to Itemslist
	@ManyToMany(mappedBy="crates")
	private List<Itemslist> itemslists;

	//bi-directional many-to-many association to Warehouse
	@ManyToMany(mappedBy="crates")
	private List<Warehouse> warehouses;

	public Crate() {
		this.warehouses = new ArrayList<Warehouse>();
		this.itemslists = new ArrayList<Itemslist>();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getClientID() {
		return this.clientID;
	}

	public void setClientID(int clientID) {
		this.clientID = clientID;
	}

	public String getItem_Type() {
		return this.item_Type;
	}

	public void setItem_Type(String item_Type) {
		this.item_Type = item_Type;
	}

	public String getShelf() {
		return this.shelf;
	}

	public void setShelf(String shelf) {
		this.shelf = shelf;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Collection> getCollections() {
		return this.collections;
	}

	public void setCollections(List<Collection> collections) {
		this.collections = collections;
	}

	public List<Cratehistory> getCratehistories() {
		return this.cratehistories;
	}

	public void setCratehistories(List<Cratehistory> cratehistories) {
		this.cratehistories = cratehistories;
	}

	public Cratehistory addCratehistory(Cratehistory cratehistory) {
		getCratehistories().add(cratehistory);
		cratehistory.setCrate(this);

		return cratehistory;
	}

	public Cratehistory removeCratehistory(Cratehistory cratehistory) {
		getCratehistories().remove(cratehistory);
		cratehistory.setCrate(null);

		return cratehistory;
	}

	public List<Delivery> getDeliveries() {
		return this.deliveries;
	}

	public void setDeliveries(List<Delivery> deliveries) {
		this.deliveries = deliveries;
	}

	public List<Itemslist> getItemslists() {
		return this.itemslists;
	}

	public void setItemslists(List<Itemslist> itemslists) {
		this.itemslists = itemslists;
	}

	public List<Warehouse> getWarehouses() {
		return this.warehouses;
	}

	public void setWarehouses(List<Warehouse> warehouses) {
		this.warehouses = warehouses;
	}

}