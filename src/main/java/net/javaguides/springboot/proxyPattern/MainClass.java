package net.javaguides.springboot.proxyPattern;

public class MainClass {

    public static void main(String[] args) {

        Internet internet = new ProxyInternet();

        try {
            internet.connect("hello.com");
            internet.connect("abc.com");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
