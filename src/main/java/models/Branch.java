package models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the branch database table.
 * 
 */
@Entity
@NamedQuery(name="Branch.findAll", query="SELECT b FROM Branch b")
public class Branch implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	//bi-directional many-to-one association to Address
	@ManyToOne
	@JoinColumn(name="AddressID")
	private Address address;

	//bi-directional many-to-many association to Order
	@ManyToMany
	@JoinTable(
		name="branch_orders"
		, joinColumns={
			@JoinColumn(name="Branch_ID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="order_Number")
			}
		)
	private List<Order> orders;

	public Branch() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

}