/*
 * DryRunCommand.java
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
 * Output the SQL that would be executed without applying changes.
 *
 * @author patrick
 */
@Command(name = "dry-run", description = "Output the SQL that would be executed without applying changes.")
public class DryRunCommand implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(DryRunCommand.class);

    @Mixin
    private DatabaseConnectionMixin db;

    @Mixin
    private ChangelogMixin changelog;


    /**
     * Generate and output the SQL that would be executed without applying changes.
     */
    @Override
    public void run() {
        // TODO: integrate with IChangelogRunner.generateSql()
        LOG.info("Generating SQL (dry-run)...");
    }
}
