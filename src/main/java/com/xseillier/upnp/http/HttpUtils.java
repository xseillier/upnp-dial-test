package com.xseillier.upnp.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author xseillier
 *
 */
public class HttpUtils {

	public static final String HEADER_CONTENT_TYPE = "Content-Type";
	
	 
	public static final String METOHD_POST         = "POST";
	public static final String METOHD_GET          = "GET";
	public static final String METOHD_DELETE       = "DELETE";
		
	
	/**
	 * 
	 * @param aUrl
	 * @param aData
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public static HttpPage sendPOSTData( String aUrl, String aData ) throws MalformedURLException, IOException {
		return sendPOSTData( aUrl, null,  aData );
	}
	
	/**
	 * 
	 * @param aUrl
	 * @param aContentType
	 * @param aData
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public static HttpPage sendPOSTData( String aUrl, String aContentType, String aData ) throws MalformedURLException, IOException {
		
	
		DataOutputStream oDataOutputStream = null;		
		HttpURLConnection oURLConnection   = null;
		
		oURLConnection = (HttpURLConnection) new URL( aUrl ).openConnection();	
		
		if( aContentType != null && !"".equals( aContentType ) ) {
			oURLConnection.setRequestProperty( HEADER_CONTENT_TYPE, aContentType ); 
		}
		
		oURLConnection.setRequestMethod( METOHD_POST );
		oURLConnection.setDoOutput(true);
		if( aData !=null ) {
			oDataOutputStream = new DataOutputStream( oURLConnection.getOutputStream() );
			oDataOutputStream.writeBytes( aData );
			oDataOutputStream.flush();
			oDataOutputStream.close();
		}
		
		
		return processResponse( oURLConnection ); 
	
	}
	
	
	
	/**
	 * 
	 * @param aUrl
	 * @return
	 * @throws IOException 
	 * @throws MalformedURLException 
	 */
	public static HttpPage sendGETData( String aUrl ) throws MalformedURLException, IOException {
		return sendGETData( aUrl, null,  null );
	}
	
	/**
	 * 
	 * @param aUrl
	 * @param aData
	 * @return
	 * @throws IOException 
	 * @throws MalformedURLException 
	 */
	public static HttpPage sendGETData( String aUrl, String aData ) throws MalformedURLException, IOException {
		return sendGETData( aUrl, null,  aData );
	}
	
	/**
	 * 
	 * @param aUrl
	 * @param aContentType
	 * @param aData
	 * @return
	 * @throws IOException 
	 * @throws MalformedURLException 
	 */
	public static HttpPage sendGETData( String aUrl, String aContentType, String aData ) throws MalformedURLException, IOException {
		HttpURLConnection oURLConnection  = null;
							
		String oFinalUrl = aUrl;
		if(  aData != null && !"".equals( aData )  ) {
			oFinalUrl = oFinalUrl + "?" + aData;
		}
		
		oURLConnection = (HttpURLConnection) new URL( oFinalUrl ).openConnection();
		oURLConnection.setRequestMethod( METOHD_GET );
		
		if( aContentType != null && !"".equals( aContentType ) ) {
			oURLConnection.setRequestProperty( HEADER_CONTENT_TYPE, aContentType ); 
		}
					
		return processResponse( oURLConnection );
	}

	
	
	public static HttpPage sendDELETE( String aUrl ) throws MalformedURLException, IOException {
		HttpURLConnection oURLConnection  = null;
				
		oURLConnection = (HttpURLConnection) new URL( aUrl ).openConnection();
		oURLConnection.setRequestMethod( METOHD_DELETE );
					
		return processResponse( oURLConnection );
	}
	
	
	/**
	 * 
	 * @param aInput
	 * @return
	 */
	public static Map<String, String> extractHttpHeaderFromRawREquest(String aInput ) 
	{
	
		String[] oHeadersArray = aInput.split("\r\n");
	    
		Map<String, String> oHeaders = new HashMap<String, String>();
	    
		for (int i = 1; i < oHeadersArray.length - 1; i++) { 	
	    	int oIndex = oHeadersArray[ i ].indexOf(":");
	    	if( oIndex > -1 )
	    	{	
	    		String oKey    = oHeadersArray[i].substring(0, oIndex  );
	    		String oValue  = "";
	    		if( oIndex < oHeadersArray[ i ].length() )
	    		{
	    			oValue = oHeadersArray[i].substring( oIndex + 1 );
	    		}
	    		oHeaders.put(oKey.trim(), oValue.trim() );
	    	}
	    }
		return oHeaders;
	}
	
	/**
	 * 
	 * @param aURLConnection
	 * @return
	 * @throws IOException
	 */
	private static HttpPage processResponse( HttpURLConnection aURLConnection ) throws IOException {
		
		BufferedReader oContent           = null;
		Map<String, List<String>> aHeader = null;		
		StringBuffer oResponse            = new StringBuffer();
		int oResponsestatus               = -1;
		aHeader = aURLConnection.getHeaderFields();
		
		try {
			oResponsestatus = aURLConnection.getResponseCode();
			oContent = new BufferedReader( new InputStreamReader( aURLConnection.getInputStream() ) );
		
			String inputLine;
		
			while ((inputLine = oContent.readLine()) != null) {
				oResponse.append(inputLine);
			}
			
		} 
		finally
		{
			if( oContent != null ){
				oContent.close();
			}
		}
		return new HttpPage( aHeader, oResponsestatus, oResponse.toString() );
	}
}
