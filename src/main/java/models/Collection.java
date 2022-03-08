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
@NamedQuery(name="Collection.findAll", query="SELECT c FROM Collection c")
public class Collection implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.DATE)
	private Date date;

	private String journey;

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
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getJourney() {
		return this.journey;
	}

	public void setJourney(String journey) {
		this.journey = journey;
	}

	public List<Crate> getCrates() {
		return this.crates;
	}

	public void setCrates(List<Crate> crates) {
		this.crates = crates;
	}

}