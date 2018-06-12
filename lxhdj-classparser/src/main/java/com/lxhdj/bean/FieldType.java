package com.lxhdj.bean;

public enum FieldType {
	ACC_PUBLIC(0x0001, "public"), ACC_PRIVATE(0x0002, "private"), ACC_PROTECTED(0x0004, "protected"), ACC_STATIC(0x0008,
			"static"), ACC_FINAL(0x0010, "final"), ACC_VOLATILE(0x0040, "volatile"), ACC_TRANSIENT(0x0080,
					"transient"), ACC_SYNTHETIC(0x1000, "synthetic"), ACC_ENUM(0x4000, "enum");

	private int value;

	private String type;

	private FieldType(int value, String type) {
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
