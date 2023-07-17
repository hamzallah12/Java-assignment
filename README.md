FortuneCookie.java

Create a FortuneCookie class in the same folder as Server and Client with the following data members:
fortune: String
lucky1: int
lucky2: int
lucky3: int
lucky4: int
lucky5: int
word: String

(The 5 separate integers can be replaced with an array or ArrayList if you prefer.)

Include an “all-args” constructor and a toString() with an easy-to-read format, 
such as:

	Fortune: Fortune goes here.
	Lucky Numbers: 1  2  3  4  5
	Word of the Day: word

Server.java

Modify the Server file by doing the following:

Add an ArrayList/LinkedList that stores 5 FortuneCookie objects. Add these 5 objects manually. You will need to come up with 5 fortunes, 5 sets of 5 integers, and 5 words to hard-code into each FortuneCookie in the ArrayList. You can approach this however you wish. 
Accept an integer from the client and use that as the index to retrieve a FortuneCookie from your list. You will send back to the user the actual FortuneCookie object, not just the toString().
Update the server text area to show what the user’s integer was and what their resulting FortuneCookie was.
Note: DataInputStream and DataOutputStream may need to be replaced by streams that can handle objects.

Client.java

Modify the Client file by doing the following:

Update directions in TextField (ie. “Enter 1-5 for fortune”) 
Update code so that a FortuneCookie object can be accepted from the server. 
Input/output streams may need modification here as well.

Input Validation

Your ArrayList must have exactly 5 entries in it (index 0 - 4). 
You must require that the user enter 1-5. (Make the adjustment in your code so that this works.)
You must also reject inputs that are less than 1 or greater than 5.
If you try to get something from an ArrayList in an index that has nothing, the program will freeze/crash.
This portion alone is worth 10 points.


Implementation: 

There’s a case to be made for both client-side and server-side input validation.  

I do not care where you put this checking mechanism, but make sure 
integers other than 1-5 do not freeze the program.  

Your code must deny integers outside of the acceptable range. The way you approach this and the location of the code is up to you.  (It is preferable to handle this using simple decision statements rather than an ArrayIndexOutofBoundsException catch block. The user’s input should never make it to the ArrayList without being checked first.)  
Submission
When checking this lab, I will run your program and also look through your code to make sure things are set up as requested. I will enter a mixture of good and bad integers to make sure the program doesn’t crash.
