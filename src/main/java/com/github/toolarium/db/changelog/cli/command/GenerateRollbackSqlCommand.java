/*
 * GenerateRollbackSqlCommand.java
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
import picocli.CommandLine.Option;


/**
 * Generate rollback SQL for a specified number of applied changesets without executing.
 *
 * @author patrick
 */
@Command(name = "generate-rollback-sql",
         description = "Generate rollback SQL for a specified number of applied changesets.")
public class GenerateRollbackSqlCommand implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(GenerateRollbackSqlCommand.class);

    @Mixin
    private DatabaseConnectionMixin db;

    @Mixin
    private ChangelogMixin changelog;

    @Option(names = {"--count"}, description = "Number of changesets to generate rollback SQL for", required = true)
    private int count;


    /**
     * Generate rollback SQL for the specified number of applied changesets.
     */
    @Override
    public void run() {
        // TODO: integrate with IChangelogRunner.generateRollbackSql(count)
        LOG.info("Generating rollback SQL for {} changeset(s)...", count);
    }
}
