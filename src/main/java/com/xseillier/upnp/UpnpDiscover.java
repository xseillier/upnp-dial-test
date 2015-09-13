package com.xseillier.upnp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.xseillier.upnp.exception.UpnpException;
import com.xseillier.upnp.model.UpnpDevice;
import com.xseillier.upnp.task.ParseUpnpDeviceTask;

/**
 * 
 * @author xseillier
 *
 */
public class UpnpDiscover {

	
	public static interface UpnpDiscoverListener{
		public void startUpnpDiscover();
		public void finishUpnpDiscover();
		public void deviceFind( UpnpDevice aUpnpDevice );
	}
	
	
	
	private static String MULTICAST_IP       = "239.255.255.250";
	private static int    MULTICAST_PORT_DST = 1900;
	private static int    PORT_SRC           = 0; // listen on random port
	private static int    TIMEOUT            = 5;
	private static int    THREAD_NB          = 2;
	
	private List<UpnpDiscoverListener> mlistener = new ArrayList<UpnpDiscoverListener>();
	
	private ExecutorService executorService = Executors.newFixedThreadPool( THREAD_NB );
	
	
	/**
	 * find upnp device
	 * @param aServiceType
	 * @throws UnknownHostException
	 * @throws UpnpException 
	 */
	public void start(String aServiceType ) throws UnknownHostException, UpnpException{
		start( aServiceType, InetAddress.getLocalHost() );
	}
	
	/**
	 * find upnp device
	 * @param aServiceType
	 * @param aInetAddress
	 * @throws UpnpException 
	 * @throws UnknownHostException 
	 */
	public void start( String aServiceType, InetAddress aInetAddress ) throws UpnpException, UnknownHostException{
			
		findStartUpnpDiscover();
		MulticastSocket socket = null;
		DatagramPacket inPacket = null;
		
		List<ParseUpnpDeviceTask> aTasks = new ArrayList<ParseUpnpDeviceTask>();
		
	    byte[] inBuf = new byte[256];
	    try {
	    	
	        InetSocketAddress srcAddress = new InetSocketAddress(aInetAddress, PORT_SRC);
	        // Send to 239.255.255.250:1900
	        InetSocketAddress dstAddress = new InetSocketAddress(InetAddress.getByName(MULTICAST_IP), MULTICAST_PORT_DST);
	    	
		     socket = new MulticastSocket( null );
		     socket.bind( srcAddress );
	    	 socket.setSoTimeout( TIMEOUT * 1000 );
		  
		     StringBuffer searchRequest = new StringBuffer();
		      
		     searchRequest.append("M-SEARCH * HTTP/1.1\r\n");
		     searchRequest.append("HOST:");
		     searchRequest.append(MULTICAST_IP);
		     searchRequest.append(":");
		     searchRequest.append(MULTICAST_PORT_DST);
		     searchRequest.append("\r\n");
		     searchRequest.append("MAN:\"ssdp:discover\"\r\n");
		     searchRequest.append("MX:" + TIMEOUT + "\r\n");
		     searchRequest.append("ST:");
		     searchRequest.append( aServiceType );
		     searchRequest.append("\r\n");
		     searchRequest.append("\r\n");
		      
		     byte[] raw = searchRequest.toString().getBytes();
		     socket.send( new  DatagramPacket( raw, raw.length, dstAddress )  );		      
		     
		     while (true) {	        
		    	inPacket = new DatagramPacket(inBuf, inBuf.length );
		        socket.receive( inPacket );	         	        
		        aTasks.add( new ParseUpnpDeviceTask(  new String(inBuf, 0, inPacket.getLength() ) ) );	        
		     }
	    } catch (java.net.SocketTimeoutException ioe) {
	    } catch (IOException e) {
			e.printStackTrace();
		}
	    finally
	    {
	    	if( socket != null )
	    	{
	    		socket.close();
	    	}
	    	if( aTasks.size() > 0  ) {
	    		try {
	    			List< Future<UpnpDevice> > result = executorService.invokeAll( aTasks );
	    			executorService.shutdown();
	    			for( Future<UpnpDevice> oDevice : result ) {
	    				
	    				if( oDevice.isDone() ) {
	    					try
	    					{
	    						fireDeviceFind( oDevice.get() );
	    					}
	    					catch( Exception ue )
	    					{
	    						System.out.println( ue.getMessage() );
	    					}
	    				}
	    			}
				}
	    		catch (InterruptedException e) {e.printStackTrace();}
	    	}
	    	fireEndUpnpDiscover();
	    }
		
	}
	
	
	/**
	 * add new listener
	 * @param aListener
	 */
	public void addListener( UpnpDiscoverListener aListener ){
		mlistener.add( aListener );
	}
	
	/**
	 * remove listener
	 * @param aListener
	 */
	public void removeListener( UpnpDiscoverListener aListener ){
		mlistener.remove( aListener );
	}
	
	/**
	 * 
	 */
	private void findStartUpnpDiscover() {
		for( UpnpDiscoverListener oListener : mlistener ) {
			oListener.startUpnpDiscover();
		}
	}
	
	/**
	 * 
	 * @param aUpnpDevice
	 */
	private void fireDeviceFind( UpnpDevice aUpnpDevice ) {
		for( UpnpDiscoverListener oListener : mlistener ) {
			oListener.deviceFind( aUpnpDevice );
		}
	}
	
	/**
	 * 
	 */
	private void fireEndUpnpDiscover() {
		for( UpnpDiscoverListener oListener : mlistener ) {
			oListener.finishUpnpDiscover();
		}
	}

}