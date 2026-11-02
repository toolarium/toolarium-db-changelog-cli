[![License](https://img.shields.io/github/license/toolarium/toolarium-db-changelog-cli)](https://github.com/toolarium/toolarium-db-changelog-cli/blob/master/LICENSE)
[![Maven Central](https://img.shields.io/maven-central/v/com.github.toolarium/toolarium-db-changelog-cli/0.0.1)](https://search.maven.org/artifact/com.github.toolarium/toolarium-db-changelog-cli/0.0.1/jar)
[![javadoc](https://javadoc.io/badge2/com.github.toolarium/toolarium-db-changelog-cli/javadoc.svg)](https://javadoc.io/doc/com.github.toolarium/toolarium-db-changelog-cli)

# toolarium-db-changelog-cli

A standalone command-line interface for [toolarium-db-changelog](https://github.com/toolarium/toolarium-db-changelog). It wraps the full database migration API into CLI commands for use in scripts, CI/CD pipelines, and interactive terminals.


## Features

- **Migration operations**: update, rollback (by count, tag, or date), status, validate, tag, sync
- **SQL generation**: dry-run, future-rollback-sql, generate-rollback-sql, generate-sync-sql
- **Schema tools**: generate-changelog, diff, diff-changelog, drop-all, history, clear-checksums
- **Rollback verification**: update-testing-rollback (apply, rollback, re-apply each changeset)
- **Format migration**: convert Liquibase and Flyway changelogs to DB-Changelog format via `migrate`
- **Runner configuration**: distributed locking via leader election, snapshot policy, rollback validation policy
- **Configuration file**: `db-changelog.properties` for default option values via `--defaults-file`
- **CI/CD exit codes**: distinct codes for success, validation failure, rollback failure, and connection failure
- **Secure credentials**: interactive password prompt via `--password` without value


## Installation

Download the fat JAR from the [releases page](https://github.com/toolarium/toolarium-db-changelog-cli/releases) or build from source:

```bash
source cb --setenv && cb
java -jar build/libs/toolarium-db-changelog-cli-*-all.jar --help
```


## Quick start

```bash
# Apply all pending changesets
db-changelog update --url jdbc:h2:./testdb -f db/changelog.db.yaml

# Apply with distributed locking and strict rollback validation
db-changelog update --url jdbc:h2:./testdb -f db/changelog.db.yaml \
    --leader-election --rollback-policy FAIL

# Show pending changesets
db-changelog status --url jdbc:h2:./testdb -f db/changelog.db.yaml

# Rollback last 3 changesets
db-changelog rollback --url jdbc:h2:./testdb -f db/changelog.db.yaml --count 3

# Rollback to a tag
db-changelog rollback --url jdbc:h2:./testdb -f db/changelog.db.yaml --tag v1.0

# Validate changelog for errors
db-changelog validate --url jdbc:h2:./testdb -f db/changelog.db.yaml

# Generate SQL without executing (dry-run)
db-changelog dry-run --url jdbc:h2:./testdb -f db/changelog.db.yaml

# Tag the current database state
db-changelog tag --url jdbc:h2:./testdb v1.0

# Generate rollback SQL for last 5 applied changesets
db-changelog generate-rollback-sql --url jdbc:h2:./testdb -f db/changelog.db.yaml --count 5

# Show applied changeset history
db-changelog history --url jdbc:h2:./testdb

# Convert a Flyway migration directory to DB-Changelog format
db-changelog migrate --source db/migration/ -o db/changelog/ --source-type FLYWAY
```


## Commands

| Command | Description |
|---|---|
| `update` | Execute pending changesets against the database |
| `rollback` | Rollback changesets by count, tag, or date |
| `status` | Show pending changesets that have not been applied |
| `validate` | Validate the changelog for errors |
| `tag` | Tag the current database state for future rollback |
| `sync` | Mark all pending changesets as executed without running them |
| `dry-run` | Output the SQL that would be executed without applying changes |
| `generate-changelog` | Generate a changelog from the current database schema |
| `diff` | Compare two databases and show differences |
| `diff-changelog` | Generate a changelog from the differences between two databases |
| `drop-all` | Drop all database objects (requires `--force`) |
| `history` | Show the history of applied changesets |
| `future-rollback-sql` | Generate rollback SQL for pending changesets |
| `generate-rollback-sql` | Generate rollback SQL for a specified number of applied changesets |
| `generate-sync-sql` | Generate the SQL that the sync command would execute |
| `update-testing-rollback` | Apply, rollback, and re-apply each pending changeset |
| `clear-checksums` | Clear all stored checksums from the tracking table |
| `migrate` | Convert external changelogs (Liquibase, Flyway) to DB-Changelog format |


## Options

**Database connection** (shared across most commands):

| Option | Description |
|---|---|
| `--url` | JDBC database URL (required) |
| `--username`, `-u` | Database username |
| `--password`, `-p` | Database password (prompted interactively if no value given) |
| `--driver` | JDBC driver class name |
| `--default-schema` | Default database schema |

**Changelog** (shared across commands that operate on changelogs):

| Option | Description |
|---|---|
| `--changelog-file`, `-f` | Path to the changelog file (required) |
| `--contexts` | Comma-separated list of contexts to execute |
| `--labels` | Label filter expression |
| `--changelog-table` | Name of the tracking table (default: `DB_CHANGELOG`) |

**Runner configuration** (available on update, rollback, update-testing-rollback):

| Option | Description |
|---|---|
| `--leader-election` | Enable distributed locking via [toolarium-leader-election](https://github.com/toolarium/toolarium-leader-election) |
| `--snapshot-policy` | Pre-execution snapshot policy: `AUTO`, `DDL`, `ALL`, or `NONE` (default: `AUTO`) |
| `--rollback-policy` | Rollback validation policy: `WARN`, `FAIL`, or `IGNORE` (default: `WARN`) |

**Global options**:

| Option | Description |
|---|---|
| `--defaults-file` | Path to a properties file with default option values (default: `db-changelog.properties`) |
| `--help` | Show usage help |
| `--version` | Print version information |


## Exit codes

| Code | Meaning |
|---|---|
| `0` | Successful execution |
| `1` | General execution error |
| `2` | Invalid command-line usage |
| `3` | Changelog validation failed (checksum mismatch, parse error) |
| `4` | Rollback failed or no rollback path available |
| `5` | Database connection failed |


## CI/CD pipeline example

```bash
# 1. Validate changelog
db-changelog validate --url "$DB_URL" -u "$DB_USER" -p "$DB_PASS" -f db/changelog.db.yaml

# 2. Dry-run: generate SQL for review
db-changelog dry-run --url "$DB_URL" -u "$DB_USER" -p "$DB_PASS" -f db/changelog.db.yaml

# 3. Generate rollback scripts for pending changesets
db-changelog future-rollback-sql --url "$DB_URL" -u "$DB_USER" -p "$DB_PASS" -f db/changelog.db.yaml

# 4. Apply pending changesets with leader election
db-changelog update --url "$DB_URL" -u "$DB_USER" -p "$DB_PASS" -f db/changelog.db.yaml \
    --leader-election

# 5. Tag the deployment
db-changelog tag --url "$DB_URL" -u "$DB_USER" -p "$DB_PASS" "v1.2.0"
```


## Built With

* [cb](https://github.com/toolarium/common-build) - The toolarium common build


## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/toolarium/toolarium-db-changelog-cli/tags).


## License

This project is licensed under the MPL-2.0 License, see the [LICENSE](LICENSE) file for details.

| Action | Allowed | Required | Not Allowed |
|---|---|---|---|
| Commercial use | Yes | | |
| Private use | Yes | | |
| Modify source | Yes | | |
| Distribute | Yes | | |
| Mix with proprietary code | Yes | | |
| Share modified MPL files | | Yes | |
| Keep license notices | | Yes | |
| Remove copyright notices | | | Yes |
| Hold authors liable | | | Yes |
| Use authors' trademarks | | | Yes |
