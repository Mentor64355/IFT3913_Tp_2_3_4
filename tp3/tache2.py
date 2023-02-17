import csv
import matplotlib.pyplot as plt
import numpy as np
import scipy.stats as ss


class Tache2:
    def main( dataClass, dataNOcom, dataNCLOC,dataDCP, limitPoints):


        tmpDataNOcom = dataNOcom.copy()
        popListNCLOC = []
        popListDCP = []

        # get through the list datas and remove the points 
        # that are above or bellow the sub/inf limit
        for i in range(len(dataNOcom)):
            if (dataNOcom[i] > limitPoints[0][0] or dataNOcom[i] < limitPoints[0][1]):
                popListNCLOC.append(i)
                
            elif (dataNCLOC[i] > limitPoints[1][0] or dataNCLOC[i] < limitPoints[1][1]):
                popListNCLOC.append(i)


        for i in range(len(tmpDataNOcom)):
            if (tmpDataNOcom[i] > limitPoints[0][0] or tmpDataNOcom[i] < limitPoints[0][1]):
                popListDCP.append(i)
            elif (dataDCP[i] > limitPoints[2][0] or dataDCP[i] < limitPoints[2][1]):
                popListDCP.append(i)


        lenNcloc = len(popListNCLOC) - 1
        for i in range(len(popListNCLOC)):
            dataNOcom.pop(popListNCLOC[lenNcloc - i])
            dataNCLOC.pop(popListNCLOC[lenNcloc - i])

        lenDcp = len(popListDCP) - 1
        for i in range(len(popListDCP)): 
            tmpDataNOcom.pop(popListDCP[lenDcp - i])
            dataDCP.pop(popListDCP[lenDcp - i])
            


        # calculate the correlation coefficient between NOCom and NCLOC with np
        r_NOcom_NCLOC = np.corrcoef(dataNOcom, dataNCLOC)
        print("Correlation coefficient between NOCom and NCLOC: ", r_NOcom_NCLOC[0,1], "\n")
        # calculate spearman correlation
        r_NOcom_NCLOC_spear = ss.spearmanr(dataNOcom, dataNCLOC)
        print("Correlation coefficient of spearman between NOCom and NCLOC: ", r_NOcom_NCLOC_spear[0], "\n")
        # calculate the regression line with np
        regression_line_NOCom_NCLOC = np.polyfit(dataNOcom, dataNCLOC, 1)
        print("Regression line of NOCom and NCLOC: y = ", 
        regression_line_NOCom_NCLOC[0], "x + ", regression_line_NOCom_NCLOC[1], "\n\n")
        # scatter plot between NOCom and NCLOC
        plt.scatter(dataNOcom, dataNCLOC)
        plt.title("Correlation between NOCom and NCLOC")
        # plot the regression line
        plt.plot(dataNOcom, regression_line_NOCom_NCLOC[0] * np.array(dataNOcom) + 
        regression_line_NOCom_NCLOC[1], color='red')
        plt.show()

        # explications:
        # ****************************************************************************
        # ****************************************************************************
        # comme on peut le voir avec le graphique denude des points extremes
        # (si le x OU le y etait un point extreme dans la boite a moustache de la tache1,
        # on l'enleve du graphique et du calcul de la correlation + regression line),
        # il y a une corrélation positive entre NOCom et NCLOC
        # parcontre, il est difficile de savoir si nos donnes
        # suivent une distribution normale uniquement avec l'allure du graphique. 
        # On a donc calculé le coefficient de corrélation de pearson et de spearman
        # et on voit que le coefficient de pearson est plus proche de 1 que le coefficient de spearman
        # ce qui veut dire que ces donnes suivent plus ou moins une distribution normale 
        # ****************************************************************************
        # ****************************************************************************


        # calculate the correlation coefficient between NOCom and DCP with np
        # TODO Here
        r_NOcom_DCP = np.corrcoef(tmpDataNOcom, dataDCP)
        # r_NOcom_DCP = np.corrcoef(dataNOcom, dataDCP)
        print("Correlation coefficient between NOCom and DCP: ", r_NOcom_DCP[0,1], "\n")
        # calculate spearman correlation
        r_NOcom_DCP_spear = ss.spearmanr(tmpDataNOcom, dataDCP)
        print("Correlation coefficient of spearman between NOCom and DCP: ", r_NOcom_DCP_spear[0], "\n")
        # calculate the regression line with np
        regression_line_NOCom_DCP = np.polyfit(tmpDataNOcom, dataDCP, 1)
        print("Regression line of NOCom and DCP: y = ", 
        regression_line_NOCom_DCP[0], "x + ", regression_line_NOCom_DCP[1], "\n\n")
        # scatter plot between NOCom and DCP
        plt.scatter(tmpDataNOcom, dataDCP)
        plt.title("Correlation between NOCom and DCP")
        # plot the regression line
        plt.plot(tmpDataNOcom, regression_line_NOCom_DCP[0] * np.array(tmpDataNOcom) +
        regression_line_NOCom_DCP[1], color='red')
        plt.show()

        # explications:
        # ****************************************************************************
        # ****************************************************************************
        # comme pour l'analyse de la correlation entre NOCom et NCLOC,
        # on peut voir avec le graphique denude des points extremes
        # qui est difficile de savoir si nos donnes suivent une distribution normale 
        # (encore plus dans ce cas la que pour la correlation entre NOCom et NCLOC).
        # On voit tous de meme une correlation negative entre NOCom et DCP
        # et apres le calcul du coefficient de corrélation de pearson et de spearman
        # on a que le coefficient de pearson est legerement plus proche de -1 que le coefficient de spearman
        # ce qui veut dire que ces donnes suivent plus ou moins une distribution normale 
        # meme si la correlation n'est en aucun cas forte.
        # ****************************************************************************
        # ****************************************************************************

    


# if __name__ == '__main__':
#     main()
