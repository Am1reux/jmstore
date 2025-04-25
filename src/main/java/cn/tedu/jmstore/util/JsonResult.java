package cn.tedu.jmstore.util;
//该类用来封装后台给浏览器响应的数据（状态码、异常信息、从数据库查询到的数据【比如所有商品、某个商品、用户原始信息】）
//
public class JsonResult<T> {
	private Integer state; 	//封装状态码
	private String message; //封装异常信息
	private T data;			//封装从数据库查询到的数据【比如所有商品、某个商品、用户原始信息】
	
	public JsonResult(Integer state, T data) {
		this.state = state;
		this.data = data;
	}

	//无参构造方法
	public JsonResult() {
		
    }

    public JsonResult(Integer state) {
        this.state = state;
    }

    public Integer getState() {
        return state;
    }
    public void setState(Integer state) {
        this.state = state;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
	
	
}
