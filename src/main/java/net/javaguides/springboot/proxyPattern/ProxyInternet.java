package net.javaguides.springboot.proxyPattern;

import java.util.ArrayList;
import java.util.List;

public class ProxyInternet implements Internet {

    private Internet internet;

    List<String> bannedWebsites = new ArrayList<>();

    public ProxyInternet() {

        bannedWebsites.add("abc.com");
        bannedWebsites.add("xyz.com");
    }

    @Override
    public void connect(String websiteUrl) throws Exception{

        // check for banned sites
        if (bannedWebsites.contains(websiteUrl)) {
            throw new Exception("Website is restricted " + websiteUrl);
        }
        internet = new RealInternet();
        internet.connect(websiteUrl);
    }
}
