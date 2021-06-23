package testAPI.testcase;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import testAPI.context.ConnectionConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class DataSourceEnum {
    protected static final Logger LOGGER = LoggerFactory.getLogger(DataSourceEnum.class);



    @DataProvider(name = "dataSourceEnum")
    public static Object[][] dataSourceProvider() {
        //MaxcomputeLighting是用PostgreSQL连接勾上ssl
        return new Object[][]{
                {"yunDB", "Odps_stg", initBaseData("Odps_stg")},
                {"yunDB", "MySQL", initBaseData("MySQL")},
                {"yunDB", "SQLServer", initBaseData("SQLServer")},
                {"yunDB", "Analytic_DB", initBaseData("Analytic_DB")},
                {"yunDB", "HybridDB_for_MySQL", initBaseData("HybridDB_for_MySQL")},
                {"yunDB", "HybridDB_for_PostgreSQL", initBaseData("HybridDB_for_PostgreSQL")},
                {"yunDB", "PostgreSQL", initBaseData("PostgreSQL")},
                {"yunDB", "PostgreSQL_ssh", initBaseData("PostgreSQL_ssh")},
                {"yunDB", "PPAS", initBaseData("PPAS")},
                {"yunDB", "Date_Lake_Analytics", initBaseData("Date_Lake_Analytics")},
                {"yunDB", "Hive", initBaseData("Hive")},
                {"yunDB", "OSS", initBaseData("OSS")},
                {"yunDB", "DRDS", initBaseData("DRDS")},
                {"yunDB", "PolarDBforMySQL", initBaseData("PolarDBforMySQL")},
                {"yunDB", "yun_ADB30", initBaseData("yun_ADB30")},
                {"yunDB", "yun_TSDB", initBaseData("yun_TSDB")},
                {"yunDB", "yun_Hbase", initBaseData("yun_Hbase")},

                {"self_build_DB", "selfBuild_MySQL", initBaseData("selfBuild_MySQL")},
                {"self_build_DB", "selfBuild_MySQL_SSH", initBaseData("selfBuild_MySQL_SSH")},
                {"self_build_DB", "selfBuild_SQLServer", initBaseData("selfBuild_SQLServer")},
                {"self_build_DB", "selfBuild_PostgreSQL", initBaseData("selfBuild_PostgreSQL")},
                {"self_build_DB", "selfBuild_Oracle", initBaseData("selfBuild_Oracle")},
                {"self_build_DB", "selfBuild_Vertica", initBaseData("selfBuild_Vertica")},
                {"self_build_DB", "selfBuild_IBM_DB_LUW", initBaseData("selfBuild_IBM_DB_LUW")},
                {"self_build_DB", "selfBuild_SAP_IQ", initBaseData("selfBuild_SAP_IQ")},
                {"self_build_DB", "selfBuild_SAP_HANA", initBaseData("selfBuild_SAP_HANA")},
                // {"self_build_DB","PolarDBforMySQL",initBaseData("PolarDBforMySQL")},
                {"self_build_DB", "selfBuild_Presto", initBaseData("selfBuild_Presto")}
        };
    }

    /**
     * dataSourceTestData文件中获取指定数据源相关信息
     */
    protected static JSONObject initBaseData(String key) {
        JSONObject jsonObject = new JSONObject();
        try {
            URL scriptFilePath = ClassLoader.getSystemResource("testParamData/dataSourceTestData.txt");
            File file = new File(scriptFilePath.getPath());
            String content = org.apache.commons.io.FileUtils.readFileToString(file,"UTF-8");
            jsonObject = JSON.parseObject(content);
        } catch (Exception e) {
            LOGGER.error("读取 dataSourceTestData 脚本,出错:", e);
            Assert.fail("读取 dataSourceTestData 脚本,出错:" + e.getMessage());
        }
        return (JSONObject) jsonObject.get(key);
    }

    // dataSource赋值
    public static ConnectionConfig setDataSource(ConnectionConfig dataSource, JSONObject jsonObject) throws InterruptedException {
        Thread.sleep(1000);
        // 设置日期格式
        SimpleDateFormat df = new SimpleDateFormat("MMdd_ss");
        String nameTemp = df.format(new Date());

        dataSource.setConfig((Map) jsonObject.get("db.config"));
        dataSource.setDsType(jsonObject.get("db.dsType") + "");
        dataSource.setAddress(jsonObject.get("db.address") + "");
        dataSource.setPort(jsonObject.get("db.port") + "");
        dataSource.setUserName(jsonObject.get("db.username") + "");
        dataSource.setPassword(jsonObject.get("db.password") + "");
        dataSource.setProject(jsonObject.get("db.instance") + "");
        dataSource.setInstance(jsonObject.get("db.instance") + "");
        dataSource.setSchema(jsonObject.get("db.schema") + "");
        dataSource.setShowName(nameTemp + "_" + jsonObject.get("db.showName") + "");
        dataSource.setRegion(jsonObject.get("db.region") + "");
        dataSource.setInstanceId(jsonObject.get("db.instanceId") + "");
        return dataSource;
    }


}