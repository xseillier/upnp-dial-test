package com.xseillier.upnp.parse;

import java.io.StringReader;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.xseillier.upnp.exception.UpnpException;
import com.xseillier.upnp.model.UpnpDevice;
import com.xseillier.upnp.model.UpnpService;
import com.xseillier.upnp.model.UpnpServiceImpl;


/**
 * 
 * @author xseillier
 *
 */
public class ParseDescriptionData {

	private static final String HEADER_APPLICATION_URL = "Application-URL";
	private static final String TAG_DEVICETYPE         = "deviceType";
	private static final String TAG_FRIENDLY_NAME      = "friendlyName";
	private static final String TAG_UDN                = "UDN";
	private static final String TAG_SERVICE            = "Service";	
	private static final String TAG_SERVICETYPE        = "serviceType";
	private static final String TAG_SERVICEID          = "serviceId";
	private static final String TAG_SCPDURL            = "SCPDURL";
	private static final String TAG_CONTROLURL         = "controlURL";
	private static final String TAG_EVENTSUBURL        = "eventSubURL";
	
	private UpnpDevice mUpnpDevice;
	private String mTagBody;
		
	
	public void parse( UpnpDevice aUpnpDevice, String aContent ) throws UpnpException{		
		parse( aUpnpDevice, null, aContent );
	}
	
	public void parse( UpnpDevice aUpnpDevice, Map<String, List<String>> aHeaders, String aContent ) throws UpnpException{
		
		mUpnpDevice = aUpnpDevice;
		
		if( aHeaders != null ) {
			processHeader( aHeaders );
		}
		
		processContent( aContent );		
	}


	
	
	private void processHeader( Map<String, List<String>> aHeaders ) {
		
		List<String> oApplicationHeader = aHeaders.get( HEADER_APPLICATION_URL );
		if( oApplicationHeader != null && oApplicationHeader.size() > 0 ) {
			mUpnpDevice.setApplicationUrl( oApplicationHeader.get( 0 ) );
		}
					
	}
	
	
	private void processContent( String aContent ) throws UpnpException{
		SAXParserFactory aFactory = SAXParserFactory.newInstance();
		SAXParser saxParser;
		try {
			saxParser = aFactory.newSAXParser();
			saxParser.parse(new InputSource( new StringReader( aContent ) ), new ParseDescriptionHandler() );
			  
		} catch (Exception e) {
			throw new UpnpException("Error to parse data. error : " + e.getClass().getName() +" - "+ e.getMessage() );
		}  
	}
	
	
	

/**
 * 	
 * @author xseillier
 *
 */
class ParseDescriptionHandler extends DefaultHandler {
			Stack<Object> mObjectStack = new Stack<>();

			public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
				 
				 if( TAG_SERVICE.equalsIgnoreCase( qName ) ) {
				 		mObjectStack.push( new UpnpServiceImpl() );
				 }
				 
			 }
			
			
			 public void endElement(String uri, String localName, String qName) throws SAXException {

				 	if( TAG_FRIENDLY_NAME.equalsIgnoreCase( qName ) ) {
				 		mUpnpDevice.setFriendlyName( mTagBody );
				 	} 
				 	else if( TAG_UDN.equalsIgnoreCase( qName ) ){
				 		mUpnpDevice.setUniqueDeviceName( mTagBody );
			 		}
				 	else if ( TAG_DEVICETYPE.equalsIgnoreCase( qName ) ) {
				 		mUpnpDevice.setDeviceType( mTagBody );
				 	}
				 	else if( TAG_SERVICETYPE.equalsIgnoreCase( qName ) ){
				 		( (UpnpService ) mObjectStack.peek() ).setServiceType( mTagBody );
			 		}
				 	else if( TAG_SERVICEID.equalsIgnoreCase( qName ) ){
				 		( (UpnpService ) mObjectStack.peek() ).setServiceTypeId( mTagBody );
			 		}
				 	else if( TAG_SCPDURL.equalsIgnoreCase( qName ) ){
				 		( (UpnpService ) mObjectStack.peek() ).setSCPDURL( mTagBody );
			 		}
				 	else if( TAG_CONTROLURL.equalsIgnoreCase( qName ) ){
				 		( (UpnpService ) mObjectStack.peek() ).setControlURL( mTagBody );
			 		}
					else if( TAG_EVENTSUBURL.equalsIgnoreCase( qName ) ){
				 		( (UpnpService ) mObjectStack.peek() ).setEventSubURL( mTagBody );
			 		}
					else if( TAG_SERVICE.equalsIgnoreCase( qName )  ){
						mUpnpDevice.addDeviceType( ( UpnpService ) mObjectStack.pop() );
			 		}
			 }
			 
			 public void characters(char ch[], int start, int length)
				        throws SAXException {
				 mTagBody = new String(ch, start, length).trim();
			 }
	 }
}
	
