/*
 * StatusCommand.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.db.changelog.cli.command;

import com.github.toolarium.db.changelog.cli.mixin.ChangelogMixin;
import com.github.toolarium.db.changelog.cli.mixin.DatabaseConnectionMixin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine.Command;
import picocli.CommandLine.Mixin;


/**
 * Show pending changesets that have not been applied.
 *
 * @author patrick
 */
@Command(name = "status", description = "Show pending changesets that have not been applied.")
public class StatusCommand implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(StatusCommand.class);

    @Mixin
    private DatabaseConnectionMixin db;

    @Mixin
    private ChangelogMixin changelog;


    /**
     * Display pending changesets that have not been applied.
     */
    @Override
    public void run() {
        // TODO: integrate with IChangelogRunner.status()
        LOG.info("Showing status...");
    }
}
