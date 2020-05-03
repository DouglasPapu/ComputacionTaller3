package co.edu.icesi.fi.tics.tssc.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import co.edu.icesi.fi.tics.tssc.model.TsscGame;

public interface ITsscGameDAO {

	public void save(TsscGame game);
	public void update(TsscGame game);
	public void delete(TsscGame game);
	
	public List<TsscGame> findByName(String name);
	public List<TsscGame> findByDescription(String description);
	public List<TsscGame> findById(long id);
	
	//Search by range of date.
	
	public List<TsscGame> findByDate(LocalDate scheduledDate);
	
	//Search by date and time.
	
	public List<TsscGame> findByDateHours(LocalDate scheduledDate, LocalTime scheduledTime);
	
	//Search topics by schudeled games.
	
	public List<Object[]> findTopicByScheduledGames(LocalDate scheduledDate);
	
	//Search games by stories
	
	public List<Object[]> findGameByStory(LocalDate scheduledDate);
	
	//Search games by Stories less than 10 or not timecontrols.
	
	public List<TsscGame> buscarJuegoConMenosDe10HistoriasOSinCronometros(LocalDate scheduledDate);
}

