# Report_Generation


 A program is created for performing a specic task e.g. automatic report generation. Few instances of a program (e.g.5) are running in parallel to perform the report creating task by reading two databases with the help of two connections where data for the needed reports is present. However there are only limited number of connections are allowed to the database (e.g.5).
Each program can performs certain computations needed for the report and when data is needed it picks up two db connections and reads data needed to generate the report. Once data is read connections are released and continues with in memory computations.

Write a   program  to  simulate  the  above  behaviour  of  the  report  generation  program  where
•  You  must  prevent  where  two  instances  of  the  program  takes  same  DB  connection  at  the  same
time.
•  There  should  not  be  any  dead  lock  i.e.  where  Program  is  able  to  get  one  connection  and
waiting  for  another  connection
•  Programs  should  not  be  in  waiting  state  for  too  long
Make  sure  that  the  solution  works  for  any  number  of  program  instances  not  just  5   as  mentioned
above
