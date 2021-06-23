package testAPI.testcase;
import testAPI.Integration.AbstractIntegrationTest;
import testAPI.Integration.BaseTest;
import testAPI.Integration.ResultModel;

import org.testng.annotations.Test;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static testAPI.Integration.AbstractIntegrationTest.URL_BI;

public class test extends BaseTest {
    private String returnResult = null;
    @Test
    public  void sss() throws Exception{
        // setUp(URL_BI,TEST_LOGIN,USER_NAME,PASS_WORD);
        // httpPostLogin(URL_BI,TEST_LOGIN,USER_NAME,PASS_WORD);

        String  paramMap= "{\"id\":\"2\"}";
        getPublish(paramMap,null);
        System.out.println(getPublish(paramMap,null));
    }
    //打开公开的链接
    public ResultModel<Map<String, Object>> getPublish(  String paramMap,  String errorCode) throws URISyntaxException {

        String filter = "key=00d91e8e0cca2b76f515926a36db68f5t&phone=13594347817&passwd=123456";
        return returnResult("POSTJSON", URL_BI + "/login", null, null, paramMap, errorCode);
    }

    @Test
    public void test22()  {


//        Map<String, Object> dataMap = getMap("SELECT * from student LIMIT 1");
//        String  id = dataMap.get("id").toString();
//        System.out.println("id = " + id);



        String sqlPrepare = "SELECT * from student LIMIT 1";
        List<Map<String, Object>> resultsDB = this.executeQuery_dataSource(daily_dataSource, sqlPrepare);
        List<String> dataReport_id = new ArrayList();
        String dd="";
        for (Map<String, Object> map : resultsDB) {
            dd=map.get("id").toString();
            // dataReport_id.add(map.get("tree_id").toString());
            System.out.println(dd);
        }
    }
}
