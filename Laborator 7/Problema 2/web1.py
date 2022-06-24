from urllib.request import urlopen
import re
import urllib.request
import functools
import operator

LINK_REGEX = re.compile(r'"(http|https)://(.*?)"',re.S)
LINK_HTMLs = re.compile(r'"[a-zA-Z0-9\.]*\.html"',re.S)
LINK_CSS = re.compile(r'[a-zA-Z0-9\.]*\.css',re.S)
LINK_Images = re.compile(r'[a-zA-Z0-9\.]*\.png|[a-zA-Z0-9\.]*\.jpg|[a-zA-Z0-9\.]*\.gif',re.S)
LINK_Docs = re.compile(r'[a-zA-Z0-9\.]*\.doc|[a-zA-Z0-9\.]*\.pdf|[a-zA-Z0-9\.]*\.txt|[a-zA-Z0-9\.]*\.epub',re.S)

class LinkCollector:
    def __init__(self, url):
        self.url = url

    def collect_links(self,searching):
        with urllib.request.urlopen(self.url) as response:
            page = str(response.read())
        return re.findall(searching, page)

class LinkOperations(LinkCollector):
    def __init__(self,url):
        super().__init__(url)

    def GetHtmls(self):
        self.htmls = self.collect_links(LINK_HTMLs)
        print("\n\nFisiere de tip HTML : ")
        for file in self.htmls:
            file = file[1:len(file)-1]
            print(file)

    def GetCSS(self):
        self.css = self.collect_links(LINK_CSS)
        print("\n\nFisiere de tip CSS : ")
        for file in self.css:
            print(file)
        print('\n\n')

    def Execute(self):
        self.GetHtmls()
        self.GetCSS()

