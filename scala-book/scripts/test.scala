val breakException = new RuntimeException("break exception")

def breakable(op: => Unit) {
  try {
    op
  } catch {
    case _: Throwable =>
  }
}

def break = throw breakException

def install() = {
  val env = System.getenv("SCALA_HOME")
  if (env == null) break
  println("found scala home lets do the real work")
}

breakable(install())

val files = new java.io.File("").listFiles
for (file <- files) {
  val filename = file.getName
  if (filename.endsWith(".scala")) println(file)
}

for (file <- files; filename=file.getName; if filename.endsWith(".scala")) println()

val f = for (file <- files; filename=file.getName; if filename.endsWith(".scala")) yield file.getName

f.foreach(println(_))