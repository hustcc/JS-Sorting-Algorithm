def selectionsort(a,size):
  for step in range(size):
    min_idx=step
    for i in range(step+1,size):
      if a[i]<a[min_idx]:
        min_idx=i
    (a[step],a[min_idx])= (a[min_idx],a[step])

data = [-2, 45, 0, 11, -9]    //User defined input value
size = len(data)              //length of input
selectionsort(data, size)     //pass input and len value

print(data)                   // Sort data output value
