package com.xseillier.upnp.service.dial;



/**
 * find all app list at  http://www.dial-multiscreen.org/dial-registry/namespace-database
 * @author xseillier
 *
 */
public enum DialApplication {

	
	YOUTUBE("YouTube");
	
	
	private String mValue;
	
	private DialApplication( String aValue ){
		mValue = aValue;
	}
	
	public String getValue() {
		return mValue;
	}
}
