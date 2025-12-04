# define flatten() below...
def flatten(my_list):
  result = []
  for element in my_list:
    if isinstance(element, list):
      print("list found!")
      flat_list = flatten(element)
      result = result + flat_list
    else:
      result.append(element)
  return result


### reserve for testing...
planets = ['mercury', 'venus', ['earth'], 'mars', [['jupiter', 'saturn']], 'uranus', ['neptune', 'pluto']]
print(flatten(planets))