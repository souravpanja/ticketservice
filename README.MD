This is a command prompt based java project to implement a simple ticket service system which provides the searching, temporary hold and final reservation of seats. 
Pre-requisites
-------------------------
1.	jre1.8.0_45 has been used to run this jar file
2.	Windows x86 64 based machine has been used
How to execute
---------------------------
Download the TicketService.jar file from the folder /target. Then execute the below command in command prompt
java –jar TicketService.jar
Running the example
------------------------------
In Command prompt
> java –jar TicketService.jar

*************Below is the outline for the three Services and its corresponding number****
**********
********************1 get the number of available seats*************************
*****
********************2 to find available and hold seats**************************
*****
********************3 reserve the hold seat for the customer********************
*****
********************************************************************************
*****

99 for EXIT
Enter your service no(1 or 2 or 3)
1
         If you don't want to restrict the serch operation please press enter. Below is the optional numeric input parameter for this operation,
         venuLevel (optional): a numeric venue level identifier to limit the search
Enter venuLevel(optional - press enter for all):
*************************Count Free Seats START************************
No of seats available:6250
*************************Count Free Seats END************************

99 for EXIT
Enter your service no(1 or 2 or 3)
2
         If you don't want to restrict the hold operation please don't enter the value for below parameters minLevel and maxLevel.
Below is the optional numeric input parameter for this operation,
         numSeats : the number of seats to find and hold
         minLevel (Optional): the minimum venue level
         maxLevel (Optional): the maximum venue level
         customerEmail : the email address of the customer to which the seat hold is assigned
Enter numSeats*:2
Enter minLevel (Optional):
Enter maxLevel (Optional):
Enter customerEmail: bb@yahoo.com
*************************SeatHolD Details START************************
****Please RESERVE below seats earliest unless it will be expired*****
Seat Hold Id:1113
Customer Email:bb@yahoo.com

SeatId:LEVEL_1ROW_3SEAT_1        Status:HOLD     LevelId:1
SeatId:LEVEL_1ROW_3SEAT_2        Status:HOLD     LevelId:1
*************************SeatHolD Details END************************

99 for EXIT
Enter your service no(1 or 2 or 3)
3
         Below are the input parameters for this operation,
         seatHoldId : the seat hold identifier
         customerEmail : the email address of the customer to which the seat hold is assigned
Enter seatHoldId*: 1113
Enter customerEmail*: bb@yahoo.com
*************************Reserve Seats START************************
Reservation Confirmation Code:0acb8583075c43f3821fcffd4c9370eb
*************************Reserve Seats END************************

99 for EXIT
Enter your service no(1 or 2 or 3)
99
>
************* Example End here**************
How to Build
-----------------------------
Hence this is maven based project source code can be downloaded to build the project in other machine. 
Only single jar has been used as a maven dependency for Junit test.  The Jar for this project is junit-3.8.1.jar

Pre-requisites
JDK1.8
Eclipse Mars.1 Release (4.5.1)
