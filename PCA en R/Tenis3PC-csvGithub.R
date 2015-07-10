##ejemplo
#data <- normalized(data)

## cargar datos
## PRIMERO SE NECESITA INSTALAR EL PAQUETE RCURL
install.packages("RCurl")
library(RCurl)

## LUEGO SE LLAMA AL ARCHIVO A TRAVES DEL METODO getURL, EL ARCHIVO EN GITHUB
## DEBE ABRIRSE CON EL LINK OBTENIDO AL PRESIONAR "RAW" EN LAS OPCIONES DE VISTA
x <- getURL("https://raw.githubusercontent.com/msanhueza/ProyectoTenis/master/Datos/Arcilla/arcilla2012-punto.csv")
data <- read.csv(text = x)

# scatterplot matrix of intermediary (size/non-categorical) variables
library(RColorBrewer)
library(scales)
palette(alpha(brewer.pal(9,'Set1'), 0.5))
plot(data[,1:6], col=data$clase, pch=16)

datanorm<-(data[,1:16])
plot(datanorm[,1:6], col=data$clase, pch=16)

## PCA: principal Component Analysis
## Combinar los atributos iniciales en factores.

# Naively apply principal components analysis to raw data and plot
pc <- princomp(datanorm)

plot(pc)
plot(pc, type='l')

##decidi tomar las dos primeras componentens principales
# Get principal component vectors using prcomp instead of princomp
pc <- prcomp(datanorm)

################################3
### 3 componentes principales
################################3

# First for principal components
comp <- data.frame(pc$x[,1:3])

# Plot
#plot(comp, pch=16, col=rgb(0,0,0,0.5))
# scatterplot matrix of intermediary (size/non-categorical) variables
library(RColorBrewer)
library(scales)
palette(alpha(brewer.pal(9,'Set1'), 0.5))

plot(comp, col=data$clase, pch=c(16,16)[unclass(data$clase)])
#plot(comp, col=data$resultado, pch=ifelse(data$resultado=="W",-0x2642L,-0x2640L))

library(rgl)
install.packages("rgl")

plot3d(comp$PC1, comp$PC2, comp$PC3, col=c("blue","red")[unclass(data$clase)])
#pegar la clase
comp$class=data$clase

write.csv(file="superficies2012-3PC.csv", x=comp)