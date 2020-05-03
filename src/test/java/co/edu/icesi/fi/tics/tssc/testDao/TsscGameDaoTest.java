package co.edu.icesi.fi.tics.tssc.testDao;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.edu.icesi.fi.tics.tssc.TallerPersistenciaApplication;

@SpringBootTest(classes = TallerPersistenciaApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Rollback(false)
public class TsscGameDaoTest {
	
	
	

}
