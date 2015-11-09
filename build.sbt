name := """escarbajo"""

version := "1.0-SNAPSHOT"

//lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.6"

version := "1.0-SNAPSHOT"

import com.typesafe.sbt.SbtAspectj.{ Aspectj, aspectjSettings, compiledClasses }
import com.typesafe.sbt.SbtAspectj.AspectjKeys.{ binaries, inputs, lintProperties }


// add resolver for deadbolt and easymail snapshots

resolvers += Resolver.sonatypeRepo("snapshots")

resolvers += Resolver.url("play-easymail (release)", url("http://joscha.github.io/play-easymail/repo/releases/"))(Resolver.ivyStylePatterns)

resolvers += Resolver.url("play-easymail (snapshot)", url("http://joscha.github.io/play-easymail/repo/snapshots/"))(Resolver.ivyStylePatterns)

resolvers += Resolver.url("play-authenticate (release)", url("http://joscha.github.io/play-authenticate/repo/releases/"))(Resolver.ivyStylePatterns)

resolvers += Resolver.url("play-authenticate (snapshot)", url("http://joscha.github.io/play-authenticate/repo/snapshots/"))(Resolver.ivyStylePatterns)

resolvers += "release repository" at "http://chanan.github.io/maven-repo/releases/"

resolvers += "snapshot repository" at "http://chanan.github.io/maven-repo/snapshots/"

resolvers += "Sonatype OSS Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots"

resolvers += Resolver.url("Typesafe Ivy", url("http://repo.typesafe.com/typesafe/ivy-snapshots"))(Resolver.ivyStylePatterns)

resolvers += "Typesafe" at "http://repo.typesafe.com/typesafe/releases/"


val appDependencies = Seq(  
  cache,
  javaWs,
  javaJdbc,
  "org.webjars" % "bootstrap" % "3.2.0",
  "org.easytesting" % "fest-assert" % "1.4" % "test",
  "be.objectify"  %% "deadbolt-java"     % "2.4.0",
  // Comment the next line for local development of the Play Authentication core:
  "com.feth"      %% "play-authenticate" % "0.7.0",
  "org.postgresql" % "postgresql" % "9.4-1204-jdbc42"
)

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
//routesGenerator := InjectedRoutesGenerator

EclipseKeys.projectFlavor := EclipseProjectFlavor.Java           // Java project. Don't expect Scala IDE
EclipseKeys.createSrc := EclipseCreateSrc.ValueSet(EclipseCreateSrc.ManagedClasses, EclipseCreateSrc.ManagedResources)  // Use .class files instead of generated .scala files for views and routes 
EclipseKeys.preTasks := Seq(compile in Compile)                  // Compile the project before generating Eclipse files, so that .class files for views and routes are present

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  "signalJ" %% "signalj" % "0.5.0",
  "org.webjars" %% "webjars-play" % "2.3.0-2",
  "org.webjars" % "bootstrap" % "3.3.1",
   "org.springframework" % "spring-context" % "3.2.2.RELEASE",
  "org.springframework" % "spring-aspects" % "3.2.2.RELEASE",
  "org.springframework.data" % "spring-data-jpa" % "1.3.2.RELEASE",
  "org.webjars" % "jquery" % "2.1.1"
)

WebKeys.directWebModules in Assets += "signalj"


lazy val root = project.in(file("."))
 .enablePlugins(PlayJava, PlayEbean)
  .settings(
    libraryDependencies ++= appDependencies
  )
  
aspectjSettings

inputs in Aspectj <+= compiledClasses

binaries in Aspectj <++= update map { report =>
    report.matching(
        moduleFilter(organization = "org.springframework", name = "spring-aspects")
    )
}

products in Compile <<= products in Aspectj

products in Runtime <<= products in Compile


fork in run := true

fork in run := true

fork in run := true

fork in run := true

fork in run := true