package testAPI.context;
import org.springframework.stereotype.Service;

/**
 * Created by jingyan.cj on 2015/10/31.
 */
@Service
public class BaseBizContext {

    String userName;
    String baseId;
    long tenantId;
    long projectId;
    String projectIdentifier;
    String odpsProjectName;
    long tgtprojectId;
    String tgtProjectIdentifier;
    String tgtOdpsProjectName;
    long flowId;
    String cookie;
    String csrfToken;
    String workspaceId;
    String otherWorkspaceId;

    public String getOtherWorkspaceId() {
        return otherWorkspaceId; }

    public void setOtherWorkspaceId(String otherWorkspaceId) {  this.otherWorkspaceId = otherWorkspaceId;  }

    public String getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBaseId() {
        return baseId;
    }

    public void setBaseId(String baseId) {
        this.baseId = baseId;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }

    public long getTenantId() {
        return tenantId;
    }

    public void setTenantId(long tenantId) {
        this.tenantId = tenantId;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public long getTgtprojectId() {
        return tgtprojectId;
    }

    public void setTgtprojectId(long tgtprojectId) {
        this.tgtprojectId = tgtprojectId;
    }

    public String getTgtProjectIdentifier() {
        return tgtProjectIdentifier;
    }

    public void setTgtProjectIdentifier(String tgtProjectIdentifier) {
        this.tgtProjectIdentifier = tgtProjectIdentifier;
    }

    public long getFlowId() {
        return flowId;
    }

    public void setFlowId(long flowId) {
        this.flowId = flowId;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getCsrfToken() {
        return csrfToken;
    }

    public void setCsrfToken(String csrfToken) {
        this.csrfToken = csrfToken;
    }

    public String getOdpsProjectName() {
        return odpsProjectName;
    }

    public void setOdpsProjectName(String odpsProjectName) {
        this.odpsProjectName = odpsProjectName;
    }

    public String getTgtOdpsProjectName() {
        return tgtOdpsProjectName;
    }

    public void setTgtOdpsProjectName(String tgtOdpsProjectName) {
        this.tgtOdpsProjectName = tgtOdpsProjectName;
    }
}