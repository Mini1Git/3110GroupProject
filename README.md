# 3110GroupProject
Group Project for 3110 course
Group members:
Wilson Tran,
Daniel Lien,
Nazifa Tahsin, 
Liana Bell
Tyler Wang

--------------------------------------------------
How to compile
--------------------------------------------------
1. Open terminal in the root folder 3110GROUPPROJECT
2. Run the following command:
    javac -d bin -cp "lib\*" src\mainClasses\*.java
3. Resulting .class folders will be in \bin

--------------------------------------------------
General Program - How to run
--------------------------------------------------
1. Open terminal in the root folder 3110GROUPPROJECT
2. Run Main by entering the following command:
    java -cp "bin;lib\*" mainClasses.Main
3. input your full/relative path to file 1
4. input your full/relative path to file 2
5. See results in results.xml

--------------------------------------------------
Testing - How to run
--------------------------------------------------
1. Go to Main class, uncomment the findFiles() call and TestingCases.writeCSVData() call.
2. Comment out realTool() if you want to run automated tests.
3. Recompile and run the general program, intructions above
4. All of the testing data should then output to a CSV file named TESTING_RESULTS.csv.

--------------------------------------------------
Bonus question - Bug Finder
--------------------------------------------------
1. Open terminal in the root folder 3110GROUPPROJECT 
2. Run BonusMain by entering the following command:
    java -cp "bin;lib\*" mainClasses.BonusMain
3. Input your Full absolute path to the git repository
4. Input the filename/relative path of the file you want to analyze
5. The latest commit in the repository MUST have a commit message containing "fix", "bug", "repair"
6. Program will find the buggy lines and track when they were introduced
