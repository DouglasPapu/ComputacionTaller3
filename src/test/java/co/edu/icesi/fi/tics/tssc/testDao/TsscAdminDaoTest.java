package co.edu.icesi.fi.tics.tssc.testDao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.testng.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.fi.tics.tssc.TallerPersistenciaApplication;
import co.edu.icesi.fi.tics.tssc.dao.ITsscAdminDAO;
import co.edu.icesi.fi.tics.tssc.model.TsscAdmin;


@SpringBootTest(classes = TallerPersistenciaApplication.class)
@RunWith(SpringRunner.class)
@Rollback(false)
class TsscAdminDaoTest {

	@Autowired
	private ITsscAdminDAO adminDao;
	
	private TsscAdmin admin1;
	
	public void setUp1() {

		admin1 = new TsscAdmin();
		admin1.setUser("Douglas");

		adminDao.save(admin1);
	}
	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testSaveAdmin() {
		assertNotNull(adminDao);

		TsscAdmin adminP = new TsscAdmin();
		adminDao.save(adminP);

		assertNotNull(adminDao.findById(adminP.getId()).get(0));

	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testUpdateAdmin() {
		setUp1();
		assertNotNull(adminDao);

		TsscAdmin admin2 = new TsscAdmin();

		adminDao.update(admin2);

		assertNotNull(adminDao.findById(admin2.getId()).get(0));
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testDeleteAdmin() {
		setUp1();
		assertNotNull(adminDao);
		adminDao.delete(admin1);
		assertTrue(adminDao.findAll().size() == 0);

	}

}
