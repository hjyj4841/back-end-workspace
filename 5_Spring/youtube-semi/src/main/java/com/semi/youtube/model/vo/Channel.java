package com.semi.youtube.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class Channel {
	private int channelCode;
	private String channelImg;
	private String channelName;
	private String id; // member
}
