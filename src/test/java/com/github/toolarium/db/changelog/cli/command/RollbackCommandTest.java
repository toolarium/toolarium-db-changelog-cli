/*
 * RollbackCommandTest.java
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
 * Tests for the rollback command, specifically the ArgGroup validation
 * requiring exactly one of --count, --tag, or --date.
 *
 * @author patrick
 */
public class RollbackCommandTest {

    private static final String ROLLBACK = "rollback";
    private static final String URL_FLAG = "--url";
    private static final String TEST_URL = "jdbc:h2:mem:test";
    private static final String FILE_FLAG = "-f";
    private static final String TEST_FILE = "changelog.db.yaml";


    /**
     * Test rollback with --count succeeds.
     */
    @Test
    void testRollbackByCount() {
        int exitCode = execute(ROLLBACK, URL_FLAG, TEST_URL, FILE_FLAG, TEST_FILE, "--count", "3");
        assertEquals(0, exitCode);
    }


    /**
     * Test rollback with --tag succeeds.
     */
    @Test
    void testRollbackByTag() {
        int exitCode = execute(ROLLBACK, URL_FLAG, TEST_URL, FILE_FLAG, TEST_FILE, "--tag", "v1.0");
        assertEquals(0, exitCode);
    }


    /**
     * Test rollback with --date succeeds.
     */
    @Test
    void testRollbackByDate() {
        int exitCode = execute(ROLLBACK, URL_FLAG, TEST_URL, FILE_FLAG, TEST_FILE,
                               "--date", "2026-01-01T00:00:00Z");
        assertEquals(0, exitCode);
    }


    /**
     * Test rollback fails when no target is specified (ArgGroup multiplicity = 1).
     */
    @Test
    void testRollbackFailsWithoutTarget() {
        int exitCode = execute(ROLLBACK, URL_FLAG, TEST_URL, FILE_FLAG, TEST_FILE);
        assertEquals(2, exitCode);
    }


    /**
     * Test rollback fails when --url is missing.
     */
    @Test
    void testRollbackFailsWithoutUrl() {
        int exitCode = execute(ROLLBACK, FILE_FLAG, TEST_FILE, "--count", "1");
        assertEquals(2, exitCode);
    }


    /**
     * Test rollback fails when --changelog-file is missing.
     */
    @Test
    void testRollbackFailsWithoutChangelogFile() {
        int exitCode = execute(ROLLBACK, URL_FLAG, TEST_URL, "--count", "1");
        assertEquals(2, exitCode);
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
