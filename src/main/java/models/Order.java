package models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the orders database table.
 * 
 */
@Entity
@Table(name="orders")
@NamedQueries(
		{
			@NamedQuery(name="Order.findAll", query="SELECT o FROM Order o"),
			@NamedQuery(name="Order.findOrderByStatus", query = "Select o from Order o Where o.status=:Status"),
			@NamedQuery(name="Order.findOrderByClient", query = "Select o from Order o Where o.clientID=:id")
		})

public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int number;

	private int clientID;

	private String collectionDate;

	@Temporal(TemporalType.DATE)
	private Date date;

	private String status;

	//bi-directional many-to-many association to Branch
	@ManyToMany(mappedBy="orders")
	private List<Branch> branches;

	public Order() {
	}

	public int getNumber() {
		return this.number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getClientID() {
		return this.clientID;
	}

	public void setClientID(int clientID) {
		this.clientID = clientID;
	}

	public String getCollectionDate() {
		return this.collectionDate;
	}

	public void setCollectionDate(String collectionDate) {
		this.collectionDate = collectionDate;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Branch> getBranches() {
		return this.branches;
	}

	public void setBranches(List<Branch> branches) {
		this.branches = branches;
	}

}