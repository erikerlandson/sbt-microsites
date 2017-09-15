import microsites._

enablePlugins(MicrositesPlugin)

scalaVersion := sys.props("scala.version")

micrositeExtraMdFiles := Map(
  file("README.md") -> ExtraMdFileConfig(
    "readme.md",
    "home"
  ),
  file("CONSEQUAT.md") -> ExtraMdFileConfig(
    "consequat.md",
    "page",
    Map("title" -> "Consequata", "section" -> "consequata", "position" -> "1")
  )
)

def getLines(fileName: String) =
  IO.readLines(file(fileName))

lazy val check = TaskKey[Unit]("check")

check := {
  val checkPathFile: File = file((crossTarget in Compile).value.getAbsolutePath + "/resource_managed/main/jekyll")

  if(!checkPathFile.exists())
    sys.error("Jekyll directory doesn't exist.")
}

lazy val checkReadme = TaskKey[Unit]("checkReadme")

checkReadme := {
  val lines =
    getLines((crossTarget in Compile).value.getAbsolutePath + "/resource_managed/main/jekyll/readme.md")

  if (!lines(1).contains("home"))
    sys.error("Readme file has not layout home")
}

lazy val checkConsequat = TaskKey[Unit]("checkConsequat")

checkConsequat := {
  val lines =
    getLines((crossTarget in Compile).value.getAbsolutePath + "/resource_managed/main/jekyll/consequat.md")

  if (!lines(1).contains("page"))
    sys.error("Consequat file has not layout page")
}
