/**
 * 
 */
package com.dbquery.factory;

import com.dbquery.dal.MSSQLDBInfo;
import com.dbquery.interfaces.DBBasicInfo;

/**
 * Microsoft SQL Server 工厂，用于创建SQLServer的数据库信息
 * @author Frank
 * @version 1.0
 */
public final class MSSQLFactory implements DatabaseFactory {

	/* (non-Javadoc)
	 * @see com.dbquery.factory.DatabaseFactory#databaseInfo()
	 */
	@Override
	public DBBasicInfo databaseInfo() {
		return new MSSQLDBInfo();
	}

	/* (non-Javadoc)
	 * @see com.dbquery.factory.DatabaseFactory#databaseInfo(java.lang.String)
	 */
	@Override
	public DBBasicInfo databaseInfo(String databaseName) {
		return new MSSQLDBInfo(databaseName, "127.0.0.1");
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
