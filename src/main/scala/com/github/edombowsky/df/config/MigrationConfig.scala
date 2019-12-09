package com.github.edombowsky.df.config

import org.flywaydb.core.Flyway
import org.flywaydb.core.api.configuration.Configuration

import com.github.edombowsky.df.utils.DB

trait MigrationConfig extends DB {

  private val flyway: Flyway = Flyway.configure
    .dataSource(databaseUrl, databaseUser, databasePassword)
    .baselineOnMigrate(true)
    .load

  /**
   * Flyway Repair is important when one database Micration fails
   * Once you correct the Migration Issue, then run these line
   * to fix the issue.
   * @return
   */
  //flyway.repair()

  def migrate() = flyway.migrate()

  def reloadSchema() = {
    flyway.clean()
    flyway.migrate()
  }
}