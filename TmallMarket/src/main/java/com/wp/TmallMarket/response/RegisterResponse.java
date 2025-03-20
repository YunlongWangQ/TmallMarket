package com.wp.TmallMarket.response;

import ch.qos.logback.core.util.StringUtil;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
/**
 * <p>标题 </p>
 * <p>功能：</p>
 * <p>所属模块：XYFT_FUND</p>
 * <p>作者：王云龙</p>
 * <p>创建日期：2025/3/20 19:37</p>
 * <p>公司: 厦门象屿股份有限公司</p>
 * <p>类全名：com.wp.TmallMarket.response.RegisterResponse</p>
 */
public class RegisterResponse<T>
{
	private Map<String,T> data;
	private boolean isSuccess;
	private String errMsg;

	public static <K> RegisterResponse<K> newRegSResponse(Map<String,K> data) {
			RegisterResponse<K> successResponse = new RegisterResponse<>();
			successResponse.setSuccess(true);
			successResponse.setData(data);
			return successResponse;
	}
	public static RegisterResponse<Void> newRegFResponse(String errMsg){
		RegisterResponse<Void> failResponse = new RegisterResponse<>();
		failResponse.setSuccess(false);
		failResponse.setErrMsg(errMsg);
		return failResponse;
	}

	public Map<String,T> getData()
	{
		return data;
	}

	public void setData(Map<String,T> data)
	{
		this.data = data;
	}

	public boolean isSuccess()
	{
		return isSuccess;
	}

	public void setSuccess(boolean success)
	{
		isSuccess = success;
	}

	public String getErrMsg()
	{
		return errMsg;
	}

	public void setErrMsg(String errMsg)
	{
		this.errMsg = errMsg;
	}
}
