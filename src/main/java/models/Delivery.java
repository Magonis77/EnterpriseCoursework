package models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the delivery database table.
 * 
 */
@Entity
@NamedQuery(name="Delivery.findAll", query="SELECT d FROM Delivery d")
public class Delivery implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.DATE)
	private Date date;

	private String frequency;

	private String journey;

	private String time;

	//bi-directional many-to-many association to Crate
	@ManyToMany
	@JoinTable(
		name="delivery_crate"
		, joinColumns={
			@JoinColumn(name="Delivery_ID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Crate_ID")
			}
		)
	private List<Crate> crates;

	public Delivery() {
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

}