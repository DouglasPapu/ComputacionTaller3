package co.edu.icesi.fi.tics.tssc.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope("singleton")
public class TsscTimecontrolDAO implements ITsscTimecontrolDAO{

	@PersistenceContext
	private EntityManager entityManager;
}
