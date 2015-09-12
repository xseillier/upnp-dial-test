package com.xseillier.upnp.service.dial;

/**
 * 
 * @author xseillier
 *
 */
public abstract class AbstractDialApp implements DialApp {

	
	protected UpnpDialDevice mUpnpDialDevice;
	
	public AbstractDialApp( UpnpDialDevice aUpnpDialDevice ) {
		mUpnpDialDevice = aUpnpDialDevice;
	}
	
	@Override
	public String getAppUrl()
	{
		return  mUpnpDialDevice.getApplicationUrl() + getAppUri();
	}
	
	
}
