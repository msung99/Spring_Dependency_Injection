package com.example.demo.order;

import com.example.demo.AppConfig;
import com.example.demo.Order.Order;
import com.example.demo.Order.OrderService;
import com.example.demo.Order.OrderServiceImpl;
import com.example.demo.member.Grade;
import com.example.demo.member.Member;
import com.example.demo.member.MemberService;
import com.example.demo.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
    MemberService memberService; // = new MemberServiceImpl();
    OrderService orderService; // = new OrderServiceImpl();

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.OrderService();
    }

    @Test
    void createOrder(){
        long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itmeA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
