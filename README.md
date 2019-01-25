# Java.Groups_of_Fours
## Author: Jason A. Young

### Objective:
This application will create a Grid of random numbers between the numbers 0-9. Afterwards it will search the grid occurances of the same numbers in rows, columns, left diagonals & right diagonals, after which it will highlight the color depending on the way the numbers are grouped.

### Instructions:
You can run this program by either running the Java file or excuting the Jar file. Once the program is running, go to file and select either 'Read Data' or 'Random'. 'Read Data' will allow for you to import a text file containing a pre-made grid of numbers. 'Random' will ask you for a given number of Rows and Columns and then it will populate the 2D matrix with numbers between 0-9. Once the matrix is populated by either input method, the matrix can be processed by going to the 'File' menu and selecting 'Process'. This will group all the numbers that appear 4+ times whether it's vertically, horizontally or diagonally.


### Input:
The input can be from a text file or can be randomly generated within the program itself. Both methods require for the Number of rows and columns to be specfied. The text file has to be in a specific format. Here are three examples of appropriate text file formats for this program. 
	*<b>Note:</b> Spaces in the text file will not affect the program*
  
  <b>Example 1:</b>      
  R:3 C: 5\
  1 5 3 5 5\
  4 3 2 3 2\
  6 4 2 4 5	
  
  <b>Example 2:</b>      
  R:4 C:12\
  362452523544\
  434356346346\
  436363463634\
  346346346346
                            
  <b>Example 3:</b>
  R:11C:3\
  7 8 9\
  6 6 8\
  778\
  684\
  3 14\
  322\
  32 1\
  3 5 5\
	 482\
	2  44\
	7 7 7
  
  All three of theses example will work as text file imports for this program.

#### Color groups:
The groups of numbers are put into different colors depending on whether the group of four numbers is:\
	Horizontal (Red)\
	Vertical (Cyan)\
	Diagonal to the right(Yellow)\
	Diagonal to the left(Green)
   
Located in the repo is an example text file called 'NumberGrid.txt'. I also included a screen shot (Figure 1) of what the process looks like for that text file.
  
Figure 1: 
![alt text](https://github.com/youngj25/Java.Groups_of_Fours/blob/master/GroupsOfFours.png "Logo Title Text 1")
