package com.dbquery.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.dbquery.interfaces.DBBasicInfo;
import com.dbquery.interfaces.IConnection;

/**
 * 数据库连接类，提供数据库的连接
 * @author Frank
 * @version 1.0
 */
public class DBConnection implements IConnection {

	/**
	 * 数据库连接
	 */
	protected Connection connection;			// 数据库连接实例
	/**
	 * 数据库信息
	 */
	protected DBBasicInfo dbinfo;				// 数据库信息
	
	/* (non-Javadoc)
	 * @see com.dbquery.interfaces.IConnection#connection()
	 */
	@Override
	public Connection connection() {
		return connection;
	}

	/**
	 * 设置数据库信息
	 * @param dbinfo the dbinfo to set
	 */
	public void setDbinfo(DBBasicInfo dbinfo) {
		this.dbinfo = dbinfo;
	}
	/* (non-Javadoc)
	 * @see com.dbquery.interfaces.IConnection#openConnection()
	 */
	@Override
	public boolean openConnection() throws SQLException, ClassNotFoundException {
		if (this.dbinfo == null) {
			return false;
		}
		if (this.dbinfo.getUsername() == null || this.dbinfo.getPassword() == null) {
			return false;
		}
		
		// 开始尝试连接
		// 反射获取驱动
		Class.forName(this.dbinfo.getDriver());
		// 根据用户名密码连接
		this.connection = DriverManager.getConnection(this.dbinfo.getUrl(), this.dbinfo.getUsername(), this.dbinfo.getPassword());
		if (this.connection == null) {
			return false;// 失败
		}
		return true;
	}
	
	/* (non-Javadoc)
	 * @see com.dbquery.interfaces.IConnection#closeConnection()
	 */
	@Override
	public boolean closeConnection() throws SQLException {
		this.connection.close();
		return true;
	}

	/* (non-Javadoc)
	 * @see com.dbquery.interfaces.IConnection#isOpened()
	 */
	@Override
	public boolean isOpened() throws SQLException {
		if (this.connection == null) {
			return false; // 数据连接未实例化
		}
		return !this.connection.isClosed();
	}

	/**
	 * @return 数据库信息
	 */
	public DBBasicInfo getDbinfo() {
		return dbinfo;
	}
	
}
