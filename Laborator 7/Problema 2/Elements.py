from web1 import *

class Template(LinkCollector):
    def __init__(self,url,filename):
        self.url = url
        self.collector = LinkCollector(url)
        self.file = open(filename,"w")

    def WriteFile(self):
        pass

    def CloseFile(self):
        self.file.close()

class Links(Template):
    def __init__(self,url,filename):
        super().__init__(url,filename)
        self.links = self.collector.collect_links(LINK_REGEX)

    def WriteFile(self):
        links = []
        self.file.seek(0)
        self.file.truncate()
        self.file.write("Linkuri http/https : \n")
        for i in range(len(self.links)):
            links.append(str(self.links[i][0] + "://" + self.links[i][1]))
        for link in links:
            self.file.write(link)
            self.file.write("\n")
        self.CloseFile()

class Images(Template):
    def __init__(self,url,filename):
        super().__init__(url,filename)
        self.images = self.collector.collect_links(LINK_Images)

    def WriteFile(self):
        self.file.seek(0)
        self.file.truncate()
        self.file.write("Imagini : \n")
        for img in self.images:
            self.file.write(img)
            self.file.write("\n")
        self.CloseFile()

class Docs(Template):
    def __init__(self,url,filename):
        super().__init__(url,filename)
        self.docs = self.collector.collect_links(LINK_Docs)

    def WriteFile(self):
        self.file.seek(0)
        self.file.truncate()
        self.file.write("Documente : \n")
        for doc in self.docs:
            self.file.write(doc)
            self.file.write("\n")
        self.CloseFile()