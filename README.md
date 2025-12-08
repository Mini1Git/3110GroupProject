# 3110GroupProject
Group Project for 3110 course
--------------------------------------------------
General Program - How to run
--------------------------------------------------
1. Run Main Class
2. input your full/relative path to file 1
3. input your full/relative path to file 2
4. See results in results.xml
--------------------------------------------------
Testing - How to run
--------------------------------------------------
1. Go to Main class, uncomment the findFiles() call and TestingCases.writeCSVData() call.
2. Comment out realTool() if you want to run automated tests.
3. Run Main. 
4. All of the testing data should then output to a CSV file named TESTING_RESULTS.csv.

--------------------------------------------------
Bonus question - Bug Finder
--------------------------------------------------
- Run BonusMain
- Input your Full path to the git repository
- Input the filename/relative path of the file you want to analyze
- The program will check that file's latest commit
- if commit is a bug fix (contains words "bug", "fix", "repair"):
    - Program will find the buggy lines and track when they were introduced
