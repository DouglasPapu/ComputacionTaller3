package co.edu.icesi.fi.tics.tssc.testDao;

import static org.junit.Assert.*;
import static org.testng.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.fi.tics.tssc.TallerPersistenciaApplication;
import co.edu.icesi.fi.tics.tssc.dao.ITsscGameDAO;
import co.edu.icesi.fi.tics.tssc.dao.ITsscTopicDAO;
import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.TsscTopic;

@SpringBootTest(classes = TallerPersistenciaApplication.class)
@RunWith(SpringRunner.class)
@Rollback(false)
public class TsscTopicDaoTest {

	@Autowired
	private ITsscTopicDAO topicDao;
	
	@Autowired
	private ITsscGameDAO gameDao;
	
	private TsscTopic topic1;
	
	private TsscTopic topic2;
	
	
	public void setUp1() {
		
		topic1 = new TsscTopic();
		topic1.setDefaultGroups(4);
		topic1.setDefaultSprints(4);
		topic1.setName("topic1");
		topic1.setGroupPrefix("Prefix1");
		topic1.setDescription("Descripcion topic1");
		
		topicDao.save(topic1);
	}
	
	public void escenario2() {
		
		TsscGame game1 = new TsscGame();
		game1.setName("Juego1");
		game1.setNGroups(8);
		game1.setNSprints(8);
		game1.setScheduledDate(LocalDate.of(2020, 12, 14));
		game1.setScheduledTime(LocalTime.of(10, 5));

		TsscGame game2 = new TsscGame();
		game2.setName("Juego2");
		game2.setNGroups(1);
		game2.setNSprints(1);
		game2.setScheduledDate(LocalDate.of(2020, 12, 14));
		game2.setScheduledTime(LocalTime.of(5, 10));
		
		
		topic1 = new TsscTopic();
		topic1.setDefaultGroups(4);
		topic1.setDefaultSprints(4);
		topic1.setName("topic1");
		topic1.setGroupPrefix("Prefix1");
		topic1.setDescription("Descripcion topic1");
		
		topic2 = new TsscTopic();
		topic2.setDefaultGroups(4);
		topic2.setDefaultSprints(4);
		topic2.setName("topic1");
		topic2.setGroupPrefix("Prefix1");
		topic2.setDescription("Descripcion topic1");
		
		topic1.getTsscGames().add(game1);
		topic1.getTsscGames().add(game2);
		
		topic2.getTsscGames().add(game2);
		
		topicDao.save(topic2);		
		topicDao.save(topic2);
		
	}

	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testSaveTopic() {
		assertNotNull(topicDao);

		TsscTopic topicP = new TsscTopic();
		topicP.setName("topicP");
		topicP.setDefaultGroups(4);
		topicP.setDefaultSprints(4);
		topicDao.save(topicP);

		assertNotNull(topicDao.findById(topicP.getId()));
		assertEquals("topicP", topicDao.findById(topicP.getId()).get(0).getName());

	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testUpdateTopic() {
		setUp1();
		assertNotNull(topicDao);

		TsscTopic topic2 = new TsscTopic();
		topic2.setName("topic2");
		topic2.setDefaultGroups(20);
		topic2.setDefaultSprints(20);

		topicDao.update(topic2);

		assertNotNull(topicDao.findByName("topic2"));
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testDeleteTopic() {
		setUp1();
		assertNotNull(topicDao);
		topicDao.delete(topic1);
		assertTrue(topicDao.findAll().size() == 0);

	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByIdTopic() {
		setUp1();
		assertNotNull(topicDao);
		
		assertEquals(1, topicDao.findById(topic1.getId()).size());
	}
	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByNameTopic() {
	  setUp1();
	  assertNotNull(topicDao);
	
	  assertTrue(topicDao.findByName("topic1").size() == 1);
	}
	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByDescriptionTopic() {
	  setUp1();
	  assertNotNull(topicDao);
	  
	  assertTrue(topicDao.findByDescription("Descripcion topic1").size() == 1);
		
	}
	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindTopicByScheduledGames() {
		
		try {
		escenario2();
		
		assertNotNull(gameDao);
		
		assertNotNull(gameDao.findTopicByScheduledGames(LocalDate.of(2020, 12, 14)).get(0));
		
		}catch(Exception e) {
			
			fail();
		}
		
	}
	
}
