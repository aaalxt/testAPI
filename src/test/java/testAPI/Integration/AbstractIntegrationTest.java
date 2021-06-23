package testAPI.Integration;



//import org.apache.log4j.Logger;
//import org.apache.log4j.spi.LoggerFactory;
import testAPI.context.BaseBizContext;

import testAPI.context.BaseUserInfo;

import testAPI.testcase.sdfasd;

import com.google.gson.Gson;

import com.google.gson.GsonBuilder;

import org.apache.http.Consts;

import org.apache.http.HttpEntity;

import org.apache.http.HttpResponse;

import org.apache.http.NameValuePair;

import org.apache.http.client.CookieStore;

import org.apache.http.client.config.RequestConfig;

import org.apache.http.client.entity.UrlEncodedFormEntity;

import org.apache.http.client.methods.*;

import org.apache.http.client.utils.URLEncodedUtils;

import org.apache.http.conn.ssl.AllowAllHostnameVerifier;

import org.apache.http.conn.ssl.TrustStrategy;

import org.apache.http.entity.ContentType;

import org.apache.http.entity.StringEntity;

import org.apache.http.impl.client.*;

import org.apache.http.impl.cookie.BasicClientCookie;

import org.apache.http.message.BasicNameValuePair;

import org.apache.http.ssl.SSLContextBuilder;

import org.apache.http.util.EntityUtils;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.test.context.ContextConfiguration;

import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import org.testng.Assert;



import javax.sql.DataSource;

import java.lang.reflect.Type;

import java.net.URI;

import java.net.URISyntaxException;

import java.nio.charset.Charset;

import java.security.cert.CertificateException;

import java.security.cert.X509Certificate;

import java.sql.*;

import java.util.ArrayList;

import java.util.HashMap;

import java.util.List;

import java.util.Map;



@ContextConfiguration(locations = {"/applicationContext.xml"})

public class AbstractIntegrationTest extends AbstractTestNGSpringContextTests  {

    protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractIntegrationTest.class);

    public static DefaultHttpClient httpClient; // HTTP客户端

    public static HttpPost HttpPost_FORM_URLENCODED; // x-www-form-urlencoded

    public static HttpPost HttpPost_MULTIPART_FORMDATA; // multipart/form-data 请求形式



    public static HttpPost httpPost; // POST请求体

    public static HttpPost httpPostjson;

    public static HttpPut httpPut; // PUT请求体

    public static HttpGet httpGet; // GET请求体

    public static HttpDelete httpDelete; // DELETE请求体



    public static String URL_BI;    //登陆url

    public static String USER_NAME; //登陆名字

    public static String PASS_WORD; //登陆密码



    public static String DATESOURCE_USERNAME;//数据库账号

    public static String DATESOURCE_PASSWORD;//数据库账号

    public static String DATESOURCE_IP;//数据库地址

    public static String DATESOURCE_PORT;//数据库端口

    public static String DATESOURCE_DATABASE;//数据库库名

    public static String DRIVER;//驱动

    public static String TEST_LOGIN;

    @Autowired

    public static BaseUserInfo baseUserInfo = new BaseUserInfo();



    @Autowired

    public static BaseBizContext baseBizContext = new BaseBizContext();



    public static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    static CookieStore cookieStore = null;



    @Value("${test_driver_url}")

    public void setUrlBi(String urlBi) {

        URL_BI = urlBi;

    }

    @Value("${test_login}")

    public void setlogin(String test_login) {

        TEST_LOGIN = test_login;

    }







    @Value("${test_user_username}")

    public void setUserName(String userName) {

        USER_NAME = userName;

    }



    @Value("${test_user_password}")

    public void setPassWord(String password) {

        PASS_WORD = password;

    }



    @Value("${datesource_username}")

    public void setDatesourceUserName(String datesource_username) {

        DATESOURCE_USERNAME = datesource_username;

    }

    @Value("${datesource_password}")

    public void setDatesourcePassWord(String datesource_password) {

        DATESOURCE_PASSWORD = datesource_password;

    }



    @Value("${datesource_ip}")

    public void setDatesourceIP(String datesource_ip) {

        DATESOURCE_IP = datesource_ip;

    }



    @Value("${datesource_port}")

    public void setDatesourcePort(String datesource_port) {

        DATESOURCE_PORT = datesource_port;

    }



    @Value("${datesource_database}")

    public void setDatesourceDatabase(String datesource_database) {

        DATESOURCE_DATABASE = datesource_database;

    }

    @Value("${driver}")

    public void setDatesourceDriver(String driver) {

        DRIVER = driver;

    }



    @Autowired

    protected DataSource daily_dataSource;



    public void setUp(String URL_BI, String TEST_LOGIN, String USER_NAME, String PASS_WORD) throws Exception {



        httpPostjson = new HttpPost();

        // httpPostjson.setHeader("Accept", "application/json, text/javascript, */*; q=0.01");

        httpPostjson.setHeader("Accept", "application/json, text/plain, */*");

        httpPostjson.setHeader("Content-Type", "application/json");

        httpPostjson.setHeader("Accept-Language", "zh-CN,zh;q=0.8");

        httpPut = new HttpPut();

        httpPut.setHeader("Accept", "application/json, text/javascript, */*; q=0.01");

        httpPut.setHeader("Content-type", "application/json");

        httpPut.setHeader("Accept-Language", "zh-CN,zh;q=0.8");



        // 初始化GET请求体

        httpGet = new HttpGet();

        httpGet.setHeader("Accept", "application/json, text/javascript, */*; q=0.01");

        httpGet.setHeader("Content-type", "application/json");

        httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8");



        // 初始化DELETE请求体

        httpDelete = new HttpDelete();

        httpPostLogin(URL_BI,TEST_LOGIN,USER_NAME,PASS_WORD);



        httpPostjson.setHeader("Cookie", baseBizContext.getCookie());

        //    httpPostjson.setHeader("X-Csrf-Token", baseBizContext.getCsrfToken());



        httpPut.setHeader("Cookie", baseBizContext.getCookie());

        httpPut.setHeader("X-Csrf-Token", baseBizContext.getCsrfToken());

        httpPut.setHeader("workspaceId", baseBizContext.getWorkspaceId());



        httpGet.setHeader("Cookie", baseBizContext.getCookie());

        httpGet.setHeader("X-Csrf-Token", baseBizContext.getCsrfToken());

        httpGet.setHeader("workspaceId", baseBizContext.getWorkspaceId());



        httpDelete.setHeader("Cookie", baseBizContext.getCookie());

        httpDelete.setHeader("X-Csrf-Token", baseBizContext.getCsrfToken());

        httpDelete.setHeader("workspaceId", baseBizContext.getWorkspaceId());

        httpDelete.setHeader("x-requested-with", "XMLHttpRequest");

    }



    /**

     *  不同数据源，执行查询语句，返回list

     */

    public List<Map<String, Object>> executeQuery_dataSource(DataSource dataSource, String sqlScripts) {

        Statement sta = null;

        ResultSet rs = null;

        Connection con = null;

        List<Map<String, Object>> list = new ArrayList();

        try {

            con = dataSource.getConnection();

            sta = con.createStatement();

            //LOGGER.info("执行脚本:" + sqlScripts);

            rs = sta.executeQuery(sqlScripts);

            //LOGGER.info("成功执行");

            ResultSetMetaData md = rs.getMetaData();

            int columnCount = md.getColumnCount();

            while (rs.next()) {

                Map rowData = new HashMap();

                for (int i = 1; i <= columnCount; i++) {

                    rowData.put(md.getColumnName(i), rs.getObject(i));

                }

                list.add(rowData);

            }

        } catch (Exception e) {

            LOGGER.error("执行SQL脚本出错:", e);

            Assert.fail("执行SQL脚本出错:" + e.getMessage());

        } finally {

            try {

                if (rs != null) {

                    rs.close();

                }

                if (sta != null) {

                    sta.close();

                }

                if (con != null) {

                    con.close();

                }

            } catch (SQLException e) {

                LOGGER.error("关闭Statement出错:", e);

                Assert.fail("关闭Statement出错:" + e.getMessage());

            }

        }

        return list;

    }



    /**

     * 执行查询SQL,并按指定的列返回结果

     *

     * @param type

     * @param url

     */

    public <T> ResultModel<T> returnResult(String type, String url, String conditions, String filter, String params,

                                           String errorCode) throws URISyntaxException {

        if (conditions != null) {

            List<NameValuePair> f = URLEncodedUtils.parse(conditions, Charset.forName("UTF-8"));

            for (NameValuePair nvp: f) {

                if (url.contains("{"+ nvp.getName() +"}")) {

                    url = url.replaceAll("\\{" + nvp.getName() + "\\}", nvp.getValue());

                }

            }

        }



        if (filter != null) {

            url += "?";

            List<NameValuePair> f = URLEncodedUtils.parse(filter, Charset.forName("UTF-8"));

            url += URLEncodedUtils.format(f, "UTF-8");

        }



        String result = "";

        if ("GET".equals(type)) {

            httpGet.setURI(new URI(url));

            result = executeAndGet(httpGet);

        }

        else if ("POST".equals(type)) {

            httpPost.setURI(new URI(url));

            if (params != null) {

                httpPost.setEntity(new StringEntity(params, ContentType.APPLICATION_JSON));

            }

            result = executeAndGet(httpPost);

        }

        else if ("POSTJSON".equals(type)){

            httpPostjson.setURI(new URI(url));

            if (params != null) {

                httpPostjson.setEntity(new StringEntity(params, ContentType.APPLICATION_JSON));

            }



            System.out.println("returnResult POSTJSON " + params);

            result = executeAndGet(httpPostjson);

            System.out.println(result);

        }

        else if ("PUT".equals(type)) {

            httpPut.setURI(new URI(url));

            if (params != null) {

                httpPut.setEntity(new StringEntity(params, ContentType.APPLICATION_JSON));

            }

            result = executeAndGet(httpPut);

        }

        else if ("DELETE".equals(type)) {

            httpDelete.setURI(new URI(url));

            result = executeAndGet(httpDelete);

        }



        return returnModel(result, errorCode);

    }

    public <T> ResultModel<T> returnModel(String result, String errorCode) {

        System.out.println(result);

        logger.info(result);



        ResultModel<T> model = new ResultModel<T>();



        @SuppressWarnings("serial")

        Type type = new com.google.common.reflect.TypeToken<ResultModel<T>>() {

        }.getType();

        model = gson.fromJson(result, type);



        if (errorCode == null) {

            System.out.println(model.getData());

            logger.info(model.getData());

//            Assert.assertTrue(model.getSuccess());

            return model;

        } else {

            System.out.println(model.getErrorDesc());

            System.out.println(model.getExStack());

            logger.error(model.getErrorDesc());

            logger.error(model.getExStack());

            Assert.assertFalse(model.getSuccess());

//            Assert.assertEquals(errorCode, model.getErrorCode());

            Assert.assertEquals(errorCode, model.getCode());

            return model;

        }

    }





    /**

     * 执行GET、PUT、POST、DELETE等方法，并返回响应结果

     *

     * @param httpRequestBase

     * @return

     */

    protected String executeAndGet(HttpRequestBase httpRequestBase) {

        try {

            // HttpHost proxy = new HttpHost("11.239.249.42",51201);

            CloseableHttpClient httpClient = HttpClients.custom()

                    //       .setProxy(proxy)

                    .setHostnameVerifier(new AllowAllHostnameVerifier())

                    .setSslcontext(new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {

                        public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {

                            return true;

                        }

                    }).build()).build();



            Long start = System.currentTimeMillis();

            System.out.println("executeAndGet " + httpRequestBase);

            HttpResponse response = httpClient.execute(httpRequestBase);

//            System.out.println(response.getHeaders("R-Gw-String-To-Sign")[0]); //openapi返回服务端待签名字符串

//            System.out.println(response.getHeaders("R-Gw-Signatured")[0]); //openapi返回服务端签名字符串

            LOGGER.info(String.format("HTTP %s: %s, STATUS CODE: %d, REASON: %s",

                    httpRequestBase.getMethod(),

                    httpRequestBase.getURI(),

                    response.getStatusLine().getStatusCode(),

                    response.getStatusLine().getReasonPhrase()));

            System.out.println(String.format("===> HTTP %s: %s, STATUS CODE: %d, REASON: %s",

                    httpRequestBase.getMethod(),

                    httpRequestBase.getURI(),

                    response.getStatusLine().getStatusCode(),

                    response.getStatusLine().getReasonPhrase()));



//            Header[] headers = response.getAllHeaders();

//            for (int i = 0; i < headers.length; i++) {

//                System.out.print(headers[i].getName() + " " + headers[i].getValue()+"\n");

//            }

//            System.out.println("Server SignString: "+response.getHeaders("R-Gw-String-To-Sign")[0]);

            HttpEntity entity = response.getEntity();

            if (entity != null) {

                String strResponse = EntityUtils.toString(entity, Consts.UTF_8);

                Long end = System.currentTimeMillis();

                LOGGER.info(String.format("HTTP RESPONSE: %s ,cost time:%s", strResponse, (end - start) + "ms"));

                System.out.println(String.format("===> HTTP RESPONSE:%s ,cost time:%s", strResponse, (end - start)  + "ms"));

                System.out.println();

                LOGGER.info(String.format("HTTP %s: %s, STATUS CODE: %d, REASON: %s, cost time: %s",

                        httpRequestBase.getMethod(),

                        httpRequestBase.getURI(),

                        response.getStatusLine().getStatusCode(),

                        response.getStatusLine().getReasonPhrase(), (end - start)  + "ms"));

                return strResponse;

            }

        } catch (Exception e) {

            LOGGER.error("请求API服务,出错:", e);

            Assert.fail("请求API服务,出错:" + e.getMessage());

        }

        return  null;

    }



    public  void httpPostLogin(String URL_BI, String TEST_LOGIN, String name, String pwd) {

        String urlLogin = URL_BI + TEST_LOGIN;

        //第一次登录会保存cookie

        baseUserInfo =  doPostlogin(urlLogin,getLoginNameValuePairList(name,pwd));

        Assert.assertNotNull(baseUserInfo.getCookies());

        Assert.assertNotNull(baseUserInfo.getCsrf_token());

        baseBizContext.setCookie(baseUserInfo.getCookies());

        baseBizContext.setCsrfToken(baseUserInfo.getCsrf_token());

    }

    //登陆首次

    public static List<NameValuePair> getLoginNameValuePairList(String name,String pwd) {

        List<NameValuePair> params = new ArrayList<NameValuePair>();

//        params.add(new BasicNameValuePair("phone", name));

//        params.add(new BasicNameValuePair("passwd", pwd));

        //将密码传入使用hashpw加密



        params.add(new BasicNameValuePair("admin", name));

        params.add(new BasicNameValuePair("password", pwd));

        params.add(new BasicNameValuePair("code", "阿阿"));

        params.add(new BasicNameValuePair("uuid", "code-key-4052f53dc19e4e45a773ec24c22ff7c7"));

        return params;

    }

    /**

     * 将cookie保存到静态变量中供后续调用

     * @param httpResponse

     */

    public static void setCookieStore(HttpResponse httpResponse) {

        System.out.println("----setCookieStore");

        cookieStore = new BasicCookieStore();

        // JSESSIONID

        String setCookie = httpResponse.getFirstHeader("Set-Cookie").getValue();

        String PHPSESSID = setCookie.substring("PHPSESSID=".length(),

                setCookie.indexOf(";"));

        System.out.println("PHPSESSID:" + PHPSESSID);

        // 新建一个Cookie

        BasicClientCookie cookie = new BasicClientCookie("PHPSESSID",PHPSESSID);

        cookie.setVersion(0);

        cookie.setDomain("47.94.147.93");

        cookie.setPath("/");

        cookieStore.addCookie(cookie);

    }



    public static BaseUserInfo doPostlogin(String postUrl, List<NameValuePair> parameterList) {

        String retStr = "";

        // 创建HttpClientBuilder

        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();

        // HttpClient

        CloseableHttpClient closeableHttpClient = null;

        closeableHttpClient = httpClientBuilder.build();



        HttpPost httpPost = new HttpPost(postUrl);

        httpPost.setHeader("Accept", "application/json, text/plain, */*");

        httpPost.setHeader("Content-Type", "application/json");

        httpPost.setHeader("Accept-Language", "zh-CN,zh;q=0.8");

        // 设置请求和传输超时时间

        RequestConfig requestConfig = RequestConfig.custom()

                .setSocketTimeout(30000).setConnectTimeout(30000).build();

        httpPost.setConfig(requestConfig);

        try {

            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parameterList, "UTF-8");

            httpPost.setEntity(entity);

            CloseableHttpResponse response = closeableHttpClient.execute(httpPost);

            setCookieStore(response);



            HttpEntity httpEntity = response.getEntity();

            retStr = EntityUtils.toString(httpEntity, "UTF-8");

            System.out.println(retStr);

            // 释放资源

            closeableHttpClient.close();

        } catch (Exception e) {

        }

        return null;

    }





    public static String doPost(String postUrl, List<NameValuePair> parameterList) {

        String retStr = "";

        // 创建HttpClientBuilder

        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();

        // HttpClient

        CloseableHttpClient closeableHttpClient = null;

        if (cookieStore != null) {

            closeableHttpClient = httpClientBuilder.setDefaultCookieStore(cookieStore).build();

        } else {

            closeableHttpClient = httpClientBuilder.build();

        }

        HttpPost httpPost = new HttpPost(postUrl);

        // 设置请求和传输超时时间

        RequestConfig requestConfig = RequestConfig.custom()

                .setSocketTimeout(30000).setConnectTimeout(30000).build();

        httpPost.setConfig(requestConfig);

        try {

            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parameterList, "UTF-8");

            httpPost.setEntity(entity);

            CloseableHttpResponse response = closeableHttpClient.execute(httpPost);

            setCookieStore(response);



            HttpEntity httpEntity = response.getEntity();

            retStr = EntityUtils.toString(httpEntity, "UTF-8");

            System.out.println(retStr);

            // 释放资源

            closeableHttpClient.close();

        } catch (Exception e) {

        }

        return retStr;

    }





}
