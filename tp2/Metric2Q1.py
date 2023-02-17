import os
import sys
from Metric1Q1 import Metric1Q1

# create a class to calculate the metric2Q1
class Metric2Q1:
    def __init__(self, init_path):
        self.init_path = init_path

    def metric2Q1(self):
    # get the path
        root = self.init_path
        listfiles = []

        # get the lsit of files inn the path and subfolders
        for path, subdirs, files in os.walk(root):
            for file in files:
                if file.endswith(".java"):
                    listfiles.append(os.path.join(path, file))
                    

        # get the number of files in the path
        number_of_files = len(listfiles)

        # calculate the metric1Q1 for each file
        number_of_documented_files = 0
        for file in listfiles:
            comment_density = Metric1Q1(file).metric1Q1()
            if comment_density > 1/5:
                number_of_documented_files += 1


        # calculate number_of_comments/number_of_files
        documented_file_ratio = number_of_documented_files / number_of_files

        return documented_file_ratio

# run metric2Q1 on the file in argument
if __name__ == "__main__":
    # call the metric2Q1 class
    metric2Q1 = Metric2Q1(sys.argv[1])
    print("\nRatio du nombre de classe documente sur le nombre de classe totale: " + 
    str(metric2Q1.metric2Q1()) + "\n")