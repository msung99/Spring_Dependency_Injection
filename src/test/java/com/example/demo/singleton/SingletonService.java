package com.example.demo.singleton;

public class SingletonService {
    // static 으로 선언하면 공유가 가능함
    // static 영역에 객체 instance를 미리 딱 1개만 생성해서 올려둔다.
    private static final  SingletonService instance = new SingletonService();

    // 싱글톤 인스턴스 객체를 참조할 수 있는 유일한 함수
    // => public 으로 열어서 객체 인스턴스가 필요하면 이 static 메소드를 통해서만 조회하도록 허용한다.
    public static SingletonService getInstance(){
        return instance;
    }

    // 생성자를 private 으로 선언해서, 외부에서 싱글톤 객체가 생성되는것을 막는다.
    // 즉, 아무도 외부에서 new 키워드로 객체 생성을 못하게해서 유일하게 싱글톤 인스턴스 객체가 1개만 존재하게 된다.
    // (딱 1개의 싱글톤 객체 인스턴스만 존재해야 하므로)
    private SingletonService(){

    }

    public void logic(){
        System.out.println("싱글톤 로직 호출");
    }
}
