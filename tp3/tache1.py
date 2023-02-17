import csv
import matplotlib.pyplot as plt
import statistics


class Tache1():
    def main(dataClass, dataNOcom, dataNCLOC, dataDCP):

        # Quartiles of the data, Q1 ou Quartil inferieur, 
        # medianne = Q2,
        # Q3 ou Quartile superieur
        ################################################################################ 

        # TODO partie pour NOCom
        quartileNOcom = statistics.quantiles(dataNOcom, n=4)
        print("Quartile1:", quartileNOcom[0] ,
        ", Medianne:", quartileNOcom[1] ,
        ", Quartile3:", quartileNOcom[2]," of NOcom")

        # interquartile range (IQR) of quartileNOcom
        IQR_NOcom = quartileNOcom[2] - quartileNOcom[0]
        # Superior and inferior limit of the data
        lim_sup_NOcom = quartileNOcom[2] + 1.5 * IQR_NOcom
        print("Superior limit of NOcom: ", lim_sup_NOcom)
        lim_inf_NOcom = quartileNOcom[0] - 1.5*IQR_NOcom
        if lim_inf_NOcom < min(dataNOcom):
            lim_inf_NOcom = min(dataNOcom)
        print("Inferior limit of NCLOC: ", lim_inf_NOcom)

        counter_NOcom = 0
        # for element in dataNOcom where element > lim_inf_NOcom
        # increment counter_NOcom
        for element in dataNOcom:
            if element > lim_sup_NOcom or element < lim_inf_NOcom:
                # print(element)
                counter_NOcom += 1
        print("Number of elements in the data set that are greater", 
        "than the superior limit or lower than the inferior limit",
        " of NOcom (extreme points): ", counter_NOcom, "\n")

        # boxplot (boite a moustache) of NOcom
        plt.boxplot(dataNOcom)
        plt.title("NOCom")
        plt.show()

        # histogram of NOCom
        plt.hist(dataNOcom, bins=20)
        plt.title("Histogram of NOCom")
        plt.show()
        # ressemble a une distribution asymétrique
        

        ################################################################################

        # TODO partie pour NCLOC
        quartileNCLOC = statistics.quantiles(dataNCLOC, n=4)
        print("Quartile1:", quartileNCLOC[0] ,
        ", Medianne:", quartileNCLOC[1] ,
        ", Quartile3:", quartileNCLOC[2]," of NCLOC")

        # interquartile range (IQR) of quartileNCLOC
        IQR_NCLOC = quartileNCLOC[2] - quartileNCLOC[0]
        # Superior and inferior limit of the data
        lim_sup_NCLOC = quartileNCLOC[2] + 1.5 * IQR_NCLOC
        print("Superior limit of NCLOC: ", lim_sup_NCLOC)
        lim_inf_NCLOC = quartileNCLOC[0] - 1.5*IQR_NCLOC
        if lim_inf_NCLOC < min(dataNCLOC):
            lim_inf_NCLOC = min(dataNCLOC)
        print("Inferior limit of NCLOC : ", lim_inf_NCLOC)

        counter_NCLOC = 0
        # for element in dataNCLOC where element > lim_inf_NCLOC
        # increment counter_NCLOC
        for element in dataNCLOC:
            if element > lim_sup_NCLOC or element < lim_inf_NCLOC:
                counter_NCLOC += 1
        print("Number of elements in the data set that are greater",
        "than the superior limit or lower than the inferior limit",
        " of NCLOC (extreme points): ", counter_NCLOC, "\n")

        # boxplot (boite a moustache) of NCLOC
        plt.boxplot(dataNCLOC)
        plt.title("NCLOC")
        plt.show()

        # histogram of NCLOC
        plt.hist(dataNCLOC, bins=20)
        plt.title("Histogram of NCLOC")
        plt.show()
        # ressemble a une distribution unimodale


        ################################################################################ 

        # TODO partie pour DCP
        quartileDCP = statistics.quantiles(dataDCP, n=4)
        print("Quartile1:", quartileDCP[0] ,
        ", Medianne:", quartileDCP[1] ,
        ", Quartile3:", quartileDCP[2]," of DCP")

        # interquartile range (IQR) of quartileDCP
        IQR_DCP = quartileDCP[2] - quartileDCP[0]
        # Superior and inferior limit of the data
        lim_sup_DCP = quartileDCP[2] + 1.5 * IQR_DCP
        print("Superior limit of DCP: ", lim_sup_DCP)
        lim_inf_DCP = quartileDCP[0] - 1.5*IQR_DCP
        if lim_inf_DCP < min(dataDCP):
            lim_inf_DCP = min(dataDCP)
        print("Inferior limit of DCP: ", lim_inf_DCP)

        counter_DCP = 0
        # for element in dataDCP where element > lim_inf_DCP
        # increment counter_DCP
        for element in dataDCP:
            if element > lim_sup_DCP or element < lim_inf_DCP:
                counter_DCP += 1
        print("Number of elements in the data set that are greater",
        "than the superior limit or lower than the inferior limit",
        " of DCP (extreme points): ", counter_DCP, "\n")

        # boxplot (boite a moustache) of DCP
        plt.boxplot(dataDCP)
        plt.title("DCP")
        plt.show()

        # histogram of DCP
        plt.hist(dataDCP, bins=20)
        plt.title("Histogram of DCP")
        plt.show()
        # ressemble a une distribution bimodale


        # Reponse aux questions sur distribution des donnees
        # ****************************************************************************
        # ****************************************************************************
        # TODO on voit qu'aucun des histogrammes n'est totalement normal (pas de forme de cloche) 
        # il se peut que ce soit aussi a cause de la taille des bonds entre les valeurs
        # qu'on ne voit pas les information importantes (j'ai essaye de changer le nombre de bins
        # mais ca ne change pas grand chose, donc dans notre cas, on peut
        # se fier a cet histogramme pour voir la distribution des donnees)
        # ****************************************************************************
        # ****************************************************************************


        # return the extreme points ofNOcom, NCLOC, DCP
        return [[lim_sup_NOcom, lim_inf_NOcom], 
        [lim_sup_NCLOC, lim_inf_NCLOC], 
        [lim_sup_DCP, lim_inf_DCP]]
# if __name__ == '__main__':
#     main()

# reference
# https://asq.org/quality-resources/histogram