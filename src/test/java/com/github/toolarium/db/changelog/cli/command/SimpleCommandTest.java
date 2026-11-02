/*
 * SimpleCommandTest.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.db.changelog.cli.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.toolarium.db.changelog.cli.DbChangelogCli;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;


/**
 * Tests for commands that require --url and --changelog-file: update, status, validate, sync,
 * dry-run, future-rollback-sql, update-testing-rollback.
 *
 * @author patrick
 */
public class SimpleCommandTest {

    private static final String URL_FLAG = "--url";
    private static final String TEST_URL = "jdbc:h2:mem:test";
    private static final String FILE_FLAG = "-f";
    private static final String TEST_FILE = "changelog.db.yaml";
    private static final String[] COMMANDS = {
        "update", "status", "validate", "sync", "dry-run",
        "future-rollback-sql", "update-testing-rollback", "generate-sync-sql"
    };


    /**
     * Test that each command with all required options executes successfully.
     */
    @Test
    void testCommandsWithRequiredOptionsSucceed() {
        for (String command : COMMANDS) {
            int exitCode = execute(command, URL_FLAG, TEST_URL, FILE_FLAG, TEST_FILE);
            assertEquals(0, exitCode, command + " should succeed with required options");
        }
    }


    /**
     * Test that each command fails when --url is missing.
     */
    @Test
    void testCommandsFailWithoutUrl() {
        for (String command : COMMANDS) {
            int exitCode = execute(command, FILE_FLAG, TEST_FILE);
            assertEquals(2, exitCode, command + " should fail without --url");
        }
    }


    /**
     * Test that each command fails when --changelog-file is missing.
     */
    @Test
    void testCommandsFailWithoutChangelogFile() {
        for (String command : COMMANDS) {
            int exitCode = execute(command, URL_FLAG, TEST_URL);
            assertEquals(2, exitCode, command + " should fail without --changelog-file");
        }
    }


    /**
     * Test the update command with optional --count parameter.
     */
    @Test
    void testUpdateWithCount() {
        int exitCode = execute("update", URL_FLAG, TEST_URL, FILE_FLAG, TEST_FILE, "--count", "5");
        assertEquals(0, exitCode);
    }


    /**
     * Test generate-rollback-sql with required --count option.
     */
    @Test
    void testGenerateRollbackSqlSucceeds() {
        int exitCode = execute("generate-rollback-sql", URL_FLAG, TEST_URL,
                               FILE_FLAG, TEST_FILE, "--count", "3");
        assertEquals(0, exitCode);
    }


    /**
     * Test generate-rollback-sql fails when --count is missing.
     */
    @Test
    void testGenerateRollbackSqlFailsWithoutCount() {
        int exitCode = execute("generate-rollback-sql", URL_FLAG, TEST_URL, FILE_FLAG, TEST_FILE);
        assertEquals(2, exitCode);
    }


    /**
     * Test update command with runner options (leader-election, snapshot-policy, rollback-policy).
     */
    @Test
    void testUpdateWithRunnerOptions() {
        int exitCode = execute("update", URL_FLAG, TEST_URL, FILE_FLAG, TEST_FILE,
                               "--leader-election",
                               "--snapshot-policy", "ALL",
                               "--rollback-policy", "FAIL");
        assertEquals(0, exitCode);
    }


    /**
     * Test the update command with all optional parameters.
     */
    @Test
    void testUpdateWithAllOptions() {
        int exitCode = execute("update", URL_FLAG, TEST_URL,
                               "-u", "sa", "-p", "secret",
                               FILE_FLAG, TEST_FILE,
                               "--contexts", "prod,staging",
                               "--labels", "v1.0",
                               "--changelog-table", "MY_CHANGELOG",
                               "--driver", "org.h2.Driver",
                               "--default-schema", "PUBLIC",
                               "--count", "3");
        assertEquals(0, exitCode);
    }


    /**
     * Execute a CLI command with suppressed output.
     *
     * @param args the command line arguments
     * @return the exit code
     */
    private int execute(String... args) {
        CommandLine cmd = new CommandLine(new DbChangelogCli());
        cmd.setOut(new PrintWriter(new StringWriter()));
        cmd.setErr(new PrintWriter(new StringWriter()));
        return cmd.execute(args);
    }
}
