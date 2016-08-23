/**
 * 
 */
package com.dbquery.factory;

import com.dbquery.dal.MySQLDBInfo;
import com.dbquery.interfaces.DBBasicInfo;

/**
 * MySQL数据库工厂，用于创建MySQL的数据库信息
 * @author Frank
 * @version 1.0
 */
public final class MySQLFactory implements DatabaseFactory {

	/* (non-Javadoc)
	 * @see com.dbquery.factory.DatabaseFactory#databaseInfo()
	 */
	@Override
	public DBBasicInfo databaseInfo() {
		return new MySQLDBInfo();
	}

	/* (non-Javadoc)
	 * @see com.dbquery.factory.DatabaseFactory#databaseInfo(java.lang.String)
	 */
	@Override
	public DBBasicInfo databaseInfo(String databaseName) {
		return new MySQLDBInfo(databaseName, "127.0.0.1");
	}

	/* (non-Javadoc)
	 * @see com.dbquery.factory.DatabaseFactory#databaseInfo(java.lang.String, int)
	 */
	@Override
	public DBBasicInfo databaseInfo(String databaseName, int port) {
		DBBasicInfo info = databaseInfo(databaseName);
		info.setPort(port);
		return info;
	}

	/* (non-Javadoc)
	 * @see com.dbquery.factory.DatabaseFactory#databaseInfo(java.lang.String, java.lang.String, int)
	 */
	@Override
	public DBBasicInfo databaseInfo(String url, String databaseName, int port) {
		DBBasicInfo info = databaseInfo(databaseName, port);
		info.setMachineAddress(url);
		return info;
	}

}
