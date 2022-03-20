package models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the collection_items database table.
 * 
 */
@Entity
@Table(name="collection_items")
@NamedQuery(name="CollectionItem.findAll", query="SELECT c FROM CollectionItem c")
public class CollectionItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String item;

	//bi-directional many-to-many association to Collection
	@ManyToMany(mappedBy="collectionItems")
	private List<Collection> collections;

	public CollectionItem() {
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

	public List<Collection> getCollections() {
		return this.collections;
	}

	public void setCollections(List<Collection> collections) {
		this.collections = collections;
	}

}