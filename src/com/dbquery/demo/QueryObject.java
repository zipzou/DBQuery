/**
 * 
 */
package com.dbquery.demo;

import com.dbquery.dal.DBConnection;
import com.dbquery.factory.DatabaseFactory;
import com.dbquery.factory.MySQLFactory;
import com.dbquery.interfaces.AbstractQueryObject;
import com.dbquery.interfaces.DBBasicInfo;

/**
 * 测试查询类
 * @author Frank
 *
 */
public final class QueryObject extends AbstractQueryObject {

	/* (non-Javadoc)
	 * @see com.dbquery.interfaces.AbstractQueryObject#setupDatabase()
	 */
	@Override
	public void setupDatabase() {
		DatabaseFactory factory = new MySQLFactory();
		DBBasicInfo mysql = factory.databaseInfo("test");
		DBConnection connection = new DBConnection();
		mysql.setUsername("root");
		mysql.setPassword("123456");
		connection.setDbinfo(mysql);
		dbManager = connection;
	}

	private final static String SQL_CREATE_TABLE = "CREATE TABLE TB_QUERY(id int primary key, content char(120) not null);";
	/**
	 * 示例，创建一个表
	 * @return
	 */
	public boolean createTable() {
		avoidNotBindDatabase();
		return this.update(SQL_CREATE_TABLE, new Object());
	}
}
