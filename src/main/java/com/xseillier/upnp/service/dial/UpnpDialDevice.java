package com.xseillier.upnp.service.dial;

import java.util.List;

import com.xseillier.upnp.model.UpnpDevice;

/**
 * 
 * @author xseillier
 *
 */
public interface UpnpDialDevice extends UpnpDevice {

	
	public List<DialApplication> getApplication();
	public void addApplication(DialApplication aDialApplication);
	
}
