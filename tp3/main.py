# main function that calls the main function of tache1.py
import csv
from tache1 import Tache1 
from tache2 import Tache2 
from tache2AvecExtreme import Tache2Try 
# import 


def main():

    dataClass = []
    dataNOcom = []
    dataNCLOC = []
    dataDCP = []    

    with open("tp3\jfreechart-stats.csv", newline='') as csvfile:
        
        reader = csv.DictReader(csvfile)
        for row in reader:
            # rows are: 'class', ' NOCom', ' NCLOC', ' DCP'
            # Careful cause the keys 2-3-4 got spaces in front of them 
            dataClass.append(row['class'])
            dataNOcom.append(int(row[' NOCom']))
            dataNCLOC.append(int(row[' NCLOC']))
            dataDCP.append(float(row[' DCP']))


    point_limit = Tache1.main( dataClass, dataNOcom, dataNCLOC, dataDCP)

    # TODO uncomment those 4 lines to see the graphs + correlations with all datas,
    # including the extreme points

    # tmpNOcom = dataNOcom.copy()
    # tmpNCLOC = dataNCLOC.copy()
    # tmpDCP = dataDCP.copy()
    # Tache2Try.main(dataClass, tmpNOcom, tmpNCLOC, tmpDCP)

    print("\n\n")
    Tache2.main(dataClass, dataNOcom, dataNCLOC, dataDCP, point_limit)
    
    
    
    




# call main function
if __name__ == "__main__":
    main()