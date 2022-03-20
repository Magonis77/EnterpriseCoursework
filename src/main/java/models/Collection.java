package models;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the collection database table.
 * 
 */
@Entity
@NamedQueries(
		{
			@NamedQuery(name="Collection.findAll", query="SELECT c FROM Collection c"),
			@NamedQuery(name="Collection.findlatestaddedcollection", query="SELECT max(c.id) FROM Collection c"),
			@NamedQuery(name="Collection.findallitems", query="SELECT c FROM Collection c join fetch c.collectionItems Where c.id=:id "),
			@NamedQuery(name="Collection.findcratebycollectionID", query="SELECT c FROM Collection c join fetch c.crates Where c.id=:id ")


		}
		)


public class Collection implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String collection_Address;

	private String date;

	private String frequency;

	private String journey;

	private String time;

	//bi-directional many-to-one association to Client
	@ManyToOne
	@JoinColumn(name="ClientID")
	private Client client;

	//bi-directional many-to-many association to CollectionItem
	@ManyToMany
	@JoinTable(
		name="collection_collection_items"
		, joinColumns={
			@JoinColumn(name="Collection_ID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="collection_items_ID")
			}
		)
	private List<CollectionItem> collectionItems;

	//bi-directional many-to-many association to Crate
	@ManyToMany
	@JoinTable(
		name="collection_crate"
		, joinColumns={
			@JoinColumn(name="Collection_ID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Crate_ID")
			}
		)
	private List<Crate> crates;

	public Collection() {
		this.collectionItems = new ArrayList<CollectionItem>();
		this.crates = new ArrayList<Crate>();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCollection_Address() {
		return this.collection_Address;
	}

	public void setCollection_Address(String collection_Address) {
		this.collection_Address = collection_Address;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getFrequency() {
		return this.frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getJourney() {
		return this.journey;
	}

	public void setJourney(String journey) {
		this.journey = journey;
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<CollectionItem> getCollectionItems() {
		return this.collectionItems;
	}

	public void setCollectionItems(List<CollectionItem> collectionItems) {
		this.collectionItems = collectionItems;
	}

	public List<Crate> getCrates() {
		return this.crates;
	}

	public void setCrates(List<Crate> crates) {
		this.crates = crates;
	}

}