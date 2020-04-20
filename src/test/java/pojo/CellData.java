package pojo;

public class CellData {
	/**
	 * 写回excel的数据pojo类
	 */
	private String caseId; //写回的行号

	private Integer cellNum; // 写回的列号

	private String resultStr; //要写的结果

	public CellData(String caseId, Integer cellNum, String resultStr) {
		super();
		this.caseId = caseId;
		this.cellNum = cellNum;
		this.resultStr = resultStr;
	}

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public Integer getCellNum() {
		return cellNum;
	}

	public void setCellNum(Integer cellNum) {
		this.cellNum = cellNum;
	}

	public String getResultStr() {
		return resultStr;
	}

	public void setResultStr(String resultStr) {
		this.resultStr = resultStr;
	}

	@Override
	public String toString() {
		return "CellData [caseId=" + caseId + ", cellNum=" + cellNum + ", resultStr=" + resultStr + "]";
	}

}
