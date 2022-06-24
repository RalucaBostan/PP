import requests
import re
from Mecanism import *
class HTMLFormat:
    def GetListOf(self, extension):
        pass
    def GetExtList(self):
        pass
    def GetItems(self):
        pass

class BasicURL:
    def __init__(self, url, content):
        self.url = url
        self.content = content
        items = url.split('/')
        self.root = items[0] + '//' + items[2] + '/'

    def BuildAbsURL(self, relative):
        return self.root + relative

    def GetRefList(self):
        first_list = self.content.split('=')
        second_list = []
        for item in first_list:
            temp = item.split('"')
            for rel in temp:
                if len(rel) > 0:
                    second_list += [rel]
        return second_list

    def GetListOf(self, extension):
        lista = self.GetRefList()
        link_regex = []
        collection = []
        for ext in extension:
            text = str("[a-zA-Z0-9:/\.-]+\\" + ext)
            link_regex.append(re.compile(r'%s'%(text),re.S))
        for el in lista:
            for rg in link_regex:
                x = re.findall(rg,el)
                if x:
                    collection.append(x[0])
        return collection

class ArchFormat(HTMLFormat, BasicURL):
    def __init__(self, url, content):
        super().__init__(url, content)
        self.extension = ('.zip', '.7z', '.rar', '.bz2')
        self.items = BasicURL.GetListOf(self, self.extension)

    def GetExtList(self):
        return self.extension

    def GetItems(self):
        return self.items

class PictureFormat(HTMLFormat, BasicURL):
    def __init__(self, url, content):
        super().__init__(url, content)
        self.extension = ('.bmp', '.png', '.jpg', '.jpeg', '.gif', '.ico')
        self.items = BasicURL.GetListOf(self, self.extension)

    def GetExtList(self):
        return self.extension

    def GetItems(self):
        return self.items

class DocumentFormat(HTMLFormat, BasicURL):
    def __init__(self, url, content):
        super().__init__(url, content)
        self.extension = ('.txt', '.pdf', '.doc', '.docx', '.ppt', '.pptx')
        self.items = BasicURL.GetListOf(self, self.extension)


    def GetExtList(self):
        return self.extension

    def GetItems(self):
        return self.items

#-------------------------------------------------------------------------------------s

def main():
    url = 'https://www.7-zip.org/download.html'
    r = requests.get(url)
    #print(r.content)
    print('Archives:')
    arch = ArchFormat(url, r.text)
    print(arch.GetItems())
    print('Pictures')
    pict = PictureFormat(url, r.text)
    print(pict.GetItems())
    print('Documents:')
    docs = DocumentFormat(url, r.text)
    print(docs.GetItems())
    MecanismJson(url,r,"doc.js")



if __name__ == '__main__':
    main()

