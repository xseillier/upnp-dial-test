package com.xseillier.upnp.service.dial;

import java.io.IOException;
import java.net.URLEncoder;

import com.xseillier.upnp.http.HttpUtils;
import com.xseillier.upnp.service.dial.exception.DialAppException;

/**
 * 
 * @author xseillier
 *
 */
public class YouTubeApp extends AbstractDialApp{

	
	public YouTubeApp( UpnpDialDevice aUpnpDialDevice ) throws DialAppException  {
		super( aUpnpDialDevice );
		if( !mUpnpDialDevice.getApplication().contains( DialApplication.YOUTUBE ) ) {
			throw new DialAppException( mUpnpDialDevice.getFriendlyName() + " not support " + DialApplication.YOUTUBE.name() + " apps." );
		}
	}
	
	
	public void playVideo( String aVideoId ) throws DialAppException {
		
		try {
			HttpUtils.sendPOSTData( getAppUrl(), "application/x-www-form-urlencoded", "v="+ URLEncoder.encode( aVideoId ,"UTF-8" ) );
		
		} catch (IOException e) {
			
			throw new DialAppException( "Youtube app error : " + e.getMessage() );
		}
	}

	
	public void stopVideo() throws DialAppException {		
		
		try {
			HttpUtils.sendDELETE( getAppUrl() );
		} catch (Exception e) {
			throw new DialAppException("Error to stop Video. Error : " + e.getMessage() );
		}
		
	}


	@Override
	public String getAppUri() {
		return DialApplication.YOUTUBE.getValue();
	}
}
