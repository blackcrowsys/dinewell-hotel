package com.blackcrowsys.dinewell.hotel.common.config;

import com.blackcrowsys.commonutils.db.FlywayMigrator;
import com.blackcrowsys.commonutils.db.Migration;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DatabaseConfigurationTests {

    @Mock
    private DataSource dataSource;

    @InjectMocks
    private DatabaseConfiguration config;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFlyway() {
        Flyway actual = config.flyway();
        assertEquals(Flyway.class, actual.getClass());
    }

    @Test
    public void testMigration() {
        Migration migration = config.migration();
        assertEquals(FlywayMigrator.class, migration.getClass());
    }
}
