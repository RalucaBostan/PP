import re
import os
files_sources = ["fisier{}.html".format(i) for i in range(1,6)]

Open_lambda = lambda p,x,y : p(x,y)


f = {file : Open_lambda(open,file,"r") for file in files_sources}
lines = {filename : file.readlines() for (filename,file) in f.items()}
x = {filename : re.findall("<body>.*</body>",text[0]) for (filename,text) in lines.items()}
headers = {filename : header[0].replace("</body>","") for (filename,header) in x.items()}
headers = {filename : header.replace("<body>","") for (filename,header) in headers.items()}
print(headers)

if os.path.exists("HTML.html"):
    os.remove("HTML.html")

output_file = Open_lambda(open,"HTML.html","a")
output_file.write("<html><head>")
for text in headers.keys():
    output_file.write("{}<br>".format(text))
output_file.write("</head><body>")
for text in headers.values():
    output_file.write("{}".format(text))
output_file.write("</body></html>")

output_file.close()
for file in f.values():
    file.close()