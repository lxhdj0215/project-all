package com.lxhdj.bean;

import java.util.Map;

public class ClassBean {
    // 魔数
    private String magic;
    // 次版本号
    private int minorVersion;
    // 主版本号
    private int majorVersion;
    // 常量池容量
    private int constantPoolCount;
    // 常量池
    private Map<Integer, ConstantPool> map;

    public String getMagic() {
        return magic;
    }

    public void setMagic(String magic) {
        this.magic = magic;
    }

    public int getMinorVersion() {
        return minorVersion;
    }

    public void setMinorVersion(int minorVersion) {
        this.minorVersion = minorVersion;
    }

    public int getMajorVersion() {
        return majorVersion;
    }

    public void setMajorVersion(int majorVersion) {
        this.majorVersion = majorVersion;
    }

    public int getConstantPoolCount() {
        return constantPoolCount;
    }

    public void setConstantPoolCount(int constantPoolCount) {
        this.constantPoolCount = constantPoolCount;
    }

    public Map<Integer, ConstantPool> getMap() {
        return map;
    }

    public void setMap(Map<Integer, ConstantPool> map) {
        this.map = map;
    }

}
