/*
 * TagCommandTest.java
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
 * Tests for the tag command with positional parameter.
 *
 * @author patrick
 */
public class TagCommandTest {

    /**
     * Test tag with all required arguments succeeds.
     */
    @Test
    void testTagSucceeds() {
        int exitCode = execute("tag", "--url", "jdbc:h2:mem:test", "v1.0");
        assertEquals(0, exitCode);
    }


    /**
     * Test tag fails when positional tag name is missing.
     */
    @Test
    void testTagFailsWithoutTagName() {
        int exitCode = execute("tag", "--url", "jdbc:h2:mem:test");
        assertEquals(2, exitCode);
    }


    /**
     * Test tag fails when --url is missing.
     */
    @Test
    void testTagFailsWithoutUrl() {
        int exitCode = execute("tag", "v1.0");
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
