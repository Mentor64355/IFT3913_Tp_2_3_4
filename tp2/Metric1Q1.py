# calculate the density of comments of the file in argument

import re
import sys
import os
import csv

# create a class to calculate the metric1Q1
class Metric1Q1:
    def __init__(self, fileName):
        self.fileName = fileName

    def metric1Q1(self):
        
        file_name = self.fileName
        file = open(file_name, "r")
        lines = file.readlines()
        
        number_of_lines = len(lines)
        is_in_multiline = False

        # get the number of comments
        number_of_comments = 0
        for line in lines:
            # print("line: " + line + "\n")
            # check if the line is a comment java
            if re.match(r"^\s*//.*", line):
                number_of_comments += 1
            # check if the line is a multi line comment java
            elif re.match(r"^\s*/\*.*", line):
                number_of_comments += 1
                is_in_multiline = True
            # check if the line is a multi line comment java
            elif re.match(r".*\*/\s*$", line):
                number_of_comments += 1
                is_in_multiline = False
            
            elif is_in_multiline:
                number_of_comments += 1


        # calculate the density of comments
        density_of_comments = number_of_comments / number_of_lines

        return density_of_comments


# run metric1Q1 on the file in argument
if __name__ == "__main__":
    # create a csv file metric1Q1.csv to store the results
    if os.path.exists("metric1Q1.csv"):
        os.remove("metric1Q1.csv")
    
    with open("metric1Q1.csv", "w") as csv_file:
        
        csv_writer = csv.writer(csv_file)
        csv_writer.writerow(["File Name", " Density of Comments"])

        # if that path is a file, get the file name from path
        if os.path.isfile(sys.argv[1]):

            file_name = os.path.basename(sys.argv[1])

            metric1Q1 = Metric1Q1(sys.argv[1])
            # write the file name and the density of comments
            csv_writer.writerow([file_name, " " + str(metric1Q1.metric1Q1())])
        
        for path, subdirs, files in os.walk(sys.argv[1]):
            for name in files:
                if name.endswith(".java"):
                    # create a metric1Q1 object
                    metric1Q1 = Metric1Q1(os.path.join(path, name))
                    # calculate the density of comments
                    density_of_comments = metric1Q1.metric1Q1()
                    # write the file name and the density of comments
                    csv_writer.writerow([name , " " + str(density_of_comments)])