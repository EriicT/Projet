import csv
import pickle
import matplotlib.pyplot as plt
import pylab as pl
from mpl_toolkits.basemap import Basemap
import numpy as np

''' Regions '''
EasternAfrica=list()
RateEAf=0
MiddleAfrica=list()
RateMAf=0
NorthernAfrica=list()
RateNAf=0
WesternAfrica=list()
RateWAf=0
SouthernAfrica=list()
RateSAf=0

Caribbean=list()
RateCa=0
CentralAmerica=list()
RateCAm=0
SouthernAmerica=list()
RateSAm=0
NorthernAmerica=list()
RateNAm=0

CentralAsia=list()
RateCAs=0
EasternAsia=list()
RateEAs =0
SEAsia=list()
RateSEAs=0
SouthernAsia=list()
RateSAs=0
WesternAsia=list()
RateWAs=0

EasternEurope=list()
RateEE=0
NorthernEurope=list()
RateNE=0
SouthernEurope=list()
RateSE=0
WesternEurope=list()
RateWE=0

Australasia=list()
RateAus=0
Micronesia=list()
RateMic=0
Polynesia=list()
RatePoly=0


'''Continent'''
Africa = list()
America= list()
Asia =list()
Europe=list()
Oceania=list()

TauxAfrica=0
TauxAmerica=0
TauxAsia=0
TauxEurope=0
TauxOceania=0


''' Exemples de la Grece '''
listannee=list()
listtauxg=list()
listnombre=list()


''' Decoupage du Fichier CSV '''
# Ouvrir un CSVreader qui decoupe la ligne delimite par des ; en une liste 
with open('donnee.csv', 'r') as csvfile:
	ligne = csv.reader(csvfile,  delimiter=';', quotechar='|')
	#Recuperation des elements et reconvertion des elements en type
	for element in ligne :
		pays=str(element[0].decode('ascii','ignore'))
		taux=float(element[1])
		nombre = float(int(element[2]))
		continent =str(element[3])
		region=str(element[4].decode('ascii','ignore'))


		'''Fabrication Region '''
		#Trie des pays en fonction des regions et somme des taux
		if region == 'Northern Africa' :
			NorthernAfrica.append(pays)
			RateNAf+=taux

		elif region == 'Southern Africa' :
			SouthernAfrica.append(pays)
			RateSAf+=+taux
			
		elif region == 'Eastern Africa':
			EasternAfrica.append(pays)
			RateEAf += taux
			
		elif region ==  'Western Africa':
			WesternAfrica.append(pays)
			RateWAf += taux
			
		elif region ==  'Middle Africa':
			MiddleAfrica.append(pays)
			RateMAf +=taux
			
		elif region ==  'Eastern Europe':
			EasternEurope.append(pays)
			RateEE+= taux
			
		elif region == 'Western Europe':
			WesternEurope.append(pays)
			RateWE += taux
			
		elif region == 'Northern Europe':
			NorthernEurope.append(pays)
			RateNE+=taux
			
		elif region == 'Southern Europe': 
			SouthernEurope.append(pays)
			RateSE+=taux
			
		elif region== 'Central Asia' :
			CentralAsia.append(pays)
			RateCAs+= taux
			
		elif region=='Eastern Asia':
			EasternAsia.append(pays)
			RateEAs+=taux
		
		elif region=='South-Eastern Asia' :
			SEAsia.append(pays)
			RateSEAs+=taux
		
		elif region== 'Southern Asia' :
		        SouthernAsia.append(pays)
			RateSAs+=taux
			
		elif region == 'Western Asia':
			WesternAsia.append(pays)
			RateWAs+=taux
			
		elif region == 'Australasia':
			Australasia.append(pays)
			RateAus+=taux
		elif region== 'Micronesia':
			Micronesia.append(pays)
			RateMic+=taux
			
		elif region == 'Polynesia':
			Polynesia.append(pays)
			RatePoly+=taux
		
		elif region == 'Northern America':
			NorthernAmerica.append(pays)
			RateNAm += taux
			
		elif region == 'South America':
			SouthernAmerica.append(pays)
			RateSAm += taux
			
		elif region == 'Central America':
			CentralAmerica.append(pays)
			RateCAm += taux
		
			
		''' 'Fabrication de liste continent  '''
		if continent == 'Africa' :
			Africa.append(pays)
			TauxAfrica+=taux
			
		elif continent == 'Americas' :
			America.append(pays)
			TauxAmerica+=taux
			
		elif continent == 'Asia' :
			Asia.append(pays)
			TauxAsia+=taux
			
		elif continent == 'Europe' :
			Europe.append(pays)
			TauxEurope+=taux
			
		elif continent == 'Oceania' :
			Oceania.append(pays)
			TauxOceania+=taux
			
#Cration d'un CSV pour la recuperation de donnee
with open('donnes.csv', 'r') as csvfile:
	#Decoupage des elements a partir de la limite ;
	ligne = csv.reader(csvfile,  delimiter=';', quotechar='|')
	#Recuparation des donnees de l'element et traitement des types
	for element in ligne :
		annee=str(element[0].decode('ascii','ignore'))
		taux=float(element[1])
		nombre = float(int(element[2]))
		#ajouts aux listes
		listannee.append(annee)
		listtauxg.append(taux)		

