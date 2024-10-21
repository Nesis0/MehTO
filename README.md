# MehTO 

## Sujet du projet

MehTO est une application météo qui titillera votre nostalgie ! En effet, l'UI, inspirée des meilleures applications de votre enfance (seulement pour les 50 ans et +), aura le même effet que ce doux souvenir où vous vous retrouviez en famille autour d'une bonne choucroute préparée par votre mémé adorée, ou alors ce souvenir où votre cher tonton vous emmenait pêcher la carpe le lendemain matin d'une rude journée d'école. 

## Description du projet

Nous vous proposons des fonctionnalités exceptionnelles et inédites comme : 
    
- afficher la météo
  
- afficher la météo de votre localisation
    
- afficher la météo d'une de vos localisations favorites, et oui ! parce que vous pouvez ajouter des favoris !!!
    
- afficher une jolie carte où nous vous offrons le privilège de chercher vous-même votre localisation
    
- un geoguessr de la carte des vents 
    
- un geoguessr de la carte des précipitations 
  
- un geoguessr de la carte des nuages
    
- un geoguessr de la carte des pressions
    
- un geoguessr de la carte des températures
    
- des paramètres 100% locaux car le respect de votre vie privée est, bien sûr, le coeur de nos préoccupations

## Difficultés

### Map

Bien que d'une générosité sans limites, Google, qui ne pense qu'à notre bien-être, est toutefois malheureusement obligé de faire payer son API pour une modique somme de 300$ afin de bénéficier de ses excellents services. Nous comprenons très bien, Google n'est rien de plus qu'une petite startup en pleine expansion qui ne peut financer de telles infrastructures au risque de mettre en péril sa survie.

En conséquence, nous n'avons pas pu implémenter la carte comme nous le voulions, ce qui résulte en une application avec des filtres sans carte de fond, et une carte sur laquelle nous ne pouvons pas sélectionner de localisation.

### API Météo

Notre API météo possède bon nombre de qualités ⁽ᶜ'ᵉˢᵗ ᵍʳᵃᵗᵘᶦᵗ⁾ comme nous offrir la possibilité de récupérer la lagitude et la lontitude d'une localisation à partir d'une ville pour ensuite récupérer les prévisions météo. Cependant, cette API à pour vocation de nous faire sortir de notre zone de confort ! En effet, pour ne pas nous habituer à la belle vie de citadin où tout nous est offert clés en main, OpenWeather dans sa plus grande bonté d'âme nous propose quelques challenges pour nous remettre les pieds sur terre :

- l'API ne prend pas les mêmes chiffres significatifs entre ses différentes routes, c'est pourquoi il peut arriver que parfois potentiellement peut-être vous vous retrouviez avec la météo du village d'à côté.

- à l'instar de nos chers amis héritiers des pères fondateurs outre-atlantique qui préfèrent utiliser des parties du corps pour mesurer des distances contrairement à nous, fiers résidents du Vieux Continent, nos deux compères OpenStreetMap et OpenWeather ne se sont pas non plus accordés sur ce point-là. C'est pourquoi nous n'avons pas pu associer la carte interactive avec les filtres (températures, pressions...).


# Ne pas contacter

Anaëlle BOUCHUT

Baptiste LEMATTRE

Alain PLAISANCE
