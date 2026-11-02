/*
 * DiffCommand.java
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
 * Compare two databases and show differences.
 *
 * @author patrick
 */
@Command(name = "diff", description = "Compare two databases and show differences.")
public class DiffCommand implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(DiffCommand.class);

    @Mixin
    private DatabaseConnectionMixin db;

    @Option(names = {"--reference-url"}, description = "JDBC URL of the reference database", required = true)
    private String referenceUrl;

    @Option(names = {"--reference-username"}, description = "Reference database username")
    private String referenceUsername;

    @Option(names = {"--reference-password"}, description = "Reference database password", interactive = true, arity = "0..1")
    private String referencePassword;


    /**
     * Compare the target and reference databases and display differences.
     */
    @Override
    public void run() {
        // TODO: integrate with IChangelogRunner.diff()
        LOG.info("Comparing databases...");
    }
}
