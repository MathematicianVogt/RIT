def goodFib(n,current=1,prev=0):
	if (n==0):
		return prev
	elif (n==1):
		return current
	else:
		return goodFib(n-1,current+prev,current)
print str(goodFib(100))

#answer above 354224848179261915075
#Ryan Vogt