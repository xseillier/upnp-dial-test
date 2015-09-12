package com.xseillier.upnp.service.dial.task;

import java.util.concurrent.Callable;

import com.xseillier.upnp.service.dial.DialApplication;
import com.xseillier.upnp.service.dial.DialUtils;
import com.xseillier.upnp.service.dial.UpnpDialDevice;

/**
 * 
 * @author xseillier
 *
 */
public class FindAllDialAppOnUpnpDeviceTask implements Callable<Void> {

	private UpnpDialDevice mUpnpDialDevice;
	
	public FindAllDialAppOnUpnpDeviceTask( UpnpDialDevice aUpnpDialDevice ) {
		mUpnpDialDevice =  aUpnpDialDevice;
	}
		
	
	public Void call() throws Exception {
			
		for( DialApplication oDialApplication : DialApplication.values() ) {
			if( DialUtils.checkApp ( mUpnpDialDevice, oDialApplication) ) {
				mUpnpDialDevice.addApplication( oDialApplication );
			}
		}
		return null;
			
	}

	
	
	
}
	
