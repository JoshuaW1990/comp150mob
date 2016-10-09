# By Jun Wang
# Extract the information of the artists, albums, and img source.

from bs4 import BeautifulSoup
import lxml
import urllib
import urllib2
import re
import json
import pprint
import cleantool

class MusicSpiderAlbumInfo:
    def __init__(self):
        self.baseURL = "http://www.sputnikmusic.com"
        self.imageURL = None

    # Obtain the page from an url
    def getPage(self, url):
        try:
            request = urllib2.Request(url)
            response = urllib2.urlopen(request)
            return response.read()
        except urllib2.URLError, e:
            if hasattr(e,"reason"):
                print "Fail to connect sputnikmusic.com, reason: ",e.reason
                return None

    # Obtain the cover image url
    def getCover(self, page):
        soup = BeautifulSoup(page, "lxml", from_encoding="iso-8859-1")
        items = soup.find_all('img', src = re.compile("images/albums/"))
        if len(items) == 0:
            return None
        item = items[0]
        url = item['src']
        if not url.startswith('/'):
            url = '/' + url
        url = self.baseURL + url
        return url


    # Main function
    def getDetail(self, url):
        page = self.getPage(url)
        #print url
        self.imageURL = self.getCover(page)


class MusicSpiderBandInfo:
    def __init__(self):
        self.baseURL = "http://www.sputnikmusic.com"
        self.imageURL = None
        self.bandBio = None
        self.tool = cleantool.CleanTool()
        self.albumList = dict()

    # Obtain the page from an url
    def getPage(self, url):
        try:
            request = urllib2.Request(url)
            response = urllib2.urlopen(request)
            return response.read()
        except urllib2.URLError, e:
            if hasattr(e,"reason"):
                print "Fail to connect sputnikmusic.com, reason: ",e.reason
                return None

    # Obtain the artist image
    def getArtistImg(self, page):
        #writeFile("test", page)
        soup = BeautifulSoup(page, "lxml", from_encoding="iso-8859-1")
        items = soup.find_all('img', style="padding: 5px; margin-bottom:3px;")
        assert(len(items) == 1)
        item = items[0]
        ImgSrc = self.baseURL + item['src']
        return ImgSrc

    # Obtain the artist bio
    def getArtistBio(self, page):
        soup = BeautifulSoup(page, "lxml", from_encoding="iso-8859-1")
        items = soup.find_all('font', style="font-size:8pt;")
        #print items
        assert(len(items) == 1 or len(items) == 2)
        item = items[-1]
        try:
            string = "\n" + self.tool.replace(item.contents[0].encode("iso-8859-1")) + "\n"
            if isEnglish(string):
                return string
            else:
                return "None"
        except:
            return "None"


    # Obtain the album list: album name and alubm url
    def getAlbumList(self, page):
        soup = BeautifulSoup(page, "lxml", from_encoding="iso-8859-1")
        items = soup.find_all('a', href=re.compile("/album/"))
        for i in range(len(items)):
            if i%2 == 0:
                continue
            item = items[i]
            url = self.baseURL + item['href']
            spider = MusicSpiderAlbumInfo()
            spider.getDetail(url)
            if spider.imageURL == None:
                continue
            try:
                AlbumName = item.contents[0].contents[0]
                if isEnglish(AlbumName):
                    self.albumList[AlbumName.encode("iso-8859-1")] = spider.imageURL
                else:
                    continue
            except:
                continue


    # Main function
    def getDetail(self, url):
        page = self.getPage(url)
        self.imageURL = self.getArtistImg(page)
        self.bandBio = self.getArtistBio(page)
        self.getAlbumList(page)


class MusicSpiderBandList:
    def __init__(self):
        self.baseURL = "http://www.sputnikmusic.com"
        self.siteURL = "http://www.sputnikmusic.com/bandlist.php?letter="
        self.bandList = dict()

    # Obtain the page from an url
    def getPage(self, url):
        try:
            request = urllib2.Request(url)
            response = urllib2.urlopen(request)
            return response.read()
        except urllib2.URLError, e:
            if hasattr(e,"reason"):
                print "Fail to connect sputnikmusic.com, reason: ",e.reason
                return None

    #Obtain the list of the bands and their url according to the first character
    def getBandList(self, pageIndex):
        init_ch = 'a'
        ch = chr(ord(init_ch) + pageIndex)
        print "get the band list for ", ch
        url = self.siteURL + ch
        page = self.getPage(url)
        #writeFile("test", page)
        soup = BeautifulSoup(page, "lxml", from_encoding="iso-8859-1")
        #soup.prettify()
        items = soup.find_all('a', href = re.compile("/bands/"))
        count = 0
        for item in items:
            count += 1
            bandName = item.contents[0]
            if isEnglish(bandName):
                self.bandList[bandName] = None
            else:
                continue
            print "storing the information for the", count, "th band: ", bandName
            bandURL = self.baseURL + item['href']
            #print bandName, ": ", bandURL
            spider = MusicSpiderBandInfo()
            spider.getDetail(bandURL)
            self.bandList[bandName] = [spider.imageURL, spider.bandBio, spider.albumList]



def writeFile(filename, page):
    with open(filename, "w+") as f:
        f.write(page)

def isEnglish(s):
    try:
        string = s.encode('iso-8859-1')
        string.decode('ascii')
    except UnicodeDecodeError:
        return False
    else:
        return True
"""
spider = MusicSpiderBandList()
spider.getAllBands()


jsonarray = json.dumps(spider.bandList)
parsed = json.loads(jsonarray)
#print json.dumps(parsed, indent=4, sort_keys=True)
"""
for i in range(0, 26):
    spider = MusicSpiderBandList()
    spider.getBandList(i)
    ch = chr(ord('a') + i)
    filename = ch + ".json"
    with open(filename,'w') as f:
        json.dump(spider.bandList, f)
