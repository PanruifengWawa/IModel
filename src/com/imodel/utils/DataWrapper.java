package com.imodel.utils;

import com.imodel.enums.ErrorCodeEnum;

public class DataWrapper<T>  {

    private T data;
    private Integer errorCode;
    
    // 用于分页结果
    private int pageSize;
    private int pageIndex;
    private int totalNumber;
    private int totalPage;
    private Integer userLevel;

    public DataWrapper() {
    	this.errorCode = ErrorCodeEnum.No_Error.getCode();
    }

    
    


	public Integer getUserLevel() {
		return userLevel;
	}





	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}





	public Integer getErrorCode() {
		return errorCode;
	}


	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
	

	public int getPageSize() {
		return pageSize;
	}



	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}



	public int getPageIndex() {
		return pageIndex;
	}



	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}








	public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

}
