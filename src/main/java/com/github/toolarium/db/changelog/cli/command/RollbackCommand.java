/*
 * RollbackCommand.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.db.changelog.cli.command;

import com.github.toolarium.db.changelog.cli.mixin.ChangelogMixin;
import com.github.toolarium.db.changelog.cli.mixin.DatabaseConnectionMixin;
import com.github.toolarium.db.changelog.cli.mixin.RunnerMixin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine.ArgGroup;
import picocli.CommandLine.Command;
import picocli.CommandLine.Mixin;
import picocli.CommandLine.Option;


/**
 * Rollback changesets from the database.
 *
 * @author patrick
 */
@Command(name = "rollback", description = "Rollback changesets by count, tag, or date.")
public class RollbackCommand implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(RollbackCommand.class);

    @Mixin
    private DatabaseConnectionMixin db;

    @Mixin
    private ChangelogMixin changelog;

    @Mixin
    private RunnerMixin runner;

    @ArgGroup(multiplicity = "1")
    private RollbackTarget target;


    /**
     * Rollback target: exactly one of count, tag, or date must be specified.
     *
     * @author patrick
     */
    static class RollbackTarget {
        @Option(names = {"--count"}, description = "Number of changesets to rollback")
        private Integer count;

        @Option(names = {"--tag"}, description = "Rollback to the specified tag")
        private String tag;

        @Option(names = {"--date"}, description = "Rollback to the specified date (ISO-8601)")
        private String date;
    }


    /**
     * Rollback changesets from the database.
     */
    @Override
    public void run() {
        // TODO: integrate with IChangelogRunner.rollback()
        LOG.info("Executing rollback...");
    }
}
