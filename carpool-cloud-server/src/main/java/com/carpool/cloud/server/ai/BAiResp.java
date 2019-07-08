package com.carpool.cloud.server.ai;

import java.util.List;

import lombok.Data;

@Data
public class BAiResp {
	private List<Item> items;
	private String mobile;
	private String start;
	private String end;
	private String time;// 时间
	private String way;// 路线
	private String type;// 类型

	@Data
	public static class Item {
		private String item;// 解析的文本字段
		private String ne; // 类型 TIME-LOC

	}
}