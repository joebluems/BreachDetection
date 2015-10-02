#!/usr/bin/python
import json
import numpy as np
import random

### settings ###
customers=100000
merchants=10000
rate=0.05 # fraud rate
frauds=random.sample(range(0,customers),int(customers*rate)) # fraud accounts

### generate data ###
s = np.random.poisson(12, customers)
for i in range(0,len(s)):
  for j in random.sample(range(0,merchants),s[i]):
	if i in frauds: print '{acct:"%s",merchant:"%s",fraud:"%d"}' % (i,j,1)
	else: print '{acct:"%s",merchant:"%s",fraud:"%d"}' % (i,j,0)


