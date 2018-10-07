package com.blackcrowsys.dinewell.hotel.common.config;

import com.hazelcast.config.Config;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.instance.HazelcastInstanceProxy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HazelcastConfigurationTests {

    private HazelcastConfiguration config;

    @BeforeEach
    public void setUp() {
        config = new HazelcastConfiguration();
    }

    @Test
    public void configTests() {
        Config actual = config.config();

        assertEquals(Config.class, actual.getClass());
    }

    @Test
    public void hazelcastInstanceTests() {
        HazelcastInstance actual = config.hazelcastInstance();

        assertEquals(HazelcastInstanceProxy.class, actual.getClass());
    }
}
