import numpy as np
import random

# Propriete : I(i,j)  :  T[i] + T[j] = x 
# Initialisation : i = 0, j=n-1 avec n = len(T)
# Conditions d'arret : i=j ou i >= j	
# Progression : 
#  I(i,j)  & i!=j  &  i<j  & T[i] +T[j] = x => I(i+1,j-1)
#  I(i,j)  & i!=j  &  i<j  & T[i] +T[j] < x => I(i+1,j)
#  I(i,j)  & i!=j  &  i<j  & T[i] +T[j] > x => I(i,j-1)

T=[0,1,2,3,7,8,10,11,12,13,14,15,16,19,20,21,22,23,24,25,26]

def sommeX(x,t) :
	i=0 # initialisation, i=0
	j=len(t)-1 # initialisation j=n-1
	couple=np.array([0,0])	 # mise en forme
	testcouple=False # mise en forme 
	print "Tous les couples (i,j) de T tels que i+j=",x,"sont :" # mise en forme
	# I(i,j)
	while  i!=j and i<j :
		# I(i,j) & i!=j & i<j
		if t[i] + t[j] == x :		
			#I(i+1,j-1)
			couple[0]=t[i]
			couple[1]=t[j]
			print "(",i,",",j,")", couple		
			i+=1 #I(i,j-1)
			j-=1 #I(i,j)
			testcouple=True
			
		elif t[i] + t[j] < x :
			i+=1  # I(i+1,j)
		else :
			j-=1 # I(i,j-1)
			
	if testcouple==False :
		print "Il n'y a pas de couple pour ",x

#Complexite : i et j sont differents, et parcourent tous deux T. Comme T est strictement croissant, le maximum de combinaison i et j possible est n car i et j differents et i <j . Donc teta(n)		


# Propriete : I(i,j,m)  :  T[i] + T[j] = T[m]
# Initialisation : i = 0, j=n-1 avec n = len(T), m=0
# Conditions d'arret : m=n-1
# Progression : 
#  I(i,j,m)  & i!=j  &  i<j  & m != n-1 & T[i] +T[j] = T[m] => I(i+1,j-1,m)
#  I(i,j,m)  & i!=j  &  i<j  & m != n-1 & T[i] +T[j] < T[m] => I(i+1,j,m)
#  I(i,j,m)  & i!=j  &  i<j  & m != n-1 &T[i] +T[j] > T[m]=> I(i,j-1,m)
# I(i,j,m) & ( i =j ou i < j ) => I(i=0,j=n,-1,m+1)

def sommeAll() :	
	for n in T :
		sommeX(n,T)
		
#Complexite : i et j sont differents, et parcourent tous deux T. Comme T est strictement croissant, le maximum de combinaison i et j possible est n car i et j differents et i <j.Donc teta(n),
# De plus, comme le tableau est parcouru pour determiner tous les couples de chaque element du tableau ( soit n fois), on a n fois le temps precedent : n*n d'ou tete(n*n)
sommeX(15,T)
sommeX(24,T)
sommeX(7,T)
sommeX(2,T)
sommeX(19,T)