/*
 * GenerateChangelogCommandTest.java
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
 * Tests for the generate-changelog command with required --output option.
 *
 * @author patrick
 */
public class GenerateChangelogCommandTest {

    /**
     * Test generate-changelog with all required options succeeds.
     */
    @Test
    void testGenerateChangelogSucceeds() {
        int exitCode = execute("generate-changelog", "--url", "jdbc:h2:mem:test",
                               "-o", "output.db.yaml");
        assertEquals(0, exitCode);
    }


    /**
     * Test generate-changelog fails when --output is missing.
     */
    @Test
    void testGenerateChangelogFailsWithoutOutput() {
        int exitCode = execute("generate-changelog", "--url", "jdbc:h2:mem:test");
        assertEquals(2, exitCode);
    }


    /**
     * Test generate-changelog fails when --url is missing.
     */
    @Test
    void testGenerateChangelogFailsWithoutUrl() {
        int exitCode = execute("generate-changelog", "-o", "output.db.yaml");
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
