package com.example.demo.beandefinition;

import com.example.demo.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// XML, 자바코드등을 reader 를 통해 Appconfig.class (Appconfig.xml 등)의 설정 정보을 읽어서
// BeanDefinition 이라는 정보를 생성한다. 스프링 컨테이너에서 그 BeanDefinition 정보를 읽어들이면 끝!
// => 스프링 컨테이너가 다양한 형식의 정보 (XML, 자바코드 등) 을 BeanDefinition 을 통해 읽어들일 수 있다.
// => 스프링은 BeanDefinition 으로 스프링 빈의 설정 메타 정보를 추상화한다.

// 스프링 빈을 만들때는 방법이 2가지가있다.
// => 1. 개발자가 직접 등록하는 방식 2. FactoryBean 을 통해 등록하는 방식
// 자바코드의 AppConfig 는 FactoryBean 을 통해 등록되는 방식이다.

public class BeanDefinitionTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    // AnnotationConfigApplicationContext 가 아닌 ApplicationContext 을 쓰면, ac.getBeanDefinition() 을 못한다

    @Test
    @DisplayName("빈 설정 메타정보 확인")
    void findApplicationBean(){
        String[] names = ac.getBeanDefinitionNames(); // // BeanDefinition 의 이름 얻어오기
        for(String bean_name : names){
            BeanDefinition beanDefinition = ac.getBeanDefinition(bean_name); // BeanDefinition 메타정보 얻어오기

            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                System.out.println("beanDefinitionName = " + bean_name);
                System.out.println("beanDefinition = " + beanDefinition); // BeanDefinition 메타정보 출력해보기
            }
        }
    }
}
