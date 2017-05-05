from numpy import array, argmin
import numpy as np
np.set_printoptions(threshold=np.inf)


def track(D):
	D = np.asarray(D)
	i, j = array(D.shape) - 1
	p, q = [i], [j]
	while (i > 1 and j > 1):
		tb = argmin((D[i-1, j-1], D[i-1, j], D[i, j-1]))
		if (tb == 0):
			i = i - 1
			j = j - 1
		elif (tb == 1):
			i = i - 1
		elif (tb == 2):
			j = j - 1

		p.insert(0, i)
		q.insert(0, j)

	p.insert(0, 0)
	q.insert(0, 0)
	return (array(p), array(q))

def find_first_and_last_pos(D):
	start = 0
	end = 0
	flag = False
	for i in range(1, len(D)):
		if (not flag and D[i - 1] == D[i]):
			start = i - 1
			flag = True
		if (flag and D[i - 1] != D[i]):
			end = i
			break
	return (start, end)

A, B = track(D)
start, end = find_first_and_last_pos(A)
print(start)







