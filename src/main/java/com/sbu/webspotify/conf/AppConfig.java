package com.sbu.webspotify.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:config.yml")
public class AppConfig {

    @Value("${role.basicUser}")
    public String basicUser;

    @Value("${role.adminUser}")
    public String adminUser;

    @Value("${role.premiumUser}")
    public String premiumUser;

    @Value("${role.labelUser}")
    public String labelUser;

    @Value("${role.artistUser}")
    public String artistUser;

    @Value("${query.recentFavoritesToQuery}")
    public int recentFavoritesToQuery;

    @Value("${query.listeningHistoryToQuery}")
    public int listeningHistoryToQuery;
    

}