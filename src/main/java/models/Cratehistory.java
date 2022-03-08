package models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the cratehistory database table.
 * 
 */
@Entity
@NamedQuery(name="Cratehistory.findAll", query="SELECT c FROM Cratehistory c")
public class Cratehistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.DATE)
	private Date date_Accessed;

	@Temporal(TemporalType.DATE)
	private Date date_Stored;

	@Column(name="`Storage_Lenght(Days)`")
	private int storage_Lenght_Days_;

	//bi-directional many-to-one association to Crate
	@ManyToOne
	@JoinColumn(name="CrateID")
	private Crate crate;

	public Cratehistory() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate_Accessed() {
		return this.date_Accessed;
	}

	public void setDate_Accessed(Date date_Accessed) {
		this.date_Accessed = date_Accessed;
	}

	public Date getDate_Stored() {
		return this.date_Stored;
	}

	public void setDate_Stored(Date date_Stored) {
		this.date_Stored = date_Stored;
	}

	public int getStorage_Lenght_Days_() {
		return this.storage_Lenght_Days_;
	}

	public void setStorage_Lenght_Days_(int storage_Lenght_Days_) {
		this.storage_Lenght_Days_ = storage_Lenght_Days_;
	}

	public Crate getCrate() {
		return this.crate;
	}

	public void setCrate(Crate crate) {
		this.crate = crate;
	}

}