/*
 * DbChangelogCli.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.db.changelog.cli;

import com.github.toolarium.db.changelog.cli.command.ClearChecksumsCommand;
import com.github.toolarium.db.changelog.cli.command.DiffChangelogCommand;
import com.github.toolarium.db.changelog.cli.command.DiffCommand;
import com.github.toolarium.db.changelog.cli.command.DropAllCommand;
import com.github.toolarium.db.changelog.cli.command.DryRunCommand;
import com.github.toolarium.db.changelog.cli.command.FutureRollbackSqlCommand;
import com.github.toolarium.db.changelog.cli.command.GenerateChangelogCommand;
import com.github.toolarium.db.changelog.cli.command.GenerateRollbackSqlCommand;
import com.github.toolarium.db.changelog.cli.command.GenerateSyncSqlCommand;
import com.github.toolarium.db.changelog.cli.command.HistoryCommand;
import com.github.toolarium.db.changelog.cli.command.MigrateCommand;
import com.github.toolarium.db.changelog.cli.command.RollbackCommand;
import com.github.toolarium.db.changelog.cli.command.StatusCommand;
import com.github.toolarium.db.changelog.cli.command.SyncCommand;
import com.github.toolarium.db.changelog.cli.command.TagCommand;
import com.github.toolarium.db.changelog.cli.command.UpdateCommand;
import com.github.toolarium.db.changelog.cli.command.UpdateTestingRollbackCommand;
import com.github.toolarium.db.changelog.cli.command.ValidateCommand;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;


/**
 * Main CLI entry point for toolarium-db-changelog.
 *
 * <p>Provides commands for database schema migration management:
 * update, rollback, status, validate, tag, sync, dry-run,
 * generate-changelog, diff, diff-changelog, drop-all, history,
 * future-rollback-sql, update-testing-rollback, migrate,
 * clear-checksums, generate-rollback-sql, and generate-sync-sql.</p>
 *
 * @author patrick
 */
@Command(name = "db-changelog",
         mixinStandardHelpOptions = true,
         versionProvider = DbChangelogCli.VersionProvider.class,
         description = "Database schema migration tool.",
         subcommands = {
             UpdateCommand.class,
             RollbackCommand.class,
             StatusCommand.class,
             ValidateCommand.class,
             TagCommand.class,
             SyncCommand.class,
             DryRunCommand.class,
             GenerateChangelogCommand.class,
             DiffCommand.class,
             DiffChangelogCommand.class,
             DropAllCommand.class,
             HistoryCommand.class,
             FutureRollbackSqlCommand.class,
             UpdateTestingRollbackCommand.class,
             MigrateCommand.class,
             ClearChecksumsCommand.class,
             GenerateRollbackSqlCommand.class,
             GenerateSyncSqlCommand.class
         })
public class DbChangelogCli implements Runnable {

    @CommandLine.Spec
    private CommandLine.Model.CommandSpec spec;

    @Option(names = {"--defaults-file"},
            description = "Path to a properties file with default option values (default: db-changelog.properties)",
            defaultValue = "db-changelog.properties")
    private String defaultsFile;


    /**
     * Print usage information when no subcommand is specified.
     */
    @Override
    public void run() {
        spec.commandLine().usage(spec.commandLine().getOut());
    }


    /**
     * Get the defaults file path.
     *
     * @return the defaults file path
     */
    public String getDefaultsFile() {
        return defaultsFile;
    }


    /**
     * The main entry point.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new CommandLine(new DbChangelogCli()).execute(args);
    }


    /**
     * Version provider that reads the version from the Version class.
     *
     * @author patrick
     */
    static class VersionProvider implements CommandLine.IVersionProvider {

        /**
         * Get the version strings for the --version option.
         *
         * @return the version strings
         * @throws Exception if an error occurs
         */
        @Override
        public String[] getVersion() throws Exception {
            return new String[]{Version.VERSION};
        }
    }
}
