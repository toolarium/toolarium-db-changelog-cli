/*
 * UpdateTestingRollbackCommand.java
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


/**
 * Apply each pending changeset, rollback, and re-apply to test rollback correctness.
 *
 * @author patrick
 */
@Command(name = "update-testing-rollback",
         description = "Apply, rollback, and re-apply each pending changeset to test rollback correctness.")
public class UpdateTestingRollbackCommand implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(UpdateTestingRollbackCommand.class);

    @Mixin
    private DatabaseConnectionMixin db;

    @Mixin
    private ChangelogMixin changelog;

    @Mixin
    private RunnerMixin runner;


    /**
     * Apply, rollback, and re-apply each pending changeset to verify rollback correctness.
     */
    @Override
    public void run() {
        // TODO: integrate with IChangelogRunner.updateTestingRollback()
        LOG.info("Executing update-testing-rollback...");
    }
}
