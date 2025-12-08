studentID = {

    "101" : "Nazifa Tahsin",
    "102" : "Anika Yasmin",
    "103" : "Asma Idriz",
    "104" : "Bart Simson"

}
#print(studentID["103"])

print(studentID.get("101"))
#if the key doesn't exist
print(studentID.get("107"))
#if the key doesn't exist and don't want none as an answer

print(studentID.get("103", "Not a valid key"))

#we can use integers as well
students = {

    101 : "Nazifa Tahsin",
    102 : "Anika Yasmin",
    103 : "Asma Idriz",
    104 : "Bart Simson"

}
print(students[103])
print("---------------------------------")