package com.imodel.enums;

import java.io.Serializable;


public enum ErrorCodeEnum implements Serializable {
	No_Error(0),
	User_Not_Exists(101),
	Password_Error(102),
	User_Not_Login(103),
	Not_Finished(104),
	Model_State_Error(105),
	Inner_Error(500);
	
	private Integer code;
	ErrorCodeEnum() {
		
	}
	
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	ErrorCodeEnum(Integer code) {
		this.code = code;
	}
}
