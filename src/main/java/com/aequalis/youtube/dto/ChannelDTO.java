/**
 * 
 */
package com.aequalis.youtube.dto;

/**
 * @author anand
 *
 */
public class ChannelDTO {

	private String title;
	private String id;
	private String owner;
	private String lastPayoutDate;
	private int subscriberCount;
	private int token;
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}
	/**
	 * @param owner the owner to set
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}
	/**
	 * @return the lastPayoutDate
	 */
	public String getLastPayoutDate() {
		return lastPayoutDate;
	}
	/**
	 * @param lastPayoutDate the lastPayoutDate to set
	 */
	public void setLastPayoutDate(String lastPayoutDate) {
		this.lastPayoutDate = lastPayoutDate;
	}
	/**
	 * @return the subscriberCount
	 */
	public int getSubscriberCount() {
		return subscriberCount;
	}
	/**
	 * @param subscriberCount the subscriberCount to set
	 */
	public void setSubscriberCount(int subscriberCount) {
		this.subscriberCount = subscriberCount;
	}
	/**
	 * @return the token
	 */
	public int getToken() {
		return token;
	}
	/**
	 * @param token the token to set
	 */
	public void setToken(int token) {
		this.token = token;
	} 
	
}
