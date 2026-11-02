/*
 * HistoryCommand.java
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
 * Show the history of applied changesets.
 *
 * @author patrick
 */
@Command(name = "history", description = "Show the history of applied changesets.")
public class HistoryCommand implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(HistoryCommand.class);

    @Mixin
    private DatabaseConnectionMixin db;


    /**
     * Display the history of applied changesets.
     */
    @Override
    public void run() {
        // TODO: integrate with IChangelogRunner.history()
        LOG.info("Showing history...");
    }
}
