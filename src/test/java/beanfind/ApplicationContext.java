package beanfind;

import com.example.demo.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class ApplicationContext {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean(){
        String[] beanDefinitionNames =  ac.getBeanDefinitionNames(); // 정의된 빈 이름들을 모두 String 배열에 할당
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            // ac.getBeanDefinitionNames() : 스프링에 등록한 모든 빈 이름들을 조회
            // ac.getBean() : 빈 이름으로 빈 객체((인스턴스)를 조회
            System.out.println("name = " + beanDefinitionName + "object = " + bean);
        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplictionAllBean(){
        String[] beanDefinitionNames =  ac.getBeanDefinitionNames(); // 정의된 빈 이름들을 모두 String 배열에 할당
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName); // getBeanDefinition : 각 bean 하나하나에 대한 meta data 정보

            // ROLE_APPLICATION : 스프링이 내부에서 뭔가를 하기위해 시스템적으로 자동으로 등록한 빈이 아닌, 내가(개발자가) 애플리케이션을 개발하기 위해 등록한 빈
            // ROLE_INFRASTRUCTURE : 스프링이 내부에서 시스템적으로 등록한 빈
            // => ROLE_APPLICATION 을 통해 스프링이 내부에서 사용하는 빈은 제외하고, 내가 등록한 빈만 출력할 수 있다.
            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + "object = " + bean);
            }
        }
    }
}
