To-Do List
==========


Current items
-------------
- The “tag search” process currently only works for one tag; if you try to
  use two tags it will fail
- Test the app as-is


Low priority items
------------------

Because this is intended to be a little demo Scala app, I made the following
items a low priority:

- Let users click the Url field in the table
- remember size and location of main window (preferences)
- pros and cons about the design of the event handlers; should they 
  know about TableView and Database?
- Database::save can/should save the notes in the order they’re 
  shown in the TableView


Generally seems to be working
-----------------------------
- add ScalaTest unit tests for the `DateUtils` functions
- delete multiple notes at one time
- change the one date field to two, to have an "updated date" and a "created date"
- add "Are you sure?" confirmation to Delete Note process
- save Note::date as a long in the database, then convert it back
  to a Date and String during the read process
    - `date.getTime` will give you the `Long`
- Edit Note process
- handle multi-row notes
- push “multiline” code down into Flat File Database project
- Tag Search
- Full-Text Search

