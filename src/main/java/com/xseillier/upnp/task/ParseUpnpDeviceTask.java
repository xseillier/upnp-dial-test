package com.xseillier.upnp.task;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.Callable;

import com.xseillier.upnp.http.HttpPage;
import com.xseillier.upnp.http.HttpUtils;
import com.xseillier.upnp.model.UpnpDevice;
import com.xseillier.upnp.model.UpnpDeviceImpl;
import com.xseillier.upnp.parse.ParseDescriptionData;
import com.xseillier.upnp.parse.ParseDiscoveryData;


/**
 * 
 * @author xseillier
 *
 */
public class ParseUpnpDeviceTask implements Callable<UpnpDevice> {

	private String     mRawRequest;
	
	public ParseUpnpDeviceTask( String aRawRequest ) {
		mRawRequest =  aRawRequest;
	}
	
	
	
	public UpnpDevice call() throws Exception {
		
		UpnpDevice oUpnpDevice = new UpnpDeviceImpl();
		
		//parse discovery Data
		ParseDiscoveryData oParseDiscoveryData = new ParseDiscoveryData();
		oParseDiscoveryData.parse(oUpnpDevice, mRawRequest);
		
		//get description
		HttpPage oHttpPage =  getDescriptionData( oUpnpDevice.getLocation() );
		
		//parse Description Data
		ParseDescriptionData oParseDescriptionData = new ParseDescriptionData();
		oParseDescriptionData.parse(oUpnpDevice, oHttpPage.getHeader(), oHttpPage.getContent() );
			
		return oUpnpDevice;
	}

	
	private HttpPage getDescriptionData( String aLocation ) throws MalformedURLException, IOException
	{
		return HttpUtils.sendGETData( aLocation );
	}
}
	
