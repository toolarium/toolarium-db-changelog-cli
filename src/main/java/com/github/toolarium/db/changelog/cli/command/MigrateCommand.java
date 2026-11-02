/*
 * MigrateCommand.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.db.changelog.cli.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;


/**
 * Convert external changelog formats (Liquibase, Flyway) to DB-Changelog format.
 *
 * @author patrick
 */
@Command(name = "migrate", description = "Convert external changelogs (Liquibase, Flyway) to DB-Changelog format.")
public class MigrateCommand implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(MigrateCommand.class);

    @Option(names = {"--source"}, description = "Source changelog file or directory", required = true)
    private String source;

    @Option(names = {"--output-dir", "-o"}, description = "Output directory for migrated files", required = true)
    private String outputDirectory;

    @Option(names = {"--output-format"}, description = "Output format: YAML, XML, or JSON (default: YAML)",
            defaultValue = "YAML")
    private String outputFormat;

    @Option(names = {"--source-type"}, description = "Source type: LIQUIBASE or FLYWAY (auto-detected if not specified)")
    private String sourceType;

    @Option(names = {"--flatten"}, description = "Merge all changesets into a single file")
    private boolean flatten;


    /**
     * Convert external changelogs to DB-Changelog format.
     */
    @Override
    public void run() {
        // TODO: integrate with IChangelogMigrator
        LOG.info("Migrating changelog from " + source + " to " + outputDirectory + "...");
    }
}
