class Employee():
  new_id = 1
  def __init__(self):
    self.id = Employee.new_id
    Employee.new_id += 1

class Meeting:
  def __init__(self):
    self.attendees = []
  
  def __add__(self, employee): #overloading + operation
    print("ID {} added.".format(employee.id))
    self.attendees.append(employee)


  
    
e1 = Employee()

