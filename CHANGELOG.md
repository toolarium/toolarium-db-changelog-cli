# toolarium-db-changelog-cli

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [ 0.8.1 ] - 2026-11-02

## [ 0.8.0 ] - 2026-11-02
### Added
- CLI framework based on picocli 4.7.6 with fat JAR packaging via shadow plugin.
- Migration commands: update, rollback (by count, tag, or date), status, validate, tag, sync.
- SQL generation commands: dry-run, future-rollback-sql, generate-rollback-sql, generate-sync-sql.
- Schema commands: generate-changelog, diff, diff-changelog, drop-all, history, clear-checksums.
- Rollback verification via update-testing-rollback (apply, rollback, re-apply each changeset).
- Format migration via migrate (convert Liquibase and Flyway changelogs to DB-Changelog format).
- Shared option mixins for database connection, changelog parameters, and runner configuration.
- Runner configuration: --leader-election, --snapshot-policy (AUTO/DDL/ALL/NONE), --rollback-policy (WARN/FAIL/IGNORE).
- Configuration file support via --defaults-file (default: db-changelog.properties).
- Exit codes for CI/CD integration: 0 success, 1 error, 2 usage, 3 validation, 4 rollback, 5 connection.
- Interactive password prompt via --password without value.
- MPL-2.0 license matching toolarium-db-changelog.
