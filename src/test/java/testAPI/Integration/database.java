package testAPI.Integration;


import org.test4j.module.database.utility.SqlRunner;
import org.test4j.spec.inner.IScenario;
import org.test4j.testng.JSpec;

import java.util.Map;


public abstract class database extends JSpec {
    protected Map<String, Object> getMap(String sql) {
        Map<String, Object> object = SqlRunner.instance.queryMap(sql);
        return object;
    }

}