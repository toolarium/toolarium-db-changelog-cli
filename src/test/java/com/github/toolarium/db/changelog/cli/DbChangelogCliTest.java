/*
 * DbChangelogCliTest.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.db.changelog.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.PrintWriter;
import java.io.StringWriter;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;


/**
 * Tests for the CLI entry point.
 *
 * @author patrick
 */
public class DbChangelogCliTest {

    /**
     * Test that no arguments prints usage and returns zero.
     */
    @Test
    void testNoArgsPrintsUsage() {
        StringWriter out = new StringWriter();
        CommandLine cmd = new CommandLine(new DbChangelogCli());
        cmd.setOut(new PrintWriter(out));
        int exitCode = cmd.execute();
        assertEquals(0, exitCode);
        assertTrue(out.toString().contains("db-changelog"));
        assertTrue(out.toString().contains("Database schema migration tool."));
    }


    /**
     * Test that --help returns zero and contains command list.
     */
    @Test
    void testHelpContainsCommands() {
        StringWriter out = new StringWriter();
        CommandLine cmd = new CommandLine(new DbChangelogCli());
        cmd.setOut(new PrintWriter(out));
        int exitCode = cmd.execute("--help");
        assertEquals(0, exitCode);
        String output = out.toString();
        assertTrue(output.contains("update"));
        assertTrue(output.contains("rollback"));
        assertTrue(output.contains("status"));
        assertTrue(output.contains("validate"));
        assertTrue(output.contains("tag"));
        assertTrue(output.contains("sync"));
        assertTrue(output.contains("dry-run"));
        assertTrue(output.contains("generate-changelog"));
        assertTrue(output.contains("diff-changelog"));
        assertTrue(output.contains("drop-all"));
        assertTrue(output.contains("history"));
        assertTrue(output.contains("future-rollback-sql"));
        assertTrue(output.contains("update-testing-rollback"));
        assertTrue(output.contains("migrate"));
        assertTrue(output.contains("clear-checksums"));
        assertTrue(output.contains("generate-rollback-sql"));
        assertTrue(output.contains("generate-sync-sql"));
    }


    /**
     * Test that --version returns zero and contains the version string.
     */
    @Test
    void testVersionOutputContainsVersionString() {
        StringWriter out = new StringWriter();
        CommandLine cmd = new CommandLine(new DbChangelogCli());
        cmd.setOut(new PrintWriter(out));
        int exitCode = cmd.execute("--version");
        assertEquals(0, exitCode);
        assertTrue(out.toString().contains(Version.VERSION));
    }


    /**
     * Test that the VersionProvider returns the correct version.
     *
     * @throws Exception if version retrieval fails
     */
    @Test
    void testVersionProviderReturnsCorrectVersion() throws Exception {
        DbChangelogCli.VersionProvider provider = new DbChangelogCli.VersionProvider();
        String[] versions = provider.getVersion();
        assertNotNull(versions);
        assertEquals(1, versions.length);
        assertEquals(Version.VERSION, versions[0]);
    }


    /**
     * Test that exactly 18 subcommands are registered.
     */
    @Test
    void testSubcommandsRegistered() {
        CommandLine cmd = new CommandLine(new DbChangelogCli());
        assertEquals(18, cmd.getSubcommands().size());
    }


    /**
     * Test that all expected subcommand names are present.
     */
    @Test
    void testSubcommandNames() {
        CommandLine cmd = new CommandLine(new DbChangelogCli());
        String[] expected = {
            "update", "rollback", "status", "validate", "tag", "sync", "dry-run",
            "generate-changelog", "diff", "diff-changelog", "drop-all", "history",
            "future-rollback-sql", "update-testing-rollback", "migrate",
            "clear-checksums", "generate-rollback-sql", "generate-sync-sql"
        };
        for (String name : expected) {
            assertNotNull(cmd.getSubcommands().get(name), "Missing subcommand: " + name);
        }
    }


    /**
     * Test that an unknown subcommand returns non-zero exit code.
     */
    @Test
    void testUnknownSubcommandFails() {
        StringWriter err = new StringWriter();
        CommandLine cmd = new CommandLine(new DbChangelogCli());
        cmd.setErr(new PrintWriter(err));
        int exitCode = cmd.execute("nonexistent");
        assertEquals(2, exitCode);
    }
}
