package com.xseillier.upnp.model;


/**
 * 
 * @author xseillier
 *
 */
public interface UpnpService {

	public String getServiceType();
	public void setServiceType(String aServiceType);
	
	public String getServiceTypeId();
	public void setServiceTypeId( String aServiceTypeId);
	
	public String getSCPDURL();
	public void setSCPDURL( String aSCPDURL );
	
	public String getControlURL();
	public void setControlURL( String aControlURL );
	
	public String getEventSubURL();
	public void setEventSubURL( String aEventSubURL );
	
}
