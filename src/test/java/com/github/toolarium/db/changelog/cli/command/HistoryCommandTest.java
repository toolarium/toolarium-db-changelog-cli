/*
 * HistoryCommandTest.java
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
 * Tests for the history and clear-checksums commands (require --url only, no changelog file).
 *
 * @author patrick
 */
public class HistoryCommandTest {

    private static final String URL_FLAG = "--url";
    private static final String TEST_URL = "jdbc:h2:mem:test";


    /**
     * Test history with --url succeeds.
     */
    @Test
    void testHistorySucceeds() {
        int exitCode = execute("history", URL_FLAG, TEST_URL);
        assertEquals(0, exitCode);
    }


    /**
     * Test history fails when --url is missing.
     */
    @Test
    void testHistoryFailsWithoutUrl() {
        int exitCode = execute("history");
        assertEquals(2, exitCode);
    }


    /**
     * Test clear-checksums with --url succeeds.
     */
    @Test
    void testClearChecksumsSucceeds() {
        int exitCode = execute("clear-checksums", URL_FLAG, TEST_URL);
        assertEquals(0, exitCode);
    }


    /**
     * Test clear-checksums fails when --url is missing.
     */
    @Test
    void testClearChecksumsFailsWithoutUrl() {
        int exitCode = execute("clear-checksums");
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
