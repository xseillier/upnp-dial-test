package com.xseillier.upnp.service.dial;

import java.util.ArrayList;
import java.util.List;

import com.xseillier.upnp.model.UpnpDevice;
import com.xseillier.upnp.model.UpnpService;


/**
 * 
 * @author xseillier
 *
 */
public class UpnpDialDeviceImpl implements UpnpDialDevice {

	
	private UpnpDevice mUpnpDevice;

	List<DialApplication> mDialApplication = new ArrayList<>(); 
	
	public void setApplicationUrl(String string) {
		mUpnpDevice.setApplicationUrl(string);
	}

	public String getApplicationUrl() {
		return mUpnpDevice.getApplicationUrl();
	}

	public UpnpDialDeviceImpl( UpnpDevice aUpnpDevice ) {	
		 mUpnpDevice =  aUpnpDevice;
	}
	
	@Override
	public String getFriendlyName() {
		return mUpnpDevice.getFriendlyName();
	}

	@Override
	public void setFriendlyName(String aFriendlyName) {
		mUpnpDevice.setFriendlyName( aFriendlyName );
	}

	@Override
	public String getLocation() {
		return mUpnpDevice.getLocation();
	}

	@Override
	public void setLocation(String aString) {
		mUpnpDevice.setLocation( aString );
	}

	@Override
	public String getDeviceType() {
		return mUpnpDevice.getDeviceType();
	}

	@Override
	public void setDeviceType(String aServiceType) {
		mUpnpDevice.setDeviceType(aServiceType);
	}

	@Override
	public String getUniqueDeviceName() {
		return mUpnpDevice.getUniqueDeviceName();
	}

	@Override
	public void setUniqueDeviceName(String aUniqueDeviceName) {
		mUpnpDevice.setUniqueDeviceName(aUniqueDeviceName);
	}
			
	@Override
	public List<UpnpService> getService() {
		return mUpnpDevice.getService();
	}

	@Override
	public void addDeviceType(UpnpService aUpnpService) {
		mUpnpDevice.addDeviceType( aUpnpService );
	}
	
	@Override
	public List<DialApplication> getApplication() {
		// TODO Auto-generated method stub
		return mDialApplication;
	}

	@Override
	public void addApplication(DialApplication aDialApplication) {
		mDialApplication.add( aDialApplication );
	}
}
