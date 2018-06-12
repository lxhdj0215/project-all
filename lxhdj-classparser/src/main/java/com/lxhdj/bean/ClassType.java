package com.lxhdj.bean;

public enum ClassType {
	ACC_PUBLIC(0x0001, "public"), ACC_FINAL(0x0010, "final"), ACC_SUPER(0x0020, "super"), ACC_INTERFACE(0x0200,
			"interface"), ACC_ABSTRACT(0x0400, "abstract"), ACC_SYNTHETIC(0x1000,
					"synthetic"), ACC_ANNOTATION(0x2000, "annotation"), ACC_ENUM(0x4000, "enum");
	private int value;

	private String type;

	private ClassType(int value, String type) {
		this.value = value;
		this.type = type;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
