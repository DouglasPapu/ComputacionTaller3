package co.edu.icesi.fi.tics.tssc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.fi.tics.tssc.model.TsscTopic;

@Repository
@Scope("singleton")
public class TsscTopicDAO implements ITsscTopicDAO{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(TsscTopic topic) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(TsscTopic topic) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(TsscTopic topic) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<TsscTopic> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TsscTopic> findByDescription(String name) {
		// TODO Auto-generated method stub
		return null;
	}
}
