package pojo;

import lombok.Data;

@Data
public class ApiInfo {
	/**
	 * 接口信息pojo对象
	 */
	private String apiId;   //接口id
	private String apiName; //接口名称
	private String url;     //接口地址
	private String type;    //请求接口类型


	@Override
	public String toString() {
		return "ApiInfo [apiId=" + apiId + ", apiName=" + apiName + ", url=" + url + ", type=" + type + "]";
	}

}
