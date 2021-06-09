package com.epam.smyrnov.module13.model;

import java.math.BigDecimal;

public class Order {

    private String username;
    private Type type;
    private BigDecimal volumeOrCount;
    private BigDecimal orderTotal;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public BigDecimal getVolumeOrCount() {
        return volumeOrCount;
    }

    public void setVolumeOrCount(BigDecimal volumeOrCount) {
        this.volumeOrCount = volumeOrCount;
    }

    public BigDecimal getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(BigDecimal orderTotal) {
        this.orderTotal = orderTotal;
    }

    @Override
    public String toString() {
        return "Order{" +
            "username='" + username + '\'' +
            ", type=" + type +
            ", volumeOrCount=" + volumeOrCount +
            ", orderTotal=" + orderTotal +
            '}';
    }
}
