package nl.computerhok.cruddie.api.controller;

import nl.computerhok.cruddie.entity.Appservergroup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AppservergroupController.class)
public class AppservergroupControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private AppservergroupController appserverController;

    @Test
    public void test() throws Exception {
        Date testDate = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        String testDateStr = df.format(testDate);

        Appservergroup testAppservergroup = new Appservergroup("tl99", Appservergroup.Stage.t,"testuser");
        testAppservergroup.setCreated(testDate);
        testAppservergroup.setLastchanged(testDate);

        given(this.appserverController.read(5L)).willReturn(testAppservergroup);

        this.mvc.perform(get(AppservergroupController.PATH + "/5").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().json("{\"created\":\""+testDateStr+"\",\"lastchanged\":\""+testDateStr+"\",\"lastchangedby\":\"testuser\",\"id\":null,\"name\":\"tl99\",\"stage\":\"t\"}"));
    }

}
