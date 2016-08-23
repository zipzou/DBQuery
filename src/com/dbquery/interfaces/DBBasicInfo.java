
package com.dbquery.interfaces;

/**
 * 抽象基类，表示所有可用数据库的基类
 * @author Frank
 * @version 1.0
 */
public abstract class DBBasicInfo {

	protected String url;					// 数据连接URL
	protected String driver;				// 数据库驱动
	protected String username;				// 数据库用户名
	protected String password;				// 数据库密码
	protected String databaseName;			// 数据库名
	protected String machineAddress;		// 数据库主机名
	protected int port;						// 端口，默认端口根据数据库不同而不同
	
	/*getter 和 setter*/
	/**
	 * 获取数据库地址
	 * @return 数据库连接URL
	 * */
	public abstract String getUrl();
	/**
	 * 获取驱动路径
	 * @return 驱动名称
	 * */
	public String getDriver() {
		return driver;
	}
	/**
	 * 获取数据库登录用户名
	 * @return 数据库用户名
	 * */
	public String getUsername() {
		return username;
	}
	/**
	 * 设置数据库登录用户名
	 * @param username 用户名
	 * */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 获取登录用户密码
	 * @return 数据库密码
	 * */
	public String getPassword() {
		return password;
	}
	/**
	 * 设置用户登录密码
	 * @param password 密码
	 * */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * 获取数据库名
	 * @return 数据库名
	 * */
	public String getDatabaseName() {
		return databaseName;
	}
	/**
	 * 设置数据库名
	 * @param databaseName 数据库名
	 * */
	public void setDatabaseName(String databaseName){
		this.databaseName = databaseName;
	}

	/**
	 * 获取数据库主机地址
	 * @return 数据库主机地址
	 * */
	public String getMachineAddress() {
		return machineAddress;
	}

	/**
	 * 设置数据库主机地址
	 * @param machineAddress 主机地址
	 * */
	public void setMachineAddress(String machineAddress) {
		this.machineAddress = machineAddress;
	}

	/**
	 * 获取端口号
	 * @return the port
	 */
	public int getPort() {
		return port;
	}
	/**
	 * 设置端口号
	 * @param port 要设置的数据库端口
	 */
	public void setPort(int port) {
		this.port = port;
	}
}
