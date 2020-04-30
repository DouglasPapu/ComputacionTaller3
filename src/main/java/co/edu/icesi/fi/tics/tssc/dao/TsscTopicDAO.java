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
		entityManager.persist(topic);
		
	}

	@Override
	public void update(TsscTopic topic) {
		entityManager.merge(topic);
		
	}

	@Override
	public void delete(TsscTopic topic) {
		entityManager.remove(topic);
		
	}

	@Override
	public List<TsscTopic> findByName(String name) {
		String cons = "Select a from TsscTopic a WHERE a.name = '"+name+"'";
		return entityManager.createQuery(cons).getResultList();
	}

	@Override
	public List<TsscTopic> findByDescription(String description) {
		String cons = "Select a from TsscTopic a WHERE a.description = '"+description+"'";
		return entityManager.createQuery(cons).getResultList();
	}
}
