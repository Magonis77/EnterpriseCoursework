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
@NamedQuery(name="Client.findAll", query="SELECT c FROM Client c")
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

	//bi-directional many-to-many association to Address
	@ManyToMany
	@JoinTable(
		name="client_address"
		, joinColumns={
			@JoinColumn(name="Client_ID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Address_ID")
			}
		)
	private List<Address> addresses;

	//bi-directional many-to-one association to Customerusage
	@OneToMany(mappedBy="client")
	private List<Customerusage> customerusages;

	//bi-directional many-to-one association to Invoice
	@OneToMany(mappedBy="client")
	private List<Invoice> invoices;

	//bi-directional many-to-one association to Payment
	@OneToMany(mappedBy="client")
	private List<Payment> payments;

	public Client() {
		this.addresses = new ArrayList<Address>();
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

	public List<Address> getAddresses() {
		return this.addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
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

	public List<Payment> getPayments() {
		return this.payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	public Payment addPayment(Payment payment) {
		getPayments().add(payment);
		payment.setClient(this);

		return payment;
	}

	public Payment removePayment(Payment payment) {
		getPayments().remove(payment);
		payment.setClient(null);

		return payment;
	}

}