package co.edu.icesi.fi.tics.tssc.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.TsscTopic;

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
	public List<TsscGame> findByDate(LocalDate scheduledDate) {
		String cons = "Select a from TsscGame a WHERE a.scheduledDate = '"+scheduledDate+"'";
		return entityManager.createQuery(cons).getResultList();
	}

	@Override
	public List<TsscGame> findByDateHours(LocalDate scheduledDate, LocalTime scheduledTime) {
		String cons = "Select a from TsscGame a WHERE a.scheduledDate = '"+scheduledDate+"'"+" AND a.scheduledTime = '"+scheduledTime+"'";
		return entityManager.createQuery(cons).getResultList();
	}

	//Método que se encarga de encontrar los topics que están asociados a un juego programado en una fecha, ordenados por hora.
	
	@Override
	public List<Object[]> findTopicByScheduledGames(LocalDate scheduledDate) {
	//	String q = "select s.tsscTopic, Count(s) from TsscGame s where "
    //				+ "date = s.scheduledDate ORDER BY s.scheduledTime ASC ";
		String q = "Select a, Count(s) FROM TsscTopic a JOIN TsscGame s ON a.id = s.id.tsscTopic.id WHERE s.scheduledDate = date"
				+ "ORDER BY s.scheduledTime ASC";
		
		TypedQuery<Object[]> query = entityManager.createQuery(q, Object[].class);
		query.setParameter("date", scheduledDate);


		return query.getResultList();
	}
	
	//Mostrar los juegos que están programados para una fecha pero tienen menos de diez historias asociadas para una fecha dada o no tienen
	// al menos un crónometro.
	
	public List<TsscGame> buscarJuegoConMenosDe10HistoriasOSinCronometros(LocalDate scheduledDate){
		
		String query = "Select a from TsscGame a Where "+ "(a.scheduledDate = scheduledDate AND (a.tsscTimecontrols is null OR ("
				+ "SELECT Count(b) FROM TsscTimecontrol b WHERE b.tsscGame.id = a.id) = 0) OR "+
		"( SELECT Count(s) FROM TsscStory s WHERE s.tsscGame.id = a.id AND"+
							  "s.tsscGame.scheduledDate = scheduledDate) < 10";
		
		TypedQuery<TsscGame> q = entityManager.createQuery(query, TsscGame.class);
		
		q.setParameter("scheduledDate", scheduledDate);
		
		return q.getResultList();
	}

	@Override
	public List<TsscGame> findAll() {
		String jpql = "Select a FROM TsscGame a";
		return entityManager.createQuery(jpql, TsscGame.class).getResultList();
	}

	
	
	
}
