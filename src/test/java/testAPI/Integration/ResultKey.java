package testAPI.Integration;

/**
 * 返回值通用类
 * @author ting.weit
 */
public class ResultKey {

    private static final long  serialVersionUID = 8482909105020394871L;
    /**
     * 错误码
     */
    public static final String errorCode        = "errorCode";
    /**
     * 错误信息
     */
    public static final String errorDesc        = "errorDesc";
    /**
     * 异常stack
     */
    public static final String exStack          = "exStack";
    /**
     * 错误解决方案
     */
    public static final String solution         = "solution";
    /**
     * 错误处理人，一般可以指定维护人员
     */
    public static final String opers            = "opers";
    /**
     * 当前请求的唯一性标识
     */
    public static final String traceId          = "traceId";
    /**
     * 返回值
     */
    public static final String data             = "data";

    /**
     * 访问是否成功
     */
    public static final String success = "success";

    /**
     * 是否对输出的json进行转码
     */
    public static final String nonEscapeJSON = "_non_escape_json_";
}

