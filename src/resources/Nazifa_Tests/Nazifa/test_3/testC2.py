# define flatten() below...
def flatten(my_list):
  result = []
  for element in my_list:
    if isinstance(element, list):
      flat_list = flatten(element)
      result += flat_list
    else:
      result.append(element)
  return result


### reserve for testing...
jup_sat = ['jupiter', 'saturn']
nep_plut = ['neptune', 'pluto']
planets = ['mercury', 'venus', ['earth'], 'mars', [jup_sat], 'uranus', nep_plut]
flat_list = flatten(planets)
print(flat_list)


