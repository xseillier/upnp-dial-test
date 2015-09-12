package com.xseillier.upnp.model;

import java.util.List;

/**
 * 
 * @author xseillier
 *
 */
public interface UpnpDevice {

	public String getFriendlyName();
	public void setFriendlyName( String aFriendlyName );
	
	public String getUniqueDeviceName();
	public void   setUniqueDeviceName( String aUniqueDeviceName );
		
	public String getLocation();	
	public void setLocation(String aString );	
	
	public String getDeviceType();
	public void setDeviceType( String aServiceType);
	
	public List<UpnpService> getService();
	public void addDeviceType( UpnpService aUpnpService);
		
	public void   setApplicationUrl(String string);
	public String getApplicationUrl();
	
}
