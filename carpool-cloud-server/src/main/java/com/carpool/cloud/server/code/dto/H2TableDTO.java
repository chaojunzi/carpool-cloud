package com.carpool.cloud.server.code.dto;

import com.carpool.cloud.server.code.core.IDatabaseInfo;

import lombok.Data;

@Data
public class H2TableDTO implements IDatabaseInfo{

	private String tableSchema;
	private String tableName;
 
}
