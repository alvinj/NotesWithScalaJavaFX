# README (Notes app)

I created this *Notes* application using Scala and JavaFX
to demonstrate how to write a relatively-small-but-useful
GUI application with [Scala](http://scala-lang.org/).

I created this project as a small demo app to go along with
my [“Hello, Scala” tutorial](http://hello-scala.com/). Because
it’s a demo app for that tutorial, I won’t be adding new features 
to it (but you’re welcome to fork it and add whatever features 
you’d like).


## How the app works

The basic idea of the application is that you can use it to store
miscellaneous notes throughout your day. Along with each note you
can add an optional URL (such as for YouTube or TED Talk videos,
or other web resources), and an optional list of comma-separated tags.

Notes can be small, or they can be large. For instance, if you want to
remember some Scala code, your note can be this:

```scala
import java.text.SimpleDateFormat
import java.util.Date

object DateUtils {

    val DATE_FORMAT = "EEE, MMM dd, yyyy h:mm a"

    def getDateAsString(d: Date): String = {
        val dateFormat = new SimpleDateFormat(DATE_FORMAT)
        dateFormat.format(d)
    }

    def convertStringToDate(s: String): Date = {
        val dateFormat = new SimpleDateFormat(DATE_FORMAT)
        dateFormat.parse(s)
    }
    
}
```

Then you might save that code as a note with some tags like these:

```scala
scala, date, string, simpledateformat, long, current date
```


## Video demo

Github markdown won’t let me include a video of the application in this
file, but here’s a [a link to the two-minute video on 
YouTube](https://youtu.be/3vI1l97ETxw).


## The “database”

Sometimes when I first create a little project, I don’t like to commit
to using a database like MySQL, H2, or others, so I start by using a little
[Flat File Database](https://github.com/alvinj/ScalaFlatFileDatabase) 
project I created. Please note that this really isn’t a database:
it just stores text in one or more text files, with each “field” separated by a
pipe character (|). It’s really just a convenience library for reading and
writing to a text file. As you can tell from that description, it only
works with plain text, so it is very limited in many ways.

I specifically use the Flat File Database in this project so you don’t
have to concern yourself with learning a Scala database library like
[ScalelikeJDBC](http://scalikejdbc.org/) 
or [Slick](http://slick.lightbend.com/). If you want to develop this
into a real project I recommend using a real database, or perhaps 
storing your notes in the cloud so you can access them from multiple
devices and platforms.

>IMPORTANT NOTE: The application creates its database file in your
home directory. For example, on my system it creates a file named
*/Users/al/Notes.data*. You can change that setup in the *Globals.scala*
class.


## The event handlers

There are a number of different ways to implement event handlers in Java and Scala.
I want to note that I chose the approach shown in this code because:

- I like to be able to look at something like the `AddNoteEventHandler` class and
  easily see, “Ah, to add a new note, this handler needs access to the TableView and
  Database.”
- (Conversely, if I had kept all of the “handler” code in the `MainController`,
  I might access the `tableView` and `db` class-level fields inside my handler
  methods, and these days I find code like that to be harder to read.)
- The handler code is what I’d call *obvious*. Because this project is intended as a
  teaching project, I didn’t want to get too fancy with the approach.


## The build process

I don’t have any Windows computers, but I can confirm that the build process
works on MacOS, and it should also work on Linux, though I haven’t tested it
there. In theory all you have to do to build the complete app is to run this
script in the root directory:

````
_build.sh
````

That script uses the `sbt assembly` command, followed by the `javapackager`
command. I call another script at the end of that script to update the
MacOS *Info.plist* file, and then definitely won’t be needed on Windows or
Linux systems (so remove that line if you’re building the app on those
systems).

### Running on Linux (and maybe Windows)

I just confirmed that if you build a Jar file with `sbt assembly` on Linux:

````
$ sbt assembly
````

you can then run that Jar file with this command:

````
$ scala target/scala-2.12/Notes-assembly-1.0.jar
````

Note that Scala 2.12.x is required.


## A few notes about the code

Here are a few notes about the code:

- All of the code is under the *com.alvinalexander.notes* package
- The application starts in the *NotesMain* class
- The *Notes.data* file is created in your home directory; you can configure that
  in *Globals.scala*
- I try to follow an MVC design, so a lot of the action runs through the
  *MainController*
- JavaFX seems to require that model classes conform to the JavaBean standard,
  so I created the main `Note` class as *Note.java* 
- I mostly included a CSS file so you can see how that’s done; experiment with 
  it as desired
- I added a couple of unit tests with ScalaTest so you can see how that’s done
- The “tag search” process currently only works for one tag; I noted this as a
  “to-do” item in *TODO.md*
- As a final reminder, the “database” isn’t really a database, it’s a single
  text file


## ScalaFX

Please note that I could have used [ScalaFX](http://www.scalafx.org/) for this
project, and I may eventually release a rewrite of it using ScalaFX. But because
this is intended to be a demo application for new Scala developers, I didn’t want
to introduce too many different variables at one time.


## License

This project is released under the terms of the GNU General Public License Version 3
(GNU GPLv3).


## More information

This project is created by [Alvin Alexander](https://alvinalexander.com). I created 
it for my [“Hello, Scala” tutorial](http://hello-scala.com/).




