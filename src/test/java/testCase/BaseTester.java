package testCase;

import org.testng.annotations.AfterSuite;

import Util.ExcelUtil;

public class BaseTester {
    
	//把所有收集完的数据往target/rest_info_result.xlsx的第一个sheet批量写入收集数据
	@AfterSuite
	public void afterSuite(){
		ExcelUtil.batchWrite("/rest_info.xlsx","target/rest_info_result.xlsx",1);
	}

}
