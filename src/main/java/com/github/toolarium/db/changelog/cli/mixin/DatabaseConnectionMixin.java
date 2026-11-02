/*
 * DatabaseConnectionMixin.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.db.changelog.cli.mixin;

import picocli.CommandLine.Option;


/**
 * Shared database connection options for all commands that need a database connection.
 *
 * @author patrick
 */
public class DatabaseConnectionMixin {

    @Option(names = {"--url"}, description = "JDBC database URL", required = true)
    private String url;

    @Option(names = {"--username", "-u"}, description = "Database username")
    private String username;

    @Option(names = {"--password", "-p"}, description = "Database password", interactive = true, arity = "0..1")
    private String password;

    @Option(names = {"--driver"}, description = "JDBC driver class name")
    private String driver;

    @Option(names = {"--default-schema"}, description = "Default database schema")
    private String defaultSchema;


    /**
     * Get the JDBC URL.
     *
     * @return the URL
     */
    public String getUrl() {
        return url;
    }


    /**
     * Get the username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }


    /**
     * Get the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }


    /**
     * Get the driver class name.
     *
     * @return the driver class name
     */
    public String getDriver() {
        return driver;
    }


    /**
     * Get the default schema.
     *
     * @return the default schema
     */
    public String getDefaultSchema() {
        return defaultSchema;
    }
}
