package utils;

public class ResultModel {
	boolean result;
	String message;
	String stepName;
	
	public ResultModel() {
		super();
		this.result = true;
		this.message = "";
		this.stepName = "";
	}
	
	public ResultModel(boolean result, String message) {
		super();
		this.result = result;
		this.message = message;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStepName() {
		return stepName;
	}

	public void setStepName(String stepName) {
		this.stepName = stepName;
	}
	
	
}
