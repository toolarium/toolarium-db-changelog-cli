/*
 * RunnerMixin.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.db.changelog.cli.mixin;

import picocli.CommandLine.Option;


/**
 * Shared runner configuration options for commands that execute changesets.
 * Maps to ChangelogRunnerBuilder configuration options.
 *
 * @author patrick
 */
public class RunnerMixin {

    @Option(names = {"--leader-election"}, description = "Enable distributed locking via leader election")
    private boolean leaderElection;

    @Option(names = {"--snapshot-policy"},
            description = "Snapshot policy: AUTO, DDL, ALL, or NONE (default: AUTO)",
            defaultValue = "AUTO")
    private String snapshotPolicy;

    @Option(names = {"--rollback-policy"},
            description = "Rollback validation policy: WARN, FAIL, or IGNORE (default: WARN)",
            defaultValue = "WARN")
    private String rollbackPolicy;


    /**
     * Check whether leader election is enabled.
     *
     * @return true if leader election is enabled
     */
    public boolean isLeaderElection() {
        return leaderElection;
    }


    /**
     * Get the snapshot policy.
     *
     * @return the snapshot policy name
     */
    public String getSnapshotPolicy() {
        return snapshotPolicy;
    }


    /**
     * Get the rollback validation policy.
     *
     * @return the rollback policy name
     */
    public String getRollbackPolicy() {
        return rollbackPolicy;
    }
}
