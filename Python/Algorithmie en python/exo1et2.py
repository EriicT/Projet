import numpy as np
import pylab as pl
import math


# Fonction qui multiplie deux matrices A et B carre 2/2, le resultat est retourne dans la matrice C
def produitM(A,B) :
	C=np.array([[1,1],[1,1]])
	C[0,0]=A[0,0]*B[0,0]+A[0,1]*B[1,0]
	C[0,1]=A[0,0]*B[0,1]+A[0,1]*B[1,1]
	C[1,0]=A[1,0]*B[0,0]+A[1,1]*B[1,0]
	C[1,1]=A[1,0]*B[0,1]+A[1,1]*B[1,1]
	return C
	
def puissanceM(n) :
	p=np.eye(2,2)
	M=np.array([[0,1],[1,1]])
	while n!=0 :
		if (n%2)!=0 :
			p=produitM(p,M)
		M=produitM(M,M)
		n=n/2
	return p[0,1]


#Fibonacci 
def fibo(a) :
	if a!=0 :
		res=puissanceM(a)
		return res
	else :
		return 0
#Fibonacci en recursif	
def fiboR(a):
	if a==0 :
		return 0
	elif a==1 :
		return 1
	else : 
		return fiboR(a-2)+fiboR(a-1)


# Fonction qui calcule le temps et qui retourne le resultat d'un fibonacci et le temps pris
def time(a) :
	import time
	debut=time.clock()
	resultat=fibo(a)
	fin=time.clock()
	temps=fin-debut
	return temps,resultat



# Fonction qui moyenne  (100 fois) les temps d'execution de Fibonacci 0 a 40
def test() :
	import numpy as np
	import pylab as pl
	times=list()
	for n in range (0,41,1) :
		z=0
		moyenne=0
		while z!=1000 :
			temps,resultat=time(n)
			moyenne+=temps			
			z+=1
			

		times.append((moyenne/1000))
		print "Fibonacci de ", n, "vaut ", resultat, " et le temps vaut :",(moyenne/1000),"s"

	abs=list(range(0,41,1))	
	X = np.arange(len(abs))
	pl.xticks(X, abs)
	pl.xticks(rotation=45)
	pl.ylim(0,max(times))
	pl.xlabel('n')
	pl.ylabel('temps')
	pl.title('Suite de Fibonacci')
	pl.plot(times)
	ln=list()
	for n in abs :
		ln.append((math.log1p(n))/19000)
	pl.plot(ln)
	pl.show()
	

test()