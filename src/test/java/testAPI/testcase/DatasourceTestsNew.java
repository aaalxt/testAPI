package testAPI.testcase;


import com.alibaba.fastjson.JSONArray;

import com.alibaba.fastjson.JSONObject;
import testAPI.context.ConnectionConfig;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import org.testng.Assert;

import org.testng.annotations.Test;
import testAPI.Integration.BaseTest;


import java.io.UnsupportedEncodingException;

import java.net.URISyntaxException;

import java.text.ParseException;

import java.text.SimpleDateFormat;

import java.util.*;

import static testAPI.testcase.DataSourceEnum.setDataSource;


public class DatasourceTestsNew extends BaseTest {


    protected static final Logger LOGGER = LoggerFactory.getLogger(DatasourceTestsNew.class);


    private SimpleDateFormat df = new SimpleDateFormat("MMdd");//设置日期格式

    private String nameTemp = df.format(new Date());

    private String organizationID;

    private String workspaceId;

    private String dsId;

    private String viewName;

    private String version = "V2";

    private String defaultWorkSpaceName = "mysqlDatasourceTest";


    @Test(groups = {"DatasourceTest"}, priority = 0)

    public void cleanData() throws UnsupportedEncodingException, URISyntaxException {

        deleteAllCube();

        testdelete();

    }


    @Test(groups = {"DatasourceTest"}, dataProvider = "dataSourceEnum", dataProviderClass = DataSourceEnum.class, priority = 1)

    public void mysqlDatasourceTest(String platform, String dbname, JSONObject jsonObject) throws ParseException,

            UnsupportedEncodingException, URISyntaxException, InterruptedException {

        // ConnectionConfig dataSource = new ConnectionConfig();


        Map<String, Object> data = workSpaceWrapper.getorganizationId("method=get", version, null).getData();

        Map<String, Object> map = (Map) data.get("oganization");

        //  Map<String, Object> map2 = (Map) map.get("oganization");

        organizationID = (String) map.get("organizationId");


        //获取空间id

        workspaceId = workSpaceWrapper.createWorkspacedemo(defaultWorkSpaceName, organizationID, null);

        // 专业版空间

        baseBizContext.setOtherWorkspaceId(workspaceId);// test_ali_11@123.com 账号的 qbi测试空间


        setDataSource(dataSource, jsonObject);

        generalTest(dbname, dataSource, jsonObject);

    }


    public void generalTest(String dbname, ConnectionConfig dataSource, JSONObject jsonObject) throws ParseException,

            UnsupportedEncodingException, URISyntaxException {


        // 测试连通性

        System.out.println("    测试连通性");

        Boolean status = dataSourceWrapper.detectDatasource(dataSource, null, baseBizContext.getOtherWorkspaceId()).getData();

        Assert.assertEquals(status, Boolean.TRUE, "数据源" + dbname + "连通性失败");


        // 新增---地址一样

        System.out.println("    新增---地址一样");

        dataSource.setShowName(nameTemp + "_" + jsonObject.get("db.showName") + "_1");

        dataSourceWrapper.addDatasource(dataSource, "AE0510260005", baseBizContext.getOtherWorkspaceId());// 配置信息已添加


        String tablename = "company_sales_record";

        Map<String, Object> folderList = dashBoardsWrapper.getAllTableListPagingnew(workspaceId, dsId, tablename, param, null).getData();

        ArrayList<Map<String, Object>> listhahah = (ArrayList<Map<String, Object>>) folderList.get("data");

        ArrayList<Map<String, Object>> cubefolderList = dashBoardsWrapper.getFolderList("cube", "V2", workspaceId, "", "", "", null).getData();

        String pid = null;

        for (Map<String, Object> m : cubefolderList) {

            if (m.get("name").toString().equals("我的数据集")) {

                pid = m.get("id").toString();

            }

        }

        //新建数据集

//        for(Map<String, Object> m : listhahah) {//获取数据源table

//            if (m.get("name").equals("company_sales_record")) {

//                String filter = "workspaceId=" + workspaceId;

//                com.alibaba.fastjson.JSONObject paramsnew = new com.alibaba.fastjson.JSONObject();

//                paramsnew.put("aimDirId",pid);

//                paramsnew.put("tableName", tablename);

//                paramsnew.put("dsId", dsId);

//                paramsnew.put("userDefineCubeName", jsonObject.get("db.showName"));

//                ResultModel<String> re = dashBoardsWrapper.createCube(filter, "V2", com.alibaba.fastjson.JSONObject.toJSONString(paramsnew), null);

//               break;

//            }

//        }

//        if(jsonObject.get("db.dsType").equals("oss")){

//            Assert.assertTrue(tableInfos.getData()==null);

//

//        }

        //    Assert.assertTrue(tableInfos.getData().size() > 0 );// 判断请求状态成功 200


        // 删除---删除不存在的

//        System.out.println("    删除---删除不存在的");

//        dataSourceWrapper.deleteDataSource("1234567890", "AE0510200019", baseBizContext.getOtherWorkspaceId())

//                .getData();// 您操作的对象不存在或已被删除


//        // 删除---删除已经创建的

//        System.out.println("    删除---删除已经创建的");

//            Boolean delete_Status_2 = dataSourceWrapper.deleteDataSource(dsId,workspaceId,null).getData();

//            Assert.assertTrue(delete_Status_2);


    }


