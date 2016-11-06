package nl.computerhok.cruddie;

import nl.computerhok.cruddie.entity.Appserver;
import nl.computerhok.cruddie.entity.Appservergroup;
import nl.computerhok.cruddie.repository.AppserverRepository;
import nl.computerhok.cruddie.repository.AppservergroupRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CruddieApplicationTests {

    @Autowired
    AppserverRepository appserverRepository;

    @Autowired
    AppservergroupRepository appservergroupRepository;

    @Test
    public void contextLoads() {
        System.out.println("================== running contextloads ======================");
    }

    @Test
    public void testDelete() {
        appserverRepository.delete(1L);
    }

    @Test
    public void testInsert() {
        Appservergroup testAg = appservergroupRepository.findOne(1L);
        Appserver as = new Appserver("testhostname", "test jvmargs", Appserver.Location.Boxtel, testAg, "metskeh");
        Appserver savedAs = appserverRepository.save(as);
        System.out.println("saved " + savedAs);
        assertEquals("hostname not correct","testhostname",savedAs.getHostname());
        assertTrue("lastchanged not correct",new Date().after(savedAs.getLastchanged()));
    }
}
