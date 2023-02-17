import csv
import matplotlib.pyplot as plt
import numpy as np
import scipy.stats as ss


class Tache2Try:
    def main( dataClass, dataNOcom, dataNCLOC, dataDCP):



        # calculate the spearman correlation
        r_NOcom_NCLOC = ss.spearmanr(dataNOcom, dataNCLOC)
        print("Correlation coefficient of spearman between NOCom and NCLOC: ", r_NOcom_NCLOC[0], "\n")
        # calculate correlation coefficient between NOCom and NCLOC with np
        r_NOcom_NCLOC_np = np.corrcoef(dataNOcom, dataNCLOC)
        print("Correlation coefficient between NOCom and NCLOC: ", r_NOcom_NCLOC_np[0,1], "\n")
        # calculate regression line with np
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
        



        # calculate the spearman correlation
        r_NOcom_DCP = ss.spearmanr(dataNOcom, dataDCP)
        print("Correlation coefficient of spearman between NOCom and DCP: ", r_NOcom_DCP[0], "\n")
        # calculate correlation coefficient between NOCom and DCP with np
        r_NOcom_DCP_np = np.corrcoef(dataNOcom, dataDCP)
        print("Correlation coefficient between NOCom and DCP: ", r_NOcom_DCP_np[0,1], "\n")
        # calculate the regression line with np
        regression_line_NOCom_DCP = np.polyfit(dataNOcom, dataDCP, 1)
        print("Regression line of NOCom and DCP: y = ",
        regression_line_NOCom_DCP[0], "x + ", regression_line_NOCom_DCP[1], "\n\n")
        # scatter plot between NOCom and DCP
        plt.scatter(dataNOcom, dataDCP)
        plt.title("Correlation between NOCom and DCP")
        # plot the regression line
        plt.plot(dataNOcom, regression_line_NOCom_DCP[0] * np.array(dataNOcom) +
        regression_line_NOCom_DCP[1], color='red')
        plt.show()


# if __name__ == '__main__':
#     main()
