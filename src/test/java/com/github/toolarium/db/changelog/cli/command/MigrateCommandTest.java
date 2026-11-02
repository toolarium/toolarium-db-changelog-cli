/*
 * MigrateCommandTest.java
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
 * Tests for the migrate command with its unique options.
 *
 * @author patrick
 */
public class MigrateCommandTest {

    /**
     * Test migrate with required options succeeds.
     */
    @Test
    void testMigrateSucceeds() {
        int exitCode = execute("migrate", "--source", "legacy/changelog.xml",
                               "-o", "migrated/");
        assertEquals(0, exitCode);
    }


    /**
     * Test migrate with all options succeeds.
     */
    @Test
    void testMigrateWithAllOptions() {
        int exitCode = execute("migrate", "--source", "legacy/changelog.xml",
                               "-o", "migrated/",
                               "--output-format", "JSON",
                               "--source-type", "FLYWAY",
                               "--flatten");
        assertEquals(0, exitCode);
    }


    /**
     * Test migrate fails when --source is missing.
     */
    @Test
    void testMigrateFailsWithoutSource() {
        int exitCode = execute("migrate", "-o", "migrated/");
        assertEquals(2, exitCode);
    }


    /**
     * Test migrate fails when --output-dir is missing.
     */
    @Test
    void testMigrateFailsWithoutOutputDir() {
        int exitCode = execute("migrate", "--source", "legacy/changelog.xml");
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
