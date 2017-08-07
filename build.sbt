name := "qacinema"
 
version := "1.0" 
      
lazy val `qacinema` = (project in file(".")).enablePlugins(PlayScala)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
      
scalaVersion := "2.11.11"

libraryDependencies += "org.mongodb.scala" %% "mongo-scala-driver" % "2.1.0"
libraryDependencies +=  "org.scalaj" %% "scalaj-http" % "2.3.0"
libraryDependencies ++= Seq( jdbc , cache , ws , specs2 % Test )

libraryDependencies += "com.typesafe.play" %% "play-mailer" % "6.0.0"
libraryDependencies += "com.typesafe.play" %% "play-mailer-guice" % "6.0.0"

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )

