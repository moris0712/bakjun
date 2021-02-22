lst1 = [0 for a in range(0,9)]
for i in range(0,9):
	lst1[i] = int(input())
print(max(lst1))
print(lst1.index(max(lst1)))
