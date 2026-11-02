/*
 * ValidateCommand.java
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
 * Validate the changelog for errors.
 *
 * @author patrick
 */
@Command(name = "validate", description = "Validate the changelog for errors.")
public class ValidateCommand implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(ValidateCommand.class);

    @Mixin
    private DatabaseConnectionMixin db;

    @Mixin
    private ChangelogMixin changelog;


    /**
     * Validate the changelog for errors.
     */
    @Override
    public void run() {
        // TODO: integrate with IChangelogRunner.validate()
        LOG.info("Validating changelog...");
    }
}
