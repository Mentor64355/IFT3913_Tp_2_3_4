import subprocess
import sys

def install(package):
    subprocess.check_call([sys.executable, "-m", "pip", "install", package])
def execute(path):
    out = sys.stdout
    command = "lizard " + path
    reportxml ="lizard --output_file CC_xml --xml "+path
    reporthtml ="lizard --output_file CC_html --html "+path
    subprocess.run(command)
    subprocess.run(reportxml)
    subprocess.run(reporthtml)

# installing lizard for cyclomatic complexity analysis
#https://pypi.org/project/lizard/
install("lizard")
# installing Jinja2 to generate html file 
# https://pypi.org/project/Jinja2/
install("Jinja2") 
execute(sys.argv[1])

