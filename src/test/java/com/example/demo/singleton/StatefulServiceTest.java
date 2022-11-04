package com.example.demo.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA : A 사용자 10000원 주문
        //statefulService1.order("userA", 10000);
        // ThreadB : B 사용자 20000원 주문
        //statefulService2.order("userB", 20000);

        // ThreadA: 사용자 A 주문금액 조회
        //int price = statefulService1.getPrice(); // 동일한 인스턴스 객체를 공유하므로, 20000원이 출력된다.
        // (statefulService1 과 statefulService2 는 동일한 싱글톤 객체임)
        //System.out.println("price = " + price);

        int userAPrice = statefulService1.order("userA", 10000);
        int userBprice = statefulService2.order("userB", 20000);
        System.out.println("price = " + userAPrice);

        //Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig{

        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }
}