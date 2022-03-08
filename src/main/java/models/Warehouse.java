package models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the warehouse database table.
 * 
 */
@Entity
@NamedQuery(name="Warehouse.findAll", query="SELECT w FROM Warehouse w")
public class Warehouse implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int capacity;

	//bi-directional many-to-one association to Crate
	@OneToMany(mappedBy="warehouse")
	private List<Crate> crates;

	//bi-directional many-to-one association to Address
	@ManyToOne
	@JoinColumn(name="AddressID")
	private Address address;

	public Warehouse() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCapacity() {
		return this.capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public List<Crate> getCrates() {
		return this.crates;
	}

	public void setCrates(List<Crate> crates) {
		this.crates = crates;
	}

	public Crate addCrate(Crate crate) {
		getCrates().add(crate);
		crate.setWarehouse(this);

		return crate;
	}

	public Crate removeCrate(Crate crate) {
		getCrates().remove(crate);
		crate.setWarehouse(null);

		return crate;
	}

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}