package models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the collection database table.
 * 
 */
@Entity

@NamedQueries(
		{
			@NamedQuery(name="Collection.findAll", query="SELECT c FROM Collection c"),
			@NamedQuery(name="Collection.findCollectionbyaddress", query="Select c from Collection c Where c.collection_Address=:Address")

		}
		)
public class Collection implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String collection_Address;

	@Temporal(TemporalType.DATE)
	private Date date;

	private String frequency;

	private String journey;

	private String time;

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

	public Collection() {
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

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
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

	public List<Crate> getCrates() {
		return this.crates;
	}

	public void setCrates(List<Crate> crates) {
		this.crates = crates;
	}

	public List<CollectionItem> getCollectionItems() {
		return this.collectionItems;
	}

	public void setCollectionItems(List<CollectionItem> collectionItems) {
		this.collectionItems = collectionItems;
	}

}