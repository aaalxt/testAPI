package testAPI.Integration;

import testAPI.testcase.sdfasd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BaseTest extends AbstractIntegrationTest {
    final static protected Logger logger = LoggerFactory.getLogger(BaseTest.class);

    @BeforeClass
    @Test(groups = {"init-SetUp"}, priority = 1)
    public void setUp() {
        try {
            sdfasd dd = new sdfasd();
            String    publicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBANL378k3RiZHWx5AfJqdH9xRNBmD9wGD2iRe41HdTNF8RUhNnHit5NpMNtGL0NPTSSpPjjI1kJfVorRvaQerUgkCAwEAAQ==";
            String passwd=dd.encrypt("123456",publicKey);
            super.setUp(URL_BI,TEST_LOGIN,USER_NAME,passwd);
        } catch (Exception ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
    }

}