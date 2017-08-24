/**
 * 
 */
package com.aequalis.youtube.blockchainapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.aequalis.youtube.dto.ChannelDTO;

/**
 * @author anand
 *
 */
public class WebAPICall {
	
	
	public static int getChannelsCount() {
		int result = 0;
		try {
			URL url = new URL(WEBAPI.BASE + WEBAPI.GETCHANNELSCOUNT);
			HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
			httpCon.setDoOutput(true);
			httpCon.setRequestMethod("GET");
		  
			BufferedReader br;
			if (200 <= httpCon.getResponseCode() && httpCon.getResponseCode() <= 299) {
				br = new BufferedReader(new InputStreamReader((httpCon.getInputStream())));
				StringBuilder sb = new StringBuilder();
				String output;
				while ((output = br.readLine()) != null) {
					sb.append(output);
				}
				
				JSONObject json = new JSONObject(sb.toString());
				if(json.has("channelsCount"))
					result = json.getInt("channelsCount");
				
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static String addNewChannel(String title, String id, String onwer, int subscribers) {
		String transactionAddress = "0x12345";
		try {
			URL url = new URL(WEBAPI.BASE + WEBAPI.ADDNEWCHANNEL);
			HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
			httpCon.setDoOutput(true);
			httpCon.setRequestMethod("POST");
			httpCon.setRequestProperty("Content-Type","application/json");
			OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
			String data = WEBAPI.ADDNEWCHANNEL_DATA.replace("PARAM1", title).replace("PARAM2", id).replace("PARAM3", onwer).replace("PARAM4", "" + subscribers);
			out.write(data);
			out.close();
		  
			BufferedReader br;
			if (200 <= httpCon.getResponseCode() && httpCon.getResponseCode() <= 299) {
				br = new BufferedReader(new InputStreamReader((httpCon.getInputStream())));
				StringBuilder sb = new StringBuilder();
				String output;
				while ((output = br.readLine()) != null) {
					sb.append(output);
				}
				
				transactionAddress = sb.toString();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return transactionAddress;
	}
	
	public static ChannelDTO getChannelDetail(int currentIndex) {
		ChannelDTO channel = null;
		try {
			URL url = new URL(WEBAPI.BASE + WEBAPI.GETCHANNELDETAIL);
			HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
			httpCon.setDoOutput(true);
			httpCon.setRequestMethod("POST");
			httpCon.setRequestProperty("Content-Type","application/json");
			OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
			String data = WEBAPI.GETCHANNELDETAIL_DATA.replace("PARAM1", "" + currentIndex);
			out.write(data);
			out.close();
		  
			BufferedReader br;
			if (200 <= httpCon.getResponseCode() && httpCon.getResponseCode() <= 299) {
				br = new BufferedReader(new InputStreamReader((httpCon.getInputStream())));
				StringBuilder sb = new StringBuilder();
				String output;
				while ((output = br.readLine()) != null) {
					sb.append(output);
				}
				
				JSONObject json = new JSONObject(sb.toString());
				if(json.has("Title")) {
					channel = new ChannelDTO();
					channel.setTitle(json.getString("Title"));
					channel.setId(json.getString("Id"));
					channel.setOwner(json.getString("Owner"));
					channel.setLastPayoutDate(json.getString("LastPayoutDate"));
					channel.setSubscriberCount(Integer.parseInt(json.getString("Subscribers")));
					channel.setToken(Integer.parseInt(json.getString("Token")));
				}
				
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return channel;
	}
	
	public static String transferToken(int selectIndex, int subscribers) {
		String transactionAddress = "0x12345";
		try {
			URL url = new URL(WEBAPI.BASE + WEBAPI.TRANSFERTOKEN);
			HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
			httpCon.setDoOutput(true);
			httpCon.setRequestMethod("POST");
			httpCon.setRequestProperty("Content-Type","application/json");
			OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
			String data = WEBAPI.TRANSFERTOKEN_DATA.replace("PARAM1", "" + selectIndex).replace("PARAM2", "" + subscribers);
			out.write(data);
			out.close();
		  
			BufferedReader br;
			if (200 <= httpCon.getResponseCode() && httpCon.getResponseCode() <= 299) {
				br = new BufferedReader(new InputStreamReader((httpCon.getInputStream())));
				StringBuilder sb = new StringBuilder();
				String output;
				while ((output = br.readLine()) != null) {
					sb.append(output);
				}
				
				transactionAddress = sb.toString();
			} else {
				System.out.println(httpCon.getResponseCode() + " " + httpCon.getResponseMessage());
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return transactionAddress;
	}
	
	public static ChannelDTO getChannelDetailFromYoutube(String channelId) {
		ChannelDTO channelDTO = null;
		try {
			URL url = new URL("https://www.googleapis.com/youtube/v3/channels?part=snippet,statistics&id="+channelId+"&key=AIzaSyCTAbIo-Qf-E-xoPJpRNLrjVU2JFNQzWY8");
			HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
			httpCon.setDoOutput(true);
			httpCon.setRequestMethod("GET");
		  
			BufferedReader br;
			if (200 <= httpCon.getResponseCode() && httpCon.getResponseCode() <= 299) {
				br = new BufferedReader(new InputStreamReader((httpCon.getInputStream())));
				StringBuilder sb = new StringBuilder();
				String output;
				while ((output = br.readLine()) != null) {
					sb.append(output);
				}
				
				JSONObject json = new JSONObject(sb.toString());
				if(json.has("items")) {
					
					JSONArray arr = json.getJSONArray("items");
					if (arr.length() > 0) {
						channelDTO = new ChannelDTO();
						JSONObject obj = (JSONObject) arr.get(0);
						JSONObject snippet = obj.getJSONObject("snippet");
						channelDTO.setTitle(snippet.getString("title"));
						JSONObject statistics = obj.getJSONObject("statistics");
						int subscriberCount = statistics.getInt("subscriberCount");
						channelDTO.setSubscriberCount(subscriberCount);
					}
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return channelDTO;
	}
	
	public static List<ChannelDTO> listChannels() {
		List<ChannelDTO> channels = new ArrayList<ChannelDTO>();
		ChannelDTO channel;
		
		int totalCoins = getChannelsCount();
		
		for (int index = 0; index < totalCoins; index++) {
			channel = getChannelDetail(index);
			channels.add(channel);
		}
		
		return channels;
	}
	
	public static boolean isChannelAvailable(String id) {
		boolean result = true;
		int totalCoins = getChannelsCount();
		
		for (int index = 0; index < totalCoins; index++) {
			ChannelDTO channel = getChannelDetail(index);
			if (channel.getId().equalsIgnoreCase(id)) {
				result = false;
				break;
			}
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(WebAPICall.getChannelDetailFromYoutube("UCWxHFO8iXG7zVIL29M6K6og"));
	}
}
