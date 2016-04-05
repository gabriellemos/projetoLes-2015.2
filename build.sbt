name := """getcake"""

version := "1.0"

lazy val getcake = (project in file(".")).enablePlugins(PlayJava)

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaJpa,
  "org.apache.directory.api" % "api-all" % "1.0.0-M14",
  "postgresql" % "postgresql" % "9.1-901-1.jdbc4",
  "org.hibernate" % "hibernate-core" % "4.2.3.Final",
  "org.hibernate" % "hibernate-entitymanager" % "4.2.3.Final"
)

libraryDependencies += "org.pac4j" % "play-pac4j" % "2.1.0"
libraryDependencies += "org.pac4j" % "pac4j-oauth" % "1.8.7"

excludeDependencies += "com.google.guava" % "guava"

ivyScala := ivyScala.value map { _.copy(overrideScalaVersion = true) }