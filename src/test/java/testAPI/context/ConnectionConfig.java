package testAPI.context;
/**
 *
 */

import java.util.Map;

/**
 * 数据库连接配置信息
 *
 * @author weibing.xwb
 * @date 2016年8月16日
 * @version 1.3.0
 */
public class ConnectionConfig {

    /**
     * 数据源ID,UUID自动生成
     */
    private String dsId;

    /**
     * 操作用户
     */
    private String creatorId;

    /**
     * 数据库连接串地址（域名或ip）
     */
    private String address;

    /**
     * 端口
     */
    private String port;

    /**
     * 数据源类型
     */
    private String dsType;

    /**
     * 连接数据库用户名（加密存储）
     */
    private String userName;

    /**
     * 连接用户名对应的登陆密码（加密存储）
     */
    private String password;

    /**
     * 数据库schema，仅对支持schema的数据库需要设置<br>
     * example：sqlserver 默认使用dbo；mysql不支持schema
     */
    private String schema;

    /**
     * 数据库实例，对应数据库名称，ODPS为project
     */
    private String instance;

    /**
     * 获取连接串详情时，odps供前端展示使用
     */
    private String project;

    /**
     * 数据源前端展示名称
     */
    private String showName;
    /**
     * 数据源传id
     */
    private String instanceId;
    /**
     * 区域
     */
    private String region;

    /**
     * 数据库连接配置信息，如制定字符编码
     */
    private Map<String, String> config;

    public String getDsId() {
        return dsId;
    }

    public void setDsId(String dsId) {
        this.dsId = dsId;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDsType() {
        return dsType;
    }

    public void setDsType(String dsType) {
        this.dsType = dsType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }
    public void setProjectId(String instanceId) {
        this.instanceId = instanceId;
    }
    public String getProjectId() {
        return instanceId;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public Map<String, String> getConfig() {
        return config;
    }

    public void setConfig(Map<String, String> config) {
        this.config = config;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }
}