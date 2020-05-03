package co.edu.icesi.fi.tics.tssc.testDao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.testng.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import co.edu.icesi.fi.tics.tssc.TallerPersistenciaApplication;
import co.edu.icesi.fi.tics.tssc.dao.ITsscGameDAO;
import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.TsscStory;
import co.edu.icesi.fi.tics.tssc.model.TsscTimecontrol;

@SpringBootTest(classes = TallerPersistenciaApplication.class)
@RunWith(SpringRunner.class)
@Rollback(false)
public class TsscGameDaoTest {

	@Autowired
	private ITsscGameDAO gameDao;

	private TsscGame game1;

	private TsscGame game2;

	private TsscStory story1;

	private TsscStory story2;

	private TsscTimecontrol timeControl;

	public void setUp1() {

		game1 = new TsscGame();
		game1.setName("Juego1");
		game1.setNGroups(8);
		game1.setNSprints(8);
		game1.setScheduledDate(LocalDate.of(2020, 04, 27));
		gameDao.save(game1);

	}

	public void escenario2() {

		game1 = new TsscGame();
		game1.setName("Juego1");
		game1.setNGroups(8);
		game1.setNSprints(8);

		game1.setScheduledDate(LocalDate.of(2020, 12, 14));

		game1.setScheduledTime(LocalTime.of(10, 15));

		gameDao.save(game1);

	}

	public void escenario3() {

		game1 = new TsscGame();
		game1.setName("Juego1");
		game1.setNGroups(8);
		game1.setNSprints(8);
		game1.setScheduledDate(LocalDate.of(2020, 12, 14));

		game2 = new TsscGame();
		game2.setName("Juego2");
		game2.setNGroups(1);
		game2.setNSprints(1);
		game2.setScheduledDate(LocalDate.of(2020, 12, 14));

		story1 = new TsscStory();
		story1.setNumber(new BigDecimal(1010));
		story1.setDescription("Soy la historia 1");
		story1.setInitialSprint(new BigDecimal(12));
		story1.setPriority(new BigDecimal(10));
		story1.setBusinessValue(new BigDecimal(5));

		story2 = new TsscStory();
		story2.setNumber(new BigDecimal(1011));
		story2.setDescription("Soy la historia 2");
		story2.setInitialSprint(new BigDecimal(12));
		story2.setPriority(new BigDecimal(10));
		story2.setBusinessValue(new BigDecimal(5));

		game1.getTsscStories().add(story1);
		game1.getTsscStories().add(story2);

		game2.getTsscStories().add(story2);

		gameDao.save(game1);
		gameDao.save(game2);

	}

	public void escenario4() {
		game1 = new TsscGame();
		game1.setName("Juego1");
		game1.setNGroups(8);
		game1.setNSprints(8);
		game1.setScheduledDate(LocalDate.of(2020, 12, 14));

		game2 = new TsscGame();
		game2.setName("Juego1");
		game2.setNGroups(1);
		game2.setNSprints(1);
		game2.setScheduledDate(LocalDate.of(2020, 12, 14));

		story1 = new TsscStory();
		story1.setNumber(new BigDecimal(1010));
		story1.setDescription("Soy la historia 1");
		story1.setInitialSprint(new BigDecimal(12));
		story1.setPriority(new BigDecimal(10));
		story1.setBusinessValue(new BigDecimal(5));

		story2 = new TsscStory();
		story2.setNumber(new BigDecimal(1011));
		story2.setDescription("Soy la historia 2");
		story2.setInitialSprint(new BigDecimal(12));
		story2.setPriority(new BigDecimal(10));
		story2.setBusinessValue(new BigDecimal(5));

		game1.getTsscStories().add(story1);
		game1.getTsscStories().add(story2);

		gameDao.save(game1);

		for (int i = 0; i < 11; i++) {

			game2.getTsscStories().add(new TsscStory());

		}

		List<TsscTimecontrol> cronograma = new ArrayList<TsscTimecontrol>();
		cronograma.add(new TsscTimecontrol());
		game2.setTsscTimecontrol(cronograma);

		gameDao.save(game2);

	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testSaveGame() {
		assertNotNull(gameDao);

		TsscGame gameP = new TsscGame();
		gameP.setName("GameP");
		gameP.setNGroups(1);
		gameP.setNSprints(1);
		gameP.setScheduledDate(LocalDate.of(2020, 04, 27));
		gameDao.save(gameP);

		assertNotNull(gameDao.findById(gameP.getId()));
		assertEquals("GameP", gameDao.findById(gameP.getId()).get(0).getName());

	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testUpdateGame() {
		setUp1();
		assertNotNull(gameDao);

		TsscGame game2 = new TsscGame();
		game2.setName("Juego2");
		game2.setNGroups(3);
		game2.setNSprints(1);
		game2.setScheduledDate(LocalDate.of(2021, 02, 14));

		gameDao.update(game2);

		assertNotNull(gameDao.findByName("Juego2"));
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testDeleteGame() {
		setUp1();
		assertNotNull(gameDao);
		gameDao.delete(game1);
		assertTrue(gameDao.findAll().size() == 0);

	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByNameGame() {
		setUp1();
		assertNotNull(gameDao);

		List<TsscGame> juegos = gameDao.findByName("Juego1");

		if (juegos.size() > 0) {
			assertNotNull(gameDao.findByName("Juego1").get(0));
		} else
			fail();

	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByIdGame() {
		setUp1();
		assertNotNull(gameDao);

		List<TsscGame> juego = gameDao.findById(game1.getId());

		if (juego.get(0) != null) {
			assertNotNull(juego.get(0));
		} else
			fail();

	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByDate() {
		escenario2();
		assertNotNull(gameDao);

		try {
			List<TsscGame> juegos = gameDao.findByDate(LocalDate.of(2020, 12, 14));

			assertTrue(juegos.size() == 1);
		} catch (Exception e) {
			fail();
		}

	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByDateHours() {
		escenario2();
		assertNotNull(gameDao);

		try {
			List<TsscGame> juegos = gameDao.findByDateHours(LocalDate.of(2020, 12, 14), LocalTime.of(10, 15));

			assertTrue(juegos.size() == 1);
		} catch (Exception e) {

			fail();
		}

	}

	// Punto 2 (b)

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testEncontrarJuegoConMenosDe10Historias() {
		escenario3();
		assertNotNull(gameDao);

		List<TsscGame> juegos = gameDao.buscarJuegoConMenosDe10HistoriasOSinCronometros(LocalDate.of(2020, 12, 14));

		if (juegos.size() > 0 && juegos != null) {

			assertEquals(2, juegos.size());

		} else {
			fail();
		}
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testEncontrarJuegoSinCronograma() {
		escenario4();
		assertNotNull(gameDao);

		List<TsscGame> juegos = gameDao.buscarJuegoConMenosDe10HistoriasOSinCronometros(LocalDate.of(2020, 12, 14));

		if (juegos.size() == 1 && juegos != null) {

			assertEquals(1, juegos.size());

		} else
			fail();

	}

}
