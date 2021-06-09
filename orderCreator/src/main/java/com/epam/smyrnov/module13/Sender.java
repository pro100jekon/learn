package com.epam.smyrnov.module13;

import com.epam.smyrnov.module13.model.Order;
import com.epam.smyrnov.module13.model.Type;
import com.epam.smyrnov.module13.sender.MessageSender;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.Scanner;

public class Sender {

    public static void main(String[] args) throws JsonProcessingException {
        Scanner scanner = new Scanner(System.in);
        Order order = new Order();
        System.out.println("Enter your username");
        order.setUsername(scanner.nextLine());
        System.out.println("Select type: countable (1) or liquid (2)");
        order.setType(Type.values()[scanner.nextInt() - 1]);
        System.out.println("Enter volume or count (depends on your type, numeric)");
        order.setVolumeOrCount(scanner.nextBigDecimal().abs());
        System.out.println("Enter order total (numeric)");
        order.setOrderTotal(scanner.nextBigDecimal().abs());
        new MessageSender().sendMessage(order);
        System.out.println("Thanks! Your order is being processed");
    }
}
