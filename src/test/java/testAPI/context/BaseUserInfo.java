package testAPI.context;

public class BaseUserInfo {

    public BaseUserInfo(String _userName, String _userPw) {
        this.userName = _userName;
        this.userPassword = _userPw;

    }

    public BaseUserInfo() {
    }

    private String userName;
    private String userPassword;
    private String cookies;
    private String csrf_token;
    private String baseId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getCookies() {
        return cookies;
    }

    public void setCookies(String cookies) {
        this.cookies = cookies;
    }

    public String getCsrf_token() {
        return csrf_token;
    }

    public void setCsrf_token(String csrf_token) {
        this.csrf_token = csrf_token;
    }

    public String getBaseId() {
        return baseId;
    }

    public void setBaseId(String baseId) {
        this.baseId = baseId;
    }
}