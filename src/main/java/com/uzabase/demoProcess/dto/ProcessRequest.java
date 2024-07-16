package com.uzabase.demoProcess.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProcessRequest {
	private String input;
	private String inputSource;
	private String operation;
	private String output;
	private String outputSource;
	private String replaceFrom;
	private String replaceTo;
}
