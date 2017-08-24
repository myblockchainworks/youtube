/**
 * 
 */
package com.aequalis.youtube.main;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;

import com.aequalis.youtube.blockchainapi.WebAPICall;
import com.aequalis.youtube.dto.ChannelDTO;

/**
 * @author anand
 *
 */
public class SchedulerController {

	@Scheduled(fixedDelay = 3600000)
    public void demoServiceMethod()
    {
		System.out.println("Triggered Alert at :: "+ new Date());
		List<ChannelDTO> channels = WebAPICall.listChannels();
		for (int i = 0; i < channels.size(); i++) {
			String date = channels.get(i).getLastPayoutDate();
			long milliSeconds= Long.parseLong(date);

			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(milliSeconds);
			calendar.add(Calendar.DATE, 28);
			Calendar nowCalender = Calendar.getInstance();
			if (nowCalender.getTimeInMillis() > calendar.getTimeInMillis()) {
				System.out.println("Updated @ " + channels.get(i).getId());
				ChannelDTO channel = WebAPICall.getChannelDetailFromYoutube(channels.get(i).getId());
				WebAPICall.transferToken(i, channel.getSubscriberCount());
			}
		}
    }
}
