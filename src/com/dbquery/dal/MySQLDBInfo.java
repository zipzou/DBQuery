/**
 * 
 */
package com.dbquery.dal;

import com.dbquery.interfaces.DBBasicInfo;

/**
 * MySQL数据库信息
 * @author Frank
 * @version 1.0
 */
public class MySQLDBInfo extends DBBasicInfo {

	/**
	 * 构造函数，默认使用本机数据库和默认端口
	 */
	public MySQLDBInfo() {
		this.driver = "com.mysql.jdbc.Driver";
		this.port = 3306;		//默认端口为3306
		this.url = "jdbc:mysql://localhost" + "?useOldAliasMetadataBehavior=true&useUnicode=true&CharsetEncode=utf8";// 默认URL地址
	}

	/**
	 * 创建数据库并指定数据库名和IP地址
	 * @param databaseName 数据库名
	 * @param machineAddr IP地址
	 */
	public MySQLDBInfo(String databaseName, String machineAddr) {
		this();
		this.databaseName = databaseName;
		this.machineAddress = machineAddr;
	}
	
	/* (non-Javadoc)
	 * @see com.databasegenerate.dal.DBBasicInfo#getUrl()
	 */
	@Override
	public String getUrl() {
		if (this.machineAddress == null) {
			if (this.databaseName == null) {
				
				return this.url + ":" + this.port + "?useOldAliasMetadataBehavior=true";// 未指定数据库名
			}
			
			return this.url + ":" + this.port + "/" + this.databaseName + "?useOldAliasMetadataBehavior=true"; // 未指定数据库地址则使用默认本机地址
		}
		if (this.databaseName == null) {
			
			return "jdbc:mysql://" + this.machineAddress + ":" + this.port + "?useOldAliasMetadataBehavior=true"; // 未指定数据库名，则使用指定地址
		}
		
		return "jdbc:mysql://" + this.machineAddress + ":" + this.port + "/" + this.databaseName + "?useOldAliasMetadataBehavior=true";
	}

}
