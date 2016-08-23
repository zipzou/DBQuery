package com.dbquery.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.dbquery.demo.QueryObject;

public class TestUnit {

	@Test
	public void testCreateTable() {
		QueryObject query = new QueryObject();
		if(!query.createTable()) {
			fail("创建失败！");
		}
	}

}
