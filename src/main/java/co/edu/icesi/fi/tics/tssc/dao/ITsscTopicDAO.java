package co.edu.icesi.fi.tics.tssc.dao;

import java.util.List;

import co.edu.icesi.fi.tics.tssc.model.TsscTopic;

public interface ITsscTopicDAO {

	public void save(TsscTopic topic);
	public void update(TsscTopic topic);
	public void delete(TsscTopic topic);
	
	public List<TsscTopic> findByName(String name);
	public List<TsscTopic> findByDescription(String name);
}
