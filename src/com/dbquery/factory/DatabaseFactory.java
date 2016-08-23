/**
 * 
 */
package com.dbquery.factory;

import com.dbquery.interfaces.DBBasicInfo;

/**
 * 数据库工厂，用于创建不同的数据库
 * @author Frank
 * @version 1.0
 */
public interface DatabaseFactory {
	
	/**
	 * 创建一个数据库信息
	 * @return 数据库信息
	 */
	public DBBasicInfo databaseInfo();
	
	/**
	 * 创建数据库信息，并指定数据库名，使用本地IP地址和默认端口
	 * @param databaseName 数据库名
	 * @return 数据库信息
	 */
	public DBBasicInfo databaseInfo(String databaseName);
	
	/**
	 * 创建数据库信息，并指定数据库名和端口号，使用本地IP
	 * @param databaseName 数据库名
	 * @param port 端口号
	 * @return 数据库信息
	 */
	public DBBasicInfo databaseInfo(String databaseName, int port);
	
	/**
	 * 创建数据库信息，并指定IP地址，数据库名，端口号
	 * @param url 主机地址
	 * @param databaseName 数据库名
	 * @param port 端口号
	 * @return 数据库信息
	 */
	public DBBasicInfo databaseInfo(String url, String databaseName, int port);
}
