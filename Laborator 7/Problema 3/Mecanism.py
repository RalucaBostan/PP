from ProcesareWeb import *

def MecanismJson(url,r,filename):
    arch = ArchFormat(url, r.text)
    pict = PictureFormat(url, r.text)
    docs = DocumentFormat(url, r.text)
    types = [arch,pict,docs]
    file = open(filename,"w")
    file.seek(0)
    file.truncate()
    file.write('{')
    checking = 0
    for type in types:
        elements =  type.GetItems()
        for ext in type.extension:
            checking = 0
            count = 0
            for el in elements:
                if ext in el:
                    checking = 1
                    count = count + 1
            if checking == 1:
                file.write(' %s : {'%(ext))
            current_count = 0
            for el in elements:
                if ext in el:
                    current_count = current_count + 1
                    bs = el.rfind('/')
                    fname = el[bs+1:]
                    file.write('\'' + fname + '\' : (')
                    file.write('\'%s\', '%(str(el)))
                    file.write('\'' + type.root + ""+ el+'\'')
                    if current_count < count :
                        file.write('),\n          ')
                    else:
                        file.write(')}\n')
                        checking = 0
            if checking == 1:
                file.write('}')
                file.write("\n")
    file.write('}')
    file.close()