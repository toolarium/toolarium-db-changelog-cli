/*
 * DiffChangelogCommand.java
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
 * Generate a changelog from the differences between two databases.
 *
 * @author patrick
 */
@Command(name = "diff-changelog", description = "Generate a changelog from the differences between two databases.")
public class DiffChangelogCommand implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(DiffChangelogCommand.class);

    @Mixin
    private DatabaseConnectionMixin db;

    @Option(names = {"--reference-url"}, description = "JDBC URL of the reference database", required = true)
    private String referenceUrl;

    @Option(names = {"--reference-username"}, description = "Reference database username")
    private String referenceUsername;

    @Option(names = {"--reference-password"}, description = "Reference database password", interactive = true, arity = "0..1")
    private String referencePassword;

    @Option(names = {"--output", "-o"}, description = "Output changelog file path", required = true)
    private String outputPath;


    /**
     * Generate a changelog from the differences between the target and reference databases.
     */
    @Override
    public void run() {
        // TODO: integrate with IChangelogRunner.diffChangelog()
        LOG.info("Generating diff changelog to " + outputPath + "...");
    }
}
