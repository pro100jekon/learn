package com.epam.smyrnov.module13;

import com.epam.smyrnov.module13.model.Log;
import com.epam.smyrnov.module13.model.Order;
import com.epam.smyrnov.module13.model.Status;
import com.epam.smyrnov.module13.model.Type;
import com.epam.smyrnov.module13.sender.MessageReceiver;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import javax.jms.JMSException;

public class Receiver {

    private static final BigDecimal THRESHOLD = BigDecimal.valueOf(2000);
    private static final Map<String, BigDecimal> USERS = new HashMap<>();

    public static void main(String[] args) throws JsonProcessingException, InterruptedException {
        MessageReceiver receiver = new MessageReceiver();
        while (true) {
            Order order = receiver.receiveOrder();
            if (order != null) {
                USERS.putIfAbsent(order.getUsername(), BigDecimal.ZERO);
                Log log = new Log();
                log.setOrder(order);
                log.setStatus(Status.ACCEPTED);
                if (order.getOrderTotal().compareTo(THRESHOLD) > 0) {
                    log.setStatus(Status.REJECTED);
                }
                if (order.getType().equals(Type.LIQUID)) {
                    if (USERS.get(order.getUsername()).add(order.getVolumeOrCount()).compareTo(THRESHOLD) > 0) {
                        log.setStatus(Status.REJECTED);
                    }
                }
                if (log.getStatus().equals(Status.ACCEPTED)) {
                    USERS.put(order.getUsername(), USERS.get(order.getUsername()).add(order.getVolumeOrCount()));
                }
                receiver.logResult(log);
            }
            Thread.sleep(2000);
        }
    }
}
