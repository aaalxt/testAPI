package testAPI.Integration;


import org.apache.commons.lang.StringUtils;



import java.io.Serializable;

import java.util.HashMap;

import java.util.Map;



/**

 * 返回值通用类

 * @author ting.weit

 */

public class ResultModel<T> implements Serializable {



    private static final long     serialVersionUID = 8482909105020394870L;

    /**

     * 错误码

     */

    protected String              errorCode;



    protected String              code;



    /**

     * 错误信息

     */

    protected String              errorDesc;

    protected Boolean             success;

    /**

     * 异常stack

     */

    protected String              exStack;

    /**

     * 错误解决方案

     */

    protected String              solution;

    /**

     * 错误处理人，一般可以指定维护人员

     */

    protected String[]            opers;

    /**

     * 当前请求的唯一性标识

     */

    protected String              traceId;

    /**

     * 返回值

     */

    protected T                   data;



    protected T content;



    /**

     * 附加信息

     */

    protected Map<String, Object> additions        = new HashMap<String, Object>();

    /**

     * 是否对输出的json进行转码

     */

    private boolean nonEscape = false;



    /**

     * <p>Description: ResultModel</p>

     */

    public ResultModel() {



    }



    /**

     * <p>Description:ResultModel </p>

     * @param data

     */

    public ResultModel(T data) {

        this.data = data;

    }



    public ResultModel(Boolean success) {

        this.success = success;

    }



    public ResultModel(T content, String a) {

        this.content = content;

    }





    /**

     *

     * <p>Description:ResultModel </p>

     * @param returnCode

     * @param returnMessage

     */

    public ResultModel(String returnCode, String returnMessage) {

        this.setErrorCode(returnCode);

        this.setErrorDesc(returnMessage);

    }



    /**

     *

     * <p>Description: ResultModel</p>

     * @param data

     * @param returnCode

     * @param returnMessage

     */

    public ResultModel(T data, String returnCode, String returnMessage) {

        this(returnCode, returnMessage);

        this.data = data;

    }





    /**

     * 请求是否成功操作成功

     * @return

     */

    public boolean isSuccess() {

        return StringUtils.isEmpty(errorCode);

    }



    public boolean getSuccess() {

        return success;

    }





    /**

     * @return the errorCode

     */

    public String getErrorCode() {

        return errorCode;

    }



    public String getCode() {

        return code;

    }



    /**

     * @param errorCode the errorCode to set

     */

    public void setErrorCode(String errorCode) {

        this.errorCode = errorCode;

    }



    public void setCode(String code) {

        this.code = code;

    }



    /**

     * @return the errorDesc

     */

    public String getErrorDesc() {

        return errorDesc;

    }



    /**

     * @param errorDes c the errorDesc to set

     */

    public void setErrorDesc(String errorDesc) {

        this.errorDesc = errorDesc;

    }



    /**

     * @return the exStack

     */

    public String getExStack() {

        return exStack;

    }



    /**

     * @param exStack the exStack to set

     */

    public void setExStack(String exStack) {

        this.exStack = exStack;

    }



    /**

     * @return the opers

     */

    public String[] getOpers() {

        return opers;

    }



    /**

     * @param opers the opers to set

     */

    public void setOpers(String[] opers) {

        this.opers = opers;

    }



    /**

     * @return the solution

     */

    public String getSolution() {

        return solution;

    }



    /**

     * @param solution the solution to set

     */

    public void setSolution(String solution) {

        this.solution = solution;

    }



    /**

     * @return the data

     */

    public T getData() {

        return data;

    }



    public T getContent() {

        return content;

    }



    /**

     * @param data the data to set

     */

    public void setData(T data) {

        this.data = data;

    }



    public void setContent(T content) {

        this.content = content;

    }



    /**

     * @return the traceId

     */

    public String getTraceId() {

        return traceId;

    }



    /**

     * @param traceId the traceId to set

     */

    public void setTraceId(String traceId) {

        this.traceId = traceId;

    }



    /**

     * @return the additions

     */

    public Object getAddition(String key) {

        return additions.get(key);

    }



    /**

     * @param additions the additions to set

     */

    public void setAddition(String key, Object value) {

        this.additions.put(key, value);

    }



    /**

     * @Title:dump

     * @param result

     * @return Map<String,Object>

     */

    public Map<String, Object> dump(final Map result) {

        Map<String, Object> temp = toMap();

        temp.putAll(result);

        return temp;

    }



    /**

     * @Title:toMap

     * @return Map<String,Object>

     */

    public Map<String, Object> toMap() {

        Map<String, Object> result = new HashMap<String, Object>();

        result.put(ResultKey.data, this.getData());

        result.put(ResultKey.errorCode, this.getErrorCode());

        result.put("code", this.getErrorCode());//前端旧版本错误码

        result.put(ResultKey.errorDesc, this.getErrorDesc());

        result.put("resultMsg", this.getErrorDesc());//前端旧版本错误描述

        result.put(ResultKey.exStack, this.getExStack());

        result.put(ResultKey.opers, this.getOpers());

        result.put(ResultKey.solution, this.getSolution());

        result.put(ResultKey.success, this.isSuccess());

        if (nonEscape) {

            result.put(ResultKey.nonEscapeJSON, ResultKey.nonEscapeJSON);

        }

        return result;

    }



    /**

     * 不需要对输出的json进行转码

     * @return

     */

    public ResultModel<T> nonEscape() {

        this.nonEscape = true;

        return this;

    }

}