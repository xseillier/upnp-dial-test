package com.xseillier.upnp.model;


/**
 * 
 * @author xseillier
 *
 */
public class UpnpServiceImpl implements UpnpService {

	
	private String 	mServiceType;
	private String  mServiceTypeId;
	private String  mSCPDURL;
	private String	mControlURL;
	private String	mEventSubURL;
	
	@Override
	public String getServiceType() {
		return mServiceType;
	}

	@Override
	public void setServiceType(String aServiceType) {
		mServiceType = aServiceType;
	}

	@Override
	public String getServiceTypeId() {
		return mServiceTypeId;
	}

	@Override
	public void setServiceTypeId(String aServiceTypeId) {
		mServiceTypeId = aServiceTypeId;
	}

	@Override
	public String getSCPDURL() {
 		return mSCPDURL;
	}

	@Override
	public void setSCPDURL(String aSCPDURL) {
		mSCPDURL = aSCPDURL;
	}

	@Override
	public String getControlURL() {
		return mControlURL;
	}

	@Override
	public void setControlURL(String aControlURL) {
		mControlURL = aControlURL;
	}

	@Override
	public String getEventSubURL() {
		return mEventSubURL;
	}

	@Override
	public void setEventSubURL(String aEventSubURL) {
		mEventSubURL = aEventSubURL;
	}

}
