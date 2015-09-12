package com.xseillier.upnp.service.dial;

import java.io.IOException;

import com.xseillier.upnp.http.HttpPage;
import com.xseillier.upnp.http.HttpUtils;
import com.xseillier.upnp.service.dial.exception.DialAppException;
/**
 * 
 * @author xseillier
 *
 */
public class DialUtils {

	
	public static boolean checkApp( UpnpDialDevice aUpnpDialDevice, DialApplication aDialApplication ) throws DialAppException{		
		
		HttpPage oHttpPAge;
		try {
			oHttpPAge = HttpUtils.sendGETData( aUpnpDialDevice.getApplicationUrl() + aDialApplication.getValue() );
			if( oHttpPAge.getResponseStatus() == 200 ) {
				return true;
			}
			
			return false;
		} catch (IOException e) {
			throw new DialAppException("Error to check if app is available. Error : " + e.getMessage() );
		} 	
	}
}
