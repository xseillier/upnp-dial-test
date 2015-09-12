package com.xseillier.upnp.http;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author xseillier
 *
 */
public class HttpPage {

	
	private int    mReponseStatus;
	private String mContent;
	private Map<String, List<String>> mHeader;
	
	
	public HttpPage( Map<String, List<String>> aHeader, int aResponseStatus, String aContent ) {
		mHeader  = aHeader;
		mContent = aContent;
		mReponseStatus  = aResponseStatus;
	}
	
	public String getContent() {
		return mContent;
	}
	
	public void setContent(String aContent) {
		mContent = aContent;
	}
	
	public Map<String, List<String>> getHeader() {
		return mHeader;
	}
	
	public void setHeader(Map<String, List<String>> aHeader) {
		mHeader = aHeader;
	}
	
	
	public int getResponseStatus() {
		return mReponseStatus;
	}
	
	public void setResponseStatus(int aReponseStatus) {
		mReponseStatus = aReponseStatus;
	}
	
}
