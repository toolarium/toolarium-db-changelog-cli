/*
 * GenerateChangelogCommand.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.db.changelog.cli.command;

import com.github.toolarium.db.changelog.cli.mixin.DatabaseConnectionMixin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine.Command;
import picocli.CommandLine.Mixin;
import picocli.CommandLine.Option;


/**
 * Generate a changelog from the current database schema.
 *
 * @author patrick
 */
@Command(name = "generate-changelog", description = "Generate a changelog from the current database schema.")
public class GenerateChangelogCommand implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(GenerateChangelogCommand.class);

    @Mixin
    private DatabaseConnectionMixin db;

    @Option(names = {"--output", "-o"}, description = "Output file path", required = true)
    private String outputPath;


    /**
     * Generate a changelog from the current database schema.
     */
    @Override
    public void run() {
        // TODO: integrate with IChangelogRunner.generateChangelog()
        LOG.info("Generating changelog to " + outputPath + "...");
    }
}
