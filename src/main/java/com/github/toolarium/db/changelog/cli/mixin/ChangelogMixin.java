/*
 * ChangelogMixin.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.db.changelog.cli.mixin;

import picocli.CommandLine.Option;


/**
 * Shared changelog options for commands that operate on changelogs.
 *
 * @author patrick
 */
public class ChangelogMixin {

    @Option(names = {"--changelog-file", "-f"}, description = "Path to the changelog file", required = true)
    private String changelogFile;

    @Option(names = {"--contexts"}, description = "Comma-separated list of contexts to execute")
    private String contexts;

    @Option(names = {"--labels"}, description = "Label filter expression")
    private String labels;

    @Option(names = {"--changelog-table"}, description = "Name of the tracking table (default: DB_CHANGELOG)",
            defaultValue = "DB_CHANGELOG")
    private String changelogTable;


    /**
     * Get the changelog file path.
     *
     * @return the changelog file path
     */
    public String getChangelogFile() {
        return changelogFile;
    }


    /**
     * Get the contexts.
     *
     * @return the contexts
     */
    public String getContexts() {
        return contexts;
    }


    /**
     * Get the labels.
     *
     * @return the labels
     */
    public String getLabels() {
        return labels;
    }


    /**
     * Get the changelog table name.
     *
     * @return the changelog table name
     */
    public String getChangelogTable() {
        return changelogTable;
    }
}
