package dao;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import models.Address;
import models.Client;
import models.Crate;
import models.Cratehistory;
import models.Customerusage;
import models.Itemslist;
import models.Order;

/**
 * Session Bean implementation class BranchDTO
 */
@Stateless
@LocalBean
public class BranchDTO {
	@PersistenceContext(unitName="EnterpriseCourseWork")
	EntityManager em;
    /**
     * Default constructor. 
     */
    public BranchDTO() {
        // TODO Auto-generated constructor stub
    }

}
