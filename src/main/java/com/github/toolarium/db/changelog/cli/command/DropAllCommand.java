/*
 * DropAllCommand.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.db.changelog.cli.command;

import com.github.toolarium.db.changelog.cli.mixin.DatabaseConnectionMixin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine.Command;
import picocli.CommandLine.Mixin;
import picocli.CommandLine.Option;


/**
 * Drop all database objects (dev/test only).
 *
 * @author patrick
 */
@Command(name = "drop-all", description = "Drop all database objects. USE WITH CAUTION!")
public class DropAllCommand implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(DropAllCommand.class);

    @Mixin
    private DatabaseConnectionMixin db;

    @Option(names = {"--force"}, description = "Confirm destructive operation", required = true)
    private boolean force;


    /**
     * Drop all database objects when the force flag is set.
     */
    @Override
    public void run() {
        // TODO: integrate with IChangelogRunner.dropAll()
        if (force) {
            LOG.info("Dropping all database objects...");
        }
    }
}