''' Caclul des taux moyens continentaux'''	
MoyenAfrica=TauxAfrica/(len(Africa))
MoyenAmerica=TauxAmerica/(len(America))	
MoyenAsia=TauxAsia/(len(Asia))	
MoyenEurope=TauxEurope/(len(Europe))
MoyenOceania=TauxOceania/(len(Oceania))	

''' Cacul du maximum et du minimum du taux continental '''
maxRate=max(MoyenAfrica,MoyenAmerica,MoyenAsia,MoyenEurope,MoyenOceania)
minRate=min(MoyenAfrica,MoyenAmerica,MoyenAsia,MoyenEurope,MoyenOceania)

''' Calcul des taux moyens par regions'''
#Afrique
mNAf=RateNAf/len(NorthernAfrica)
mSAf=RateSAf/len(SouthernAfrica)
mEAf=RateEAf/len(EasternAfrica)
mMAf=RateMAf/len(MiddleAfrica)
mWAf=RateWAf/len(WesternAfrica)

#Amerique
mNAm=RateNAm/len(NorthernAmerica)
mSAm=RateSAm/len(SouthernAmerica)
mCAm=RateCAm/len(CentralAmerica)
#Europe
mNE=RateNE/len(NorthernEurope)
mSE=RateSE/len(SouthernEurope)
mWE=RateWE/len(WesternEurope)
mEE=RateEE/len(EasternEurope)

#Australiasa
mMic=RateMic/len(Micronesia)
mPoly=RatePoly/len(Polynesia)
mAus=RateAus/len(Australasia)

#Asie
mSAs=RateSAs/len(SouthernAsia)
mCAs=RateCAs/len(CentralAsia)
mSEAs=RateSEAs/len(SEAsia)
mEAs=RateEAs/len(EasternAsia)
mWAs=RateWAs/len(WesternAsia)




''' Construction de l'histogramme'''

# Organise la bar des abscisses selon le nombre d'element dans la liste listannee
X = np.arange(len(listannee))

#
pl.bar(X,listtauxg)

pl.xticks(X, listannee)
pl.xticks(rotation=45)
pl.ylim(0, 2)
pl.xlabel('Years')
pl.ylabel('Rate')
pl.title('Rate of homicide in Greece since 1996')

pl.show()



''' Construction de la BaseMap ''' 
# Creation d'une BaseMap, avec comme modele : Robin, plannisphere centre sur l'equateur et le meridien de Greenwitch
map = Basemap(projection='robin', lat_0=0, lon_0=0,
              resolution='l', area_thresh=1000.0)

# Fixation de la taille de l'image 
fig = plt.figure(figsize=(15,15))
plt.title('Les taux d homicides volontaires par region')

#Dessin des limites Terre/Mer puis entre pays 
map.drawcoastlines()
map.drawcountries()
map.drawlsmask( land_color='bisque', ocean_color='lightblue',lakes=True)

# Fixation des valeurs des latitude ( de - 90 a 90 avec un pas de 30) et des longitudes ( -180 a 180 avec un pas de 45)
parallels = np.arange(-90,90, 30)
meridians= np.arange(-180, 180, 45)

# Dessin des latitudes et longitudes avec ledgendes: gauche/droite/haut/bas
map.drawparallels(parallels,labels=[True,True,False,False])
map.drawmeridians(meridians,labels=[False,False,False,True])


#Ajoute des listes des coordonnees et de la taille des points 
lon= list([18,22,-83,46,-15,8,31,12,-3,110,59,90,138,35,-105,15,-63,-149,158,140])
lat = list([30,-30,9,5,13,60,48,41,40,12,39,23,36,38,54,10,-38,-17,6,-23])
taux=list([mNAf,mSAf,mCAm,mMAf,mWAf,mNAm,mSAm,mEAf,mNE,mSE,mWE,mEE,mMic,mPoly,mAus,mSAs,mSEAs,mEAs,mWAs])

#La taillle d'un point vaut 75 fois plus que le taux associe
taille=list()
for valeur in taux :
	taille.append(valeur*75)

#La couleur depend de sa position par rapport au taux maximum et minimum	
couleur=list()
for valeur in taux :
	if valeur < minRate :
		couleur.append('chartreuse')
	elif valeur < (maxRate/2) :
		couleur.append('gold')
	elif valeur < maxRate:
		couleur.append('orange')
	elif valeur >= maxRate:
		couleur.append('red')

                
print ( "La couleur verte claire correspond aux taux : < " + str(int(minRate)) )
print ( "La couleur jaune pale correspond aux taux : " +str(int(minRate))  +"<  <" + str(int(maxRate/2))  ) 
print ( "La couleur orange correspond aux taux : "  +str(int(maxRate/2)) + "<  < "+ str(int(maxRate))  )
print ( "La couleur rouge correspond aux taux :" +str(int(maxRate))  +" >" )


#Calcul des coordonnees et placement des points
x,y = map(lon,lat)
map.scatter(x,y,taille,couleur) 

#Affichage de la Basemap
plt.show()
