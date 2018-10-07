package com.blackcrowsys.dinewell.hotel.common.config;

import akka.actor.ActorSystem;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class AkkaSystemConfig {

    public static final String ACTORSYSTEMNAME = "DinewellHotelSystem";

    private ActorSystem actorSystem;

    @PostConstruct
    public void init(){
        actorSystem = ActorSystem.create(ACTORSYSTEMNAME);
    }

    @Bean
    public ActorSystem actorSystem(){
        return actorSystem;
    }
}
