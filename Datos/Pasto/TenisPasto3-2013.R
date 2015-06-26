##ejemplo
#data <- normalized(data)

##cargar datos
data <- read.csv(file="pasto2013-punto.csv", header=T, sep=",", row.names=NULL)

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

write.csv(file="pasto2013-punto-3PC.csv", x=comp)