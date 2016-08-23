package com.dbquery.dal;

import com.dbquery.interfaces.DBBasicInfo;

/**
 * MSSQL数据库信息类，在此设置并获取数据库的信息
 * @author Frank
 * @version 1.0
 * */
public class MSSQLDBInfo extends DBBasicInfo {
	
	/**
	 * 构造函数，使用默认端口和默认本机数据库
	 */
	public MSSQLDBInfo() {
		this.driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		this.port = 1433;		//默认端口为1433
		this.url = "jdbc:sqlserver://localhost";// 默认URL地址
	}
	
	
	/* (non-Javadoc)
	 * @see com.databasegenerate.dal.DBBasicInfo#getUrl()
	 */
	@Override
	public String getUrl() {
		if (this.machineAddress == null) {
			if (this.databaseName == null) {
				
				return this.url + ":" + this.port + ";";// 未指定数据库名
			}
			
			return this.url + ":" + this.port + ";DatabaseName=" + this.databaseName; // 未指定数据库地址则使用默认本机地址
		}
		if (this.databaseName == null) {
			
			return "jdbc:sqlserver://" + this.machineAddress + ":" + this.port + ";"; // 未指定数据库名，则使用指定地址
		}
		
		return "jdbc:sqlserver://" + this.machineAddress + ":" + this.port + ";DatabaseName=" + this.databaseName;
		
	}

	/**
	 * 根据数据库名和主机地址构造对象
	 * @param databaseName 数据库名
	 * @param machineAddress 主机地址
	 * */
	public MSSQLDBInfo(String databaseName, String machineAddress) {
		this.databaseName = databaseName;
		this.setMachineAddress(machineAddress);
	}
}
