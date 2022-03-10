package models;

import java.io.Serializable;
import javax.persistence.*;


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

	private String date_Accessed;

	private String date_Stored;

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

	public String getDate_Accessed() {
		return this.date_Accessed;
	}

	public void setDate_Accessed(String date_Accessed) {
		this.date_Accessed = date_Accessed;
	}

	public String getDate_Stored() {
		return this.date_Stored;
	}

	public void setDate_Stored(String date_Stored) {
		this.date_Stored = date_Stored;
	}

	public Crate getCrate() {
		return this.crate;
	}

	public void setCrate(Crate crate) {
		this.crate = crate;
	}

}