/*
 * TagCommand.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.db.changelog.cli.command;

import com.github.toolarium.db.changelog.cli.mixin.DatabaseConnectionMixin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine.Command;
import picocli.CommandLine.Mixin;
import picocli.CommandLine.Parameters;


/**
 * Tag the current database state for future rollback.
 *
 * @author patrick
 */
@Command(name = "tag", description = "Tag the current database state for future rollback.")
public class TagCommand implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(TagCommand.class);

    @Mixin
    private DatabaseConnectionMixin db;

    @Parameters(index = "0", description = "Tag name to apply")
    private String tagName;


    /**
     * Tag the current database state for future rollback.
     */
    @Override
    public void run() {
        // TODO: integrate with IChangelogRunner.tag()
        LOG.info("Tagging database as '" + tagName + "'...");
    }
}
