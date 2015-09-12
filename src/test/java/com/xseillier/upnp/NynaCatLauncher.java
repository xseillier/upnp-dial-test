package com.xseillier.upnp;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.xseillier.upnp.UpnpDiscover;
import com.xseillier.upnp.UpnpDiscover.UpnpDiscoverListener;
import com.xseillier.upnp.exception.UpnpException;
import com.xseillier.upnp.model.UpnpDevice;
import com.xseillier.upnp.service.dial.DialApplication;
import com.xseillier.upnp.service.dial.DialUtils;
import com.xseillier.upnp.service.dial.UpnpDialDevice;
import com.xseillier.upnp.service.dial.UpnpDialDeviceImpl;
import com.xseillier.upnp.service.dial.YouTubeApp;
import com.xseillier.upnp.service.dial.exception.DialAppException;



public class NynaCatLauncher {

	public static String  DIAL_V1 		   = "urn:dial-multiscreen-org:service:dial:1";
	public static String NYAN_CAT_VIDEO_ID = "QH2-TGUlwu4";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		final List<UpnpDevice> oDeviceList = new ArrayList<UpnpDevice>();
		
		UpnpDiscover upnp = new UpnpDiscover();
		try {
			upnp.addListener( new UpnpDiscoverListener(){

				public void startUpnpDiscover() {
					// TODO Auto-generated method stub		
					System.out.println( "Start discovery" );
				}

				public void finishUpnpDiscover() {
					System.out.println( "Finish discovery" );
					if( oDeviceList.isEmpty() ) {
						System.out.println("Not Dial Device");
					}
					for( UpnpDevice oUpnpDevice : oDeviceList ){
						
						UpnpDialDevice oUpnpDialDevice = new UpnpDialDeviceImpl( oUpnpDevice );
								
						try {
							if( DialUtils.checkApp( oUpnpDialDevice , DialApplication.YOUTUBE) ) {
								oUpnpDialDevice.addApplication( DialApplication.YOUTUBE );
								System.out.println( "Launch video on " + oUpnpDialDevice.getFriendlyName() );
							//	new YouTubeApp( oUpnpDialDevice ).playVideo( NYAN_CAT_VIDEO_ID );
								new YouTubeApp( oUpnpDialDevice ).stopVideo();
							}
							else {
								System.out.println( "YouTube app not supported by  " + oUpnpDialDevice.getFriendlyName() );
							}
								
						} catch (DialAppException e) {
							e.printStackTrace();
						}
						
					}
				}

				public void deviceFind(UpnpDevice aUpnpDevice) {
					// TODO Auto-generated method stub
					System.out.println( aUpnpDevice );
					oDeviceList.add( aUpnpDevice );
					
				}});
						
			//upnp.start( DIAL_V1, InetAddress.getByName("192.168.1.100") );
			upnp.start( DIAL_V1, InetAddress.getLocalHost() );
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UpnpException e) {
			e.printStackTrace();
		}
	}

}
