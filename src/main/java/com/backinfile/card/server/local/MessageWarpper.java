package com.backinfile.card.server.local;

import com.backinfile.card.model.Human;
import com.backinfile.dSync.model.DSyncBaseHandler.DSyncBase;

public class MessageWarpper {
	public String token;
	public String content;

	public static MessageWarpper pack(Human human, DSyncBase msg) {
		MessageWarpper messageWarpper = new MessageWarpper();
		messageWarpper.token = human.token;
		messageWarpper.content = msg.toMessage();
		return messageWarpper;
	}
}
