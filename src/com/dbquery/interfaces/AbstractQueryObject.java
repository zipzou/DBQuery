package com.dbquery.interfaces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dbquery.interfaces.IConnection;


/**
 * 抽象查询类，提供数据库访问
 * @author Frank
 * @version 1.3
 */
public abstract class AbstractQueryObject {
	
	
	/**
	 * 数据库连接管理
	 */
	protected IConnection dbManager;
	
	/**
	 * 构造函数，在实例化时，调用子类的方法指定数据库
	 */
	public AbstractQueryObject() {
		avoidNotBindDatabase();
	}
	
	
	/**
	 * 更新数据
	 * @param sqlStatement SQL语句
	 * @param params 参数
	 * @return 返回是否更新成功, <b><i>true</i></b>表示更新成功, <b><i>false</i></b>表示更新失败
	 * */
	protected boolean update(String sqlStatement, Object... params){
		Connection dbConnection = null;
		try {
			dbConnection = this.connectToDatabase();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} //数据库连接
		PreparedStatement updateStatement = null;
		try {// 创建Statement防止注入
			updateStatement = dbConnection.prepareStatement(sqlStatement);
			this.formatStatement(updateStatement, params);// 格式化SQL语句
			boolean res =  updateStatement.executeUpdate() >= 0;// 更新结果
			updateStatement.close();
			return res;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;// 发生异常错误
		}finally {
			//关闭连接
			try {
				dbManager.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 删除数据
	 * @param sqlStatement SQL语句
	 * @param params 参数
	 * @return 已删除的记录数目
	 * */
	protected int delete(String sqlStatement, Object... params){
		Connection dbConnection = null;
		try {
			dbConnection = this.connectToDatabase();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		PreparedStatement delStatement = null;
		try {// 创建SQL声明语句，自动释放
			
			delStatement = dbConnection.prepareStatement(sqlStatement);
			
			// 填充语句
			this.formatStatement(delStatement, params);
			
			// 执行删除
			int rowCount = delStatement.executeUpdate();
			delStatement.close();
			return rowCount;
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			try {
				dbManager.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 插入数据
	 * @param sqlStatement SQL语句
	 * @param params 参数
	 * @return 返回是否插入成功, <b><i>true</i></b>表示更新成功, <b><i>false</i></b>表示更新失败
	 * */
	protected boolean insert(String sqlStatement, Object... params){
		Connection insConnection = null;
		try {
			insConnection = this.connectToDatabase();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} //数据库连接
		PreparedStatement insPrepareStatement = null;
		try {
			
			insPrepareStatement = insConnection.prepareStatement(sqlStatement);
			
			// 格式化SQL
			this.formatStatement(insPrepareStatement, params);
			
			// 执行插入
			int resCount = insPrepareStatement.executeUpdate();
			boolean res = resCount >= 0;
			// 关闭SQL
			insPrepareStatement.close();
			return res;
		}catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}finally {
			try {
				dbManager.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}// 关闭连接 
		}
		
	}
	/**
	 * 查询数据
	 * @param sqlStatement SQL语句
	 * @param params 参数
	 * @return 查询到的结果集
	 * */
	protected List<Map<String, Object>> select(String sqlStatement, Object... params){
		Connection selectConnection = null;
		try {
			selectConnection = this.connectToDatabase();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} // 连接
		ResultSet setResult = null;
		PreparedStatement selelctStatement = null;
		try  { // 获取到SQL声明
			selelctStatement = selectConnection.prepareStatement(sqlStatement);
			// 格式化声明
			this.formatStatement(selelctStatement, params);
			// 查询
			setResult = selelctStatement.executeQuery();// 执行查询
			// 结果集合转为集合
			
			List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
			
			while (setResult.next()) {
				Map<String, Object> rowObj = this.setTransformToMap(setResult); // 一条元祖
				
				if (null != rowObj) {
					results.add(rowObj); // 加入
				}
				
			}
			return results;
		}catch (SQLException ex) {
			ex.printStackTrace();
			return null; // 出现SQL异常
		} finally {
			try {
				setResult.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			//关闭SQL
			try {
				dbManager.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	/**
	 * 格式化SQL语句
	 * @param statement SQL语句
	 * @param params 要格式化填充的参数
	 * @throws SQLException 在填充SQL语句时，发生填充项索引和待填充项发生冲突的异常
	 * */
	private void formatStatement(PreparedStatement statement, Object... params) throws SQLException{
		for (int i = 0; i < params.length; i++) {
			statement.setObject(i + 1, params[i]);
		}
	}
	/**
	 * 连接到数据库
	 * @throws DBOpenException 数据库无法打开的异常
	 * @return 返回数据库连接实例
	 * @throws SQLException 数据库异常
	 * @throws ClassNotFoundException 驱动未找到
	 * */
	private Connection connectToDatabase() throws ClassNotFoundException, SQLException {
		if (dbManager.isOpened()) { // 是否连接已经打开
			return dbManager.connection();// 获取连接
		}else {
			dbManager.openConnection();
			return dbManager.connection();
		}
	}
	
	/**
	 * 将集合转为字典型数据
	 * @param set 当前行的数据集合
	 * @return 返回转换后的键值对
	 * */
	private Map<String, Object> setTransformToMap(ResultSet set) {
		try {
			int columnCount = set.getMetaData().getColumnCount();
			// 获取所有列
			Map<String, Object> queryObject = new HashMap<String, Object>();
			for (int col = 1; col <= columnCount; col++) {
				try {
					String colName = set.getMetaData().getColumnName(col); // 获取列名
					Object volValue = set.getObject(colName); // 列值
					queryObject.put(colName, volValue); // 生成键值对
				} catch (SQLException e) {
					e.printStackTrace();
					continue;
				}
			}
			return queryObject;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 动态构建SQL语句，并将值填充到参数表中
	 * @param map 要构建的动态参数
	 * @param fillInParams 要填充的参数表
	 * */
	protected String generateDynamicUpdateSQL(String tableName, Map<String, Object> map, Object[] fillInParams) {
		// 动态构建SQL语句字符串
		StringBuilder sqlBuilder = new StringBuilder("UPDATE "); // 创建SQLBuilder
		sqlBuilder.append(tableName).append("  SET  ");
		// 将Map中所有键构建到SQL中
		Set<String> keys = map.keySet(); // 所有键
		Iterator<String> keyIterator = keys.iterator(); // 迭代器
		int i = 0;
		while (keyIterator.hasNext()) {
			String key = keyIterator.next();
			if (!keyIterator.hasNext()) { //最后一个键
				sqlBuilder.append(key).append(" = ? ");
			} else {
				sqlBuilder.append(key).append(" = ? ,");
			}
			// 将值加入到属性中
			if (null != fillInParams) {
				fillInParams[i++] = map.get(key);
			}
		}
		
		// 构建完要更新的键
		
		// 设置唯一标识
		System.out.println(sqlBuilder);
		
		return sqlBuilder.toString();
	}
	/**
	 * 获取数据库管理类
	 * @return the dbManager
	 */
	public IConnection getDbManager() {
		return dbManager;
	}
	/*虚方法*/
	
	/**
	 * 载入数据库连接管理对象
	 */
	protected abstract void setupDatabase();
	
	/**
	 * 避免数据库未绑定信息
	 */
	protected void avoidNotBindDatabase() {
		if (null == this.dbManager) {
			setupDatabase();
		}
	}
}
