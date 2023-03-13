package net.javaguides.springboot.proxyPattern;

public class RealInternet implements Internet{

    public RealInternet() {

    }
    @Override
    public void connect(String websiteUrl) {

        System.out.println("Connecting to internet " + websiteUrl);
    }
}
