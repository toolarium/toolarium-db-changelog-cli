/*
 * UpdateCommand.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.db.changelog.cli.command;

import com.github.toolarium.db.changelog.cli.mixin.ChangelogMixin;
import com.github.toolarium.db.changelog.cli.mixin.DatabaseConnectionMixin;
import com.github.toolarium.db.changelog.cli.mixin.RunnerMixin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine.Command;
import picocli.CommandLine.Mixin;
import picocli.CommandLine.Option;


/**
 * Execute pending changesets against the database.
 *
 * @author patrick
 */
@Command(name = "update", description = "Execute pending changesets against the database.")
public class UpdateCommand implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(UpdateCommand.class);

    @Mixin
    private DatabaseConnectionMixin db;

    @Mixin
    private ChangelogMixin changelog;

    @Mixin
    private RunnerMixin runner;

    @Option(names = {"--count"}, description = "Number of changesets to apply")
    private Integer count;


    /**
     * Execute pending changesets against the database.
     */
    @Override
    public void run() {
        // TODO: integrate with IChangelogRunner.update()
        LOG.info("Executing update...");
    }
}
