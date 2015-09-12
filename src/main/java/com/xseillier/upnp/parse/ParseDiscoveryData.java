package com.xseillier.upnp.parse;

import java.util.Map;

import com.xseillier.upnp.exception.UpnpException;
import com.xseillier.upnp.http.HttpUtils;
import com.xseillier.upnp.model.UpnpDevice;


/**
 * 
 * @author xseillier
 *
 */
public class ParseDiscoveryData  {

	private static final String HEADER_LOCATION = "LOCATION";
	
	public void  parse( UpnpDevice aUpnpDevice,  String aInput ) throws UpnpException
	{
				    
		Map<String, String> headers = HttpUtils.extractHttpHeaderFromRawREquest( aInput );
	   	
		String oLocation = headers.get( HEADER_LOCATION );
		if( oLocation != null ) {
			aUpnpDevice.setLocation( oLocation );
		} else {
			throw new UpnpException( "Error to parse discovery response" );
		}
	}
	
}
	
