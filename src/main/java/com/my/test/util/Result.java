package com.my.test.util;

import java.io.Serializable;
import java.util.List;

public class Result{

    private String resultCode;// 1成功，0失败
    private String resultMsg;//返回信息 例如 “更新失败”  “系统异常 ”  “ 网络异常”
    private Object resultdata;//返回值
    private List<ErrorDataDto> errorData;//表单详细错误
    
    public String getResultMsg() {
        return resultMsg;
    }
    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }
    public String getResultCode() {
        return resultCode;
    }
    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }
    public Object getResultdata() {
        return resultdata;
    }
    public void setResultdata(Object resultdata) {
        this.resultdata = resultdata;
    }
    public List<ErrorDataDto> getErrorData() {
        return errorData;
    }
    
    public void setErrorData(List<ErrorDataDto> errorData) {
        this.errorData = errorData;
    }


    public class ErrorDataDto implements Serializable{
        
        private static final long serialVersionUID = 1L;
        
        private String errorProperty;//错误属性名称 例如：name, merchantName
        private String errorCode;//errorCode 参考 ErrorCodes 中的定义的code码 例如：0001
        private String errorMsg;//错误信息描述 例如：属性为空
        public String getErrorProperty() {
            return errorProperty;
        }
        public void setErrorProperty(String errorProperty) {
            this.errorProperty = errorProperty;
        }
        public String getErrorCode() {
            return errorCode;
        }
        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }
        public String getErrorMsg() {
            return errorMsg;
        }
        public void setErrorMsg(String errorMsg) {
            this.errorMsg = errorMsg;
        }
        
        
    }
    
}
