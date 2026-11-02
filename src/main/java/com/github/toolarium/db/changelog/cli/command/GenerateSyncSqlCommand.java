/*
 * GenerateSyncSqlCommand.java
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
 * Generate the SQL that the sync command would execute without applying changes.
 *
 * @author patrick
 */
@Command(name = "generate-sync-sql",
         description = "Generate the SQL that the sync command would execute without applying changes.")
public class GenerateSyncSqlCommand implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(GenerateSyncSqlCommand.class);

    @Mixin
    private DatabaseConnectionMixin db;

    @Mixin
    private ChangelogMixin changelog;


    /**
     * Generate the sync SQL and output it without applying changes.
     */
    @Override
    public void run() {
        // TODO: integrate with IChangelogRunner.generateSyncSql()
        LOG.info("Generating sync SQL...");
    }
}
