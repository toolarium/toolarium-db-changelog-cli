/*
 * CliExitCode.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.db.changelog.cli;


/**
 * Exit codes for CI/CD integration.
 *
 * <p>Provides meaningful exit codes beyond the picocli defaults (0 = success, 1 = execution error,
 * 2 = usage error) to allow pipelines to distinguish between different failure types.</p>
 *
 * @author patrick
 */
public final class CliExitCode {

    /** Successful execution. */
    public static final int SUCCESS = 0;

    /** General execution error. */
    public static final int ERROR = 1;

    /** Invalid command-line usage (picocli default). */
    public static final int USAGE_ERROR = 2;

    /** Changelog validation failed (checksum mismatch, parse error). */
    public static final int VALIDATION_FAILED = 3;

    /** Rollback failed or no rollback path available. */
    public static final int ROLLBACK_FAILED = 4;

    /** Database connection failed. */
    public static final int CONNECTION_FAILED = 5;


    /**
     * Private constructor to prevent instantiation.
     */
    private CliExitCode() {
        // utility class
    }
}
