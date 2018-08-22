package com.xtkj.paopaoxiche.event;

public class BaseEvent {

    public static final int CAR_WASH_PICK_AVATAR = 100;
    public static final int GOODS_IMAGE = 101;

    public static final int SET_GOODS_IMAGE = 201;

    private int eventType;

    private Integer intValue = null;

    private String stringValue = null;

    private Boolean boolValue = null;

    public BaseEvent(int eventType) {
        this(eventType, null, null, null);
    }

    public BaseEvent(int eventType, Integer intValue) {
        this(eventType, intValue, null, null);
    }

    public BaseEvent(int eventType, String stringValue) {
        this(eventType, null, stringValue, null);
    }

    /**
     * boolean事件
     */
    public BaseEvent(int eventType, Boolean boolValue) {
        this(eventType, null, null, boolValue);
    }

    /**
     * 构造器
     */
    public BaseEvent(int eventType, Integer intValue, String stringValue, Boolean boolValue) {
        this.eventType = eventType;
        this.intValue = intValue;
        this.stringValue = stringValue;
        this.boolValue = boolValue;
    }

    public int getType() {
        return eventType;
    }

    public void setType(int type) {
        eventType = type;
    }

    public Integer getIntValue() {
        return intValue;
    }

    public void setIntValue(Integer intValue) {
        this.intValue = intValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public Boolean getBoolValue() {
        return boolValue;
    }
}
