package com.lxhdj.bean;

import java.util.List;

public class ConstantPool {
	// 序号
	private int serialNumber;
	// 类型
	private int type;
	// 值
	private Object value;
	// 索引
	private List<Integer> indexs;

	public int getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public List<Integer> getIndexs() {
		return indexs;
	}

	public void setIndexs(List<Integer> indexs) {
		this.indexs = indexs;
	}

}