    @Test(groups = {"DatasourceTest"}, priority = 2)

    public void deleteAllCube() throws URISyntaxException, UnsupportedEncodingException {

        workspaceId = workSpaceWrapper.createWorkspacedemo(defaultWorkSpaceName, organizationID, null);

        ArrayList<Map<String, Object>> schemaList = dataSetWrapper.getFolderList(workspaceId, "", "", "", "", null).getData();

        for (Map<String, Object> schema : schemaList) {

            String schemaId = schema.get("id").toString();

            ArrayList<Map<String, Object>> cubeList = dataSetWrapper.getFolderList(workspaceId, schemaId, "", "", "", null).getData();

            for (Map<String, Object> cube : cubeList) {

                String cubeId = cube.get("id").toString();

                dashBoardsWrapper.deleteObject("cube", "V2", workspaceId, cubeId, null);

            }

        }

    }


    @Test(groups = {"DatasourceTest"}, dataProvider = "dataSourceEnum", dataProviderClass = DataSourceEnum.class, priority = 3)

    public void sqlTest(String platform, String dbname, JSONObject jsonObject) throws URISyntaxException, UnsupportedEncodingException {

//        if (!dbname.equals("selfBuild_Presto")) {

//            return;

//        }

        workspaceId = workSpaceWrapper.createWorkspacedemo(defaultWorkSpaceName, organizationID, null);

        ArrayList<Map<String, Object>> connections = dataSourceWrapper.getDataSoureConnections(workspaceId, null).getData();

        String dsId = "";

        for (Map<String, Object> connection : connections) {

            if (connection.get("showName").toString().contains(jsonObject.get("db.showName").toString())) {

                dsId = connection.get("dsId").toString();

                break;

            }

        }


        JSONArray jsonArray = (JSONArray) jsonObject.get("db.sql");

        if (jsonArray == null) {

            return;

        }

        for (int i = 0; i < jsonArray.size(); i++) {

            JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);

            Boolean re = dataSourceWrapper.sqlPreview(workspaceId, dsId, jsonObject1.get("sql").toString(), null).getSuccess();

            Assert.assertEquals(re, Boolean.TRUE, "数据源" + jsonObject.get("db.showName").toString() + i + " sql连通性失败");

            re = dataSourceWrapper.customsqlCube(workspaceId, dsId, jsonObject.get("db.showName").toString() + i,

                    jsonObject1.get("sql").toString(), jsonObject1.get("params").toString(), null).getSuccess();

            Assert.assertEquals(re, Boolean.TRUE, "数据源" + jsonObject.get("db.showName").toString() + i + " sql创建失败");

        }

    }


    @Test(groups = {"DatasourceTest"}, dataProviderClass = DataSourceEnum.class, priority = 5)

    public void testdelete() throws URISyntaxException, UnsupportedEncodingException {

        deleteAllCube();

        Map<String, Object> data = workSpaceWrapper.getorganizationId("method=get", version, null).getData();

        Map<String, Object> map = (Map) data.get("oganization");

        organizationID = (String) map.get("organizationId");


        //获取空间id

        workspaceId = workSpaceWrapper.createWorkspacedemo(defaultWorkSpaceName, organizationID, null);

        System.out.println("Defalut worksapceId : " + workspaceId);

        //获取数据源列表id

        ArrayList<Map<String, Object>> datasource = dataSourceWrapper.getDataSoureConnections(workspaceId, null).getData();

        for (Map<String, Object> m : datasource) {

            dsId = m.get("dsId").toString();

            System.out.println(dsId);

            Boolean delete_Status_2 = dataSourceWrapper.deleteDataSource(dsId, workspaceId, null).getData();

            Assert.assertTrue(delete_Status_2);

        }

    }


    @Test(groups = {"DatasourceTest"}, dataProviderClass = DataSourceEnum.class, priority = 4)

    public void testQuery() throws URISyntaxException, UnsupportedEncodingException {

        Map<String, Object> data = workSpaceWrapper.getorganizationId("method=get", version, null).getData();

        Map<String, Object> map = (Map) data.get("oganization");

        organizationID = (String) map.get("organizationId");

        //获取空间id

        workspaceId = workSpaceWrapper.createWorkspacedemo(defaultWorkSpaceName, organizationID, null);

        //后期需要传递过来空间id和数据源id以及搜索的名称

        ArrayList<Map<String, Object>> datasource = null;

        try {

            //查询数据源列表

            datasource = dataSourceWrapper.getDataSoureConnections(workspaceId, null).getData();

            if (null != datasource) {

                String tableName = "view";

                for (Map<String, Object> m : datasource) {

                    //获取空间下数据源列表id

                    dsId = m.get("dsId").toString();

                    System.out.println(dsId);

                    String param = "{\n" +

                            "    \"dsId\":\"" + dsId + "\",\n" +

                            "    \"isView\":\"false\",\n" +

                            "    \"pageNum\":\"1\",\n" +

                            "    \"pageSize\":\"20\"\n" +

                            "}";

                    //查询数据源下的数据

                    Map<String, Object> folderList = dashBoardsWrapper.getAllTableListPagingnew(workspaceId, dsId, tableName, param, null).getData();

                    ArrayList<Map<String, Object>> viewNames = (ArrayList<Map<String, Object>>) folderList.get("data");

                    if (null != viewNames) {

                        //查询数据集

                        ArrayList<Map<String, Object>> cubefolderList = dashBoardsWrapper.getFolderList("cube", "V2", workspaceId, "", "", "", null).getData();

                        String pid = null;

                        for (Map<String, Object> j : cubefolderList) {

                            if (j.get("name").toString().equals("我的数据集")) {

                                pid = j.get("id").toString();

                            }

                        }

                        for (Map<String, Object> i : viewNames) {

                            viewName = i.get("name").toString();

                            //如果该数据源下有视图，则新建数据集。否则在数据库创建视图

                            if (viewName.contains("view")) {

                                //创建数据集


                                String params = "{\"aimDirId\":\"" + pid + "\",\"tableName\":\"" + viewName + "\",\"dsId\":\"" + dsId + "\",\"userDefineCubeName\":\"" + viewName + "\"}";

                                String filter = "workspaceId=" + workspaceId;

                                ResultModel<String> re = dashBoardsWrapper.createCube(filter, "V2", params, null);

                                System.out.print(re);

                                break;

                            }

                        }

                    } else {

                        System.out.println("请创建视图,表结构命名为***view");

                    }

                }

            }

        } catch (URISyntaxException e) {

            e.printStackTrace();

        }


    }
}
