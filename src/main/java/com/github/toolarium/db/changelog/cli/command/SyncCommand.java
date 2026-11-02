/*
 * SyncCommand.java
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
 * Mark all pending changesets as executed without running them (baseline).
 *
 * @author patrick
 */
@Command(name = "sync", description = "Mark all pending changesets as executed without running them.")
public class SyncCommand implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(SyncCommand.class);

    @Mixin
    private DatabaseConnectionMixin db;

    @Mixin
    private ChangelogMixin changelog;


    /**
     * Mark all pending changesets as executed without running them.
     */
    @Override
    public void run() {
        // TODO: integrate with IChangelogRunner.sync()
        LOG.info("Syncing changelog...");
    }
}
