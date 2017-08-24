/**
 * 
 */
package com.aequalis.youtube.blockchainapi;

/**
 * @author anand
 *
 */
public interface WEBAPI {
	
	
	// Server 23.239.23.232
	
	static String BASE = "http://10.0.0.14:3001/";
	static String BLOCKCHAIN_URL = "http://10.0.0.14:8545";
	
//	static String BASE = "http://23.239.23.232:3001/";
//	static String BLOCKCHAIN_URL = "http://23.239.23.232:8545";
		
	static String GETCHANNELSCOUNT = "getChannelsCount";
	
	static String ADDNEWCHANNEL = "addNewChannel";
	
	static String ADDNEWCHANNEL_DATA = "{ \"_title\":\"PARAM1\", \"_id\":\"PARAM2\", \"_owner\":\"PARAM3\", \"_subscribers\":PARAM4 }";

	static String GETCHANNELDETAIL = "getChannel";
	
	static String GETCHANNELDETAIL_DATA = "{ \"_currentIndex\":PARAM1 }";
	
	static String TRANSFERTOKEN = "calculateAndTransferToken";
	
	static String TRANSFERTOKEN_DATA = "{ \"_selectedIndex\":PARAM1, \"_subscribers\":PARAM2}";
	
}