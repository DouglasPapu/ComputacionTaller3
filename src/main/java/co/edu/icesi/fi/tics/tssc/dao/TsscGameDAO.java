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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(TsscGame game) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(TsscGame game) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<TsscGame> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TsscGame> findByDescription(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TsscGame> findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TsscGame> findByDate(LocalDate scheduleDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TsscGame> findByDateHours(LocalDate scheduleDate, LocalTime scheduleTime) {
		// TODO Auto-generated method stub
		return null;
	}
}
