/*
 * FutureRollbackSqlCommand.java
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
 * Generate rollback SQL for pending changesets.
 *
 * @author patrick
 */
@Command(name = "future-rollback-sql", description = "Generate rollback SQL for pending changesets.")
public class FutureRollbackSqlCommand implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(FutureRollbackSqlCommand.class);

    @Mixin
    private DatabaseConnectionMixin db;

    @Mixin
    private ChangelogMixin changelog;


    /**
     * Generate rollback SQL for all pending changesets.
     */
    @Override
    public void run() {
        // TODO: integrate with IChangelogRunner.generateFutureRollbackSql()
        LOG.info("Generating future rollback SQL...");
    }
}
