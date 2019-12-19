package com.github.edombowsky.df

object GenerateWorkOrder {
  def main(args: Array[String]): Unit = {

    slick.codegen.SourceCodeGenerator.main(
      Array(
        //"slick.jdbc.PostgresProfile",
        "MyPostgresDriver",
        "org.postgresql.Driver",
        "jdbc:postgresql://localhost:25432/caeadom?stringtype=unspecified&currentSchema=datafabric_workorder",
        "src/main/scala",
        "com.github.edombowsky.models",
        "caeadom",
        "")
    )
  }
}
