package models;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the itemslist database table.
 * 
 */
@Entity
@NamedQuery(name="Itemslist.findAll", query="SELECT i FROM Itemslist i")
public class Itemslist implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String item;

	//bi-directional many-to-many association to Crate
	@ManyToMany
	@JoinTable(
		name="itemslist_crate"
		, joinColumns={
			@JoinColumn(name="Item_ID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Crate_ID")
			}
		)
	private List<Crate> crates;

	public Itemslist() {
		this.crates = new ArrayList<Crate>();
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

	public List<Crate> getCrates() {
		return this.crates;
	}

	public void setCrates(List<Crate> crates) {
		this.crates = crates;
	}

}