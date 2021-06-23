package testAPI.testcase;


import org.test4j.module.spring.annotations.SpringBeanByName;
import org.test4j.spec.inner.IScenario;
import testAPI.Integration.database;
//
//
//import org.test4j.module.spring.annotations.SpringBeanByName;
//import org.test4j.spec.inner.IScenario;
import org.testng.annotations.Test;


import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class querydata extends database {
    //private static  final String aesKey="xtasz";
    private static  final String aesKey="1234567";


    @Test
    public void test22() {


        Map<String, Object> dataMap = getMap("SELECT * from student LIMIT 1");
        String id = dataMap.get("id").toString();
        System.out.println("id = " + id);
    }



    @SpringBeanByName
    @Test(dataProvider = "story",groups = "jspec")
    public void runScenario(IScenario iScenario) throws Throwable {

    }
}