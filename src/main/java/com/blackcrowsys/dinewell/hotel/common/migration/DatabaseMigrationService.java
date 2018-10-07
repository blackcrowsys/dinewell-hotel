package com.blackcrowsys.dinewell.hotel.common.migration;

import com.blackcrowsys.commonutils.db.Migration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class DatabaseMigrationService {

    @Autowired
    private Migration migration;

    @PostConstruct
    public void migrateDatabase() {
        migration.migrate();
    }
}
