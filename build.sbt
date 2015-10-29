name := "projetao"

version := "1.0"

lazy val `projetao` = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq( javaJdbc , javaEbean , cache , javaWs, "org.postgresql" % "postgresql" % "9.3-1102-jdbc41")

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )