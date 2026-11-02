/*
 * DiffCommandTest.java
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
 * Tests for the diff and diff-changelog commands with reference database options.
 *
 * @author patrick
 */
public class DiffCommandTest {

    private static final String URL_FLAG = "--url";
    private static final String TEST_URL = "jdbc:h2:mem:test";
    private static final String REF_URL_FLAG = "--reference-url";
    private static final String REF_URL = "jdbc:h2:mem:ref";


    /**
     * Test diff with all required options succeeds.
     */
    @Test
    void testDiffSucceeds() {
        int exitCode = execute("diff", URL_FLAG, TEST_URL, REF_URL_FLAG, REF_URL);
        assertEquals(0, exitCode);
    }


    /**
     * Test diff with optional reference credentials.
     */
    @Test
    void testDiffWithReferenceCredentials() {
        int exitCode = execute("diff", URL_FLAG, TEST_URL, REF_URL_FLAG, REF_URL,
                               "--reference-username", "sa", "--reference-password", "secret");
        assertEquals(0, exitCode);
    }


    /**
     * Test diff fails when --reference-url is missing.
     */
    @Test
    void testDiffFailsWithoutReferenceUrl() {
        int exitCode = execute("diff", URL_FLAG, TEST_URL);
        assertEquals(2, exitCode);
    }


    /**
     * Test diff fails when --url is missing.
     */
    @Test
    void testDiffFailsWithoutUrl() {
        int exitCode = execute("diff", REF_URL_FLAG, REF_URL);
        assertEquals(2, exitCode);
    }


    /**
     * Test diff-changelog with all required options succeeds.
     */
    @Test
    void testDiffChangelogSucceeds() {
        int exitCode = execute("diff-changelog", URL_FLAG, TEST_URL,
                               REF_URL_FLAG, REF_URL, "-o", "diff-output.db.yaml");
        assertEquals(0, exitCode);
    }


    /**
     * Test diff-changelog fails when --output is missing.
     */
    @Test
    void testDiffChangelogFailsWithoutOutput() {
        int exitCode = execute("diff-changelog", URL_FLAG, TEST_URL, REF_URL_FLAG, REF_URL);
        assertEquals(2, exitCode);
    }


    /**
     * Test diff-changelog fails when --reference-url is missing.
     */
    @Test
    void testDiffChangelogFailsWithoutReferenceUrl() {
        int exitCode = execute("diff-changelog", URL_FLAG, TEST_URL, "-o", "diff-output.db.yaml");
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
