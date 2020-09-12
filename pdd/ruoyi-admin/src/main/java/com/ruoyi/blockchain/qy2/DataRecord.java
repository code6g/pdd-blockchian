package com.ruoyi.blockchain.qy2;


public class DataRecord {

    private String key;

    private String value;

    public DataRecord(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String[] toStringArray() {
        return new String[] {this.getKey(),this.getValue()};
    }

    @Override
    public String toString() {
        return "Data: key = "+key+"  value = " +value;
    }
}
