package nl.computerhok.cruddie;

import nl.computerhok.cruddie.repository.AppserverRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CruddieApplicationTests {

	@Autowired
	AppserverRepository appserverRepository;

	@Test
	public void contextLoads() {
		System.out.println("================== running contextloads ======================");
	}

	@Test
	public void testDelete() {
		appserverRepository.delete(1L);
	}
}
