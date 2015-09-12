package com.xseillier.upnp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author xseillier
 *
 */
public class UpnpDeviceImpl implements UpnpDevice{

	private String mFriendlyName     = "";	
	private String mUniqueDeviceName = "";
	private String mApplicationUrl   = "";
	private String mLocation         = "";
	private String mDeviceType       = "";
	
	private List<UpnpService> mServiceList = new ArrayList<>();
	
	
	@Override
	public String getFriendlyName() {
		return mFriendlyName;
	}

	@Override
	public String getUniqueDeviceName() {
		return mUniqueDeviceName;
	}

	@Override
	public void setUniqueDeviceName(String aUniqueDeviceName) {
		mUniqueDeviceName = aUniqueDeviceName;
	}

	@Override
	public void setFriendlyName(String aFriendlyName) {
		mFriendlyName = aFriendlyName;
	}
	
	@Override
	public String getDeviceType() {
		return mDeviceType;
	}
	
	@Override
	public void setDeviceType(String aDeviceType) {	
		mDeviceType = aDeviceType;
	}
	
	@Override
	public String getLocation() {
		return mLocation;
	}
	
	@Override
	public void setLocation( String aLocationName ) {
		mLocation = aLocationName;
	}
		
	@Override
	public List<UpnpService> getService() {
		return mServiceList;
	}

	@Override
	public void addDeviceType(UpnpService aUpnpService) {
		mServiceList.add( aUpnpService );
	}
	
	@Override
	public void setApplicationUrl(String aApplicationUrl) {
		mApplicationUrl = aApplicationUrl;
	}

	@Override
	public String getApplicationUrl() {
		return mApplicationUrl;
	}
	
	public String toString() {
		
		StringBuffer oResponse = new StringBuffer();
		
		oResponse.append( "name : " );
		oResponse.append( mFriendlyName );
		oResponse.append( ", DeviceType : " );
		oResponse.append( mDeviceType );
		oResponse.append( ", location : " );
		oResponse.append( mLocation );
		
		return oResponse.toString();
	}
	
}
