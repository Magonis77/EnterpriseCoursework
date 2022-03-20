package models;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the client database table.
 * 
 */
@Entity
@NamedQueries(
		{
@NamedQuery(name="Client.findAll", query="SELECT c FROM Client c"),
@NamedQuery(name="Client.findCustUsagebyID", query="Select c from Client c join fetch c.customerusages Where c.id=:id"),
@NamedQuery(name="Client.findCustInvoicebyID", query="Select c from Client c join fetch c.invoices Where c.id=:id")

		}
		)
public class Client implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String emailaddress;

	private String firstName;

	private String lastName;

	private String password;

	private int phoneNumber;

	private String username;

	//bi-directional many-to-one association to Address
	@ManyToOne
	private Address address;

	//bi-directional many-to-one association to Collection
	@OneToMany(mappedBy="client")
	private List<Collection> collections;

	//bi-directional many-to-one association to Customerusage
	@OneToMany(mappedBy="client")
	private List<Customerusage> customerusages;

	//bi-directional many-to-one association to Delivery
	@OneToMany(mappedBy="client")
	private List<Delivery> deliveries;

	//bi-directional many-to-one association to Invoice
	@OneToMany(mappedBy="client")
	private List<Invoice> invoices;

	public Client() {
		this.customerusages = new ArrayList<Customerusage>();
		this.invoices = new ArrayList<Invoice>();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmailaddress() {
		return this.emailaddress;
	}

	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Collection> getCollections() {
		return this.collections;
	}

	public void setCollections(List<Collection> collections) {
		this.collections = collections;
	}

	public Collection addCollection(Collection collection) {
		getCollections().add(collection);
		collection.setClient(this);

		return collection;
	}

	public Collection removeCollection(Collection collection) {
		getCollections().remove(collection);
		collection.setClient(null);

		return collection;
	}

	public List<Customerusage> getCustomerusages() {
		return this.customerusages;
	}

	public void setCustomerusages(List<Customerusage> customerusages) {
		this.customerusages = customerusages;
	}

	public Customerusage addCustomerusage(Customerusage customerusage) {
		getCustomerusages().add(customerusage);
		customerusage.setClient(this);

		return customerusage;
	}

	public Customerusage removeCustomerusage(Customerusage customerusage) {
		getCustomerusages().remove(customerusage);
		customerusage.setClient(null);

		return customerusage;
	}

	public List<Delivery> getDeliveries() {
		return this.deliveries;
	}

	public void setDeliveries(List<Delivery> deliveries) {
		this.deliveries = deliveries;
	}

	public Delivery addDelivery(Delivery delivery) {
		getDeliveries().add(delivery);
		delivery.setClient(this);

		return delivery;
	}

	public Delivery removeDelivery(Delivery delivery) {
		getDeliveries().remove(delivery);
		delivery.setClient(null);

		return delivery;
	}

	public List<Invoice> getInvoices() {
		return this.invoices;
	}

	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}

	public Invoice addInvoice(Invoice invoice) {
		getInvoices().add(invoice);
		invoice.setClient(this);

		return invoice;
	}

	public Invoice removeInvoice(Invoice invoice) {
		getInvoices().remove(invoice);
		invoice.setClient(null);

		return invoice;
	}

}