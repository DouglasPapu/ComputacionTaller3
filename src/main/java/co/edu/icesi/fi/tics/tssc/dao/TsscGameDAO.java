package co.edu.icesi.fi.tics.tssc.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.fi.tics.tssc.model.TsscGame;

@Repository
@Scope("singleton")
public class TsscGameDAO implements ITsscGameDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(TsscGame game) {
		entityManager.persist(game);
		
	}

	@Override
	public void update(TsscGame game) {
		entityManager.merge(game);
		
	}

	@Override
	public void delete(TsscGame game) {
		entityManager.remove(game);
		
	}

	@Override
	public List<TsscGame> findByName(String name) {
		String cons = "Select a from TsscGame a WHERE a.name = '"+name+"'";
		return entityManager.createQuery(cons).getResultList();
	}

	@Override
	public List<TsscGame> findByDescription(String description) {
		//String cons = "Select a from TsscGame a WHERE a.description = '"+description+"'";
		//return entityManager.createQuery(cons).getResultList();
		return null;
	}

	@Override
	public List<TsscGame> findById(long id) {
		String cons = "Select a from TsscGame a WHERE a.tsscTopic.id = '"+id+"'";
		return entityManager.createQuery(cons).getResultList();
	}

	@Override
	public List<TsscGame> findByDate(LocalDate scheduleDate) {
		String cons = "Select a from TsscGame a WHERE a.scheduleDate = '"+scheduleDate+"'";
		return entityManager.createQuery(cons).getResultList();
	}

	@Override
	public List<TsscGame> findByDateHours(LocalDate scheduleDate, LocalTime scheduleTime) {
		String cons = "Select a from TsscGame a WHERE a.scheduleDate = '"+scheduleDate+"'"+" AND a.scheduleTime = '"+scheduleTime+"'";
		return entityManager.createQuery(cons).getResultList();
	}
}
