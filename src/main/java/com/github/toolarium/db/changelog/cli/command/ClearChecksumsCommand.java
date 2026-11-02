/*
 * ClearChecksumsCommand.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.db.changelog.cli.command;

import com.github.toolarium.db.changelog.cli.mixin.DatabaseConnectionMixin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine.Command;
import picocli.CommandLine.Mixin;


/**
 * Clear all stored checksums so they are recalculated on the next update.
 *
 * @author patrick
 */
@Command(name = "clear-checksums", description = "Clear all stored checksums from the tracking table.")
public class ClearChecksumsCommand implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(ClearChecksumsCommand.class);

    @Mixin
    private DatabaseConnectionMixin db;


    /**
     * Clear all stored checksums so they are recalculated on the next update.
     */
    @Override
    public void run() {
        // TODO: integrate with IChangelogRunner.clearChecksums()
        LOG.info("Clearing checksums...");
    }
}
