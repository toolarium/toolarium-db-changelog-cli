/*
 * DropAllCommandTest.java
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
 * Tests for the drop-all command with required --force flag.
 *
 * @author patrick
 */
public class DropAllCommandTest {

    /**
     * Test drop-all with --force succeeds.
     */
    @Test
    void testDropAllWithForceSucceeds() {
        int exitCode = execute("drop-all", "--url", "jdbc:h2:mem:test", "--force");
        assertEquals(0, exitCode);
    }


    /**
     * Test drop-all fails when --force is missing.
     */
    @Test
    void testDropAllFailsWithoutForce() {
        int exitCode = execute("drop-all", "--url", "jdbc:h2:mem:test");
        assertEquals(2, exitCode);
    }


    /**
     * Test drop-all fails when --url is missing.
     */
    @Test
    void testDropAllFailsWithoutUrl() {
        int exitCode = execute("drop-all", "--force");
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
