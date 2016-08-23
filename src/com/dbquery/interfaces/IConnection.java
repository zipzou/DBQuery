/**
 * 
 */
package com.dbquery.interfaces;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 数据库连接接口
 * @author Frank
 * @version 1.0
 */
public interface IConnection {

	/**
	 * 打开数据库连接
	 * @throws SQLException 可能抛出数据库异常，包括用户名和密码的错误，主机的错误等
	 * @throws ClassNotFoundException 可能抛出驱动未找到的异常
	 * @return 返回是否连接成功
	 * */
	public boolean openConnection() throws SQLException, ClassNotFoundException;
	
	/**
	 * 关闭连接
	 * @throws SQLException 可能抛出数据库异常，连接不能正常关闭
	 * @return 返回是否关闭的状态
	 * */
	public boolean closeConnection() throws SQLException;
	
	/**
	 * 数据库是否连接
	 * @return 返回是否连接的状态
	 * @throws SQLException 可能抛出连接异常
	 * */
	public boolean isOpened() throws SQLException;
	
	/**
	 * 获取数据库连接
	 * @return 数据库连接
	 */
	public Connection connection();
}
