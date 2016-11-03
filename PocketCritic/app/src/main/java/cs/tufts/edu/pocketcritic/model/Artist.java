package cs.tufts.edu.pocketcritic.model;

import java.util.List;

/**
 * Created by junwang on 11/3/16.
 */

public class Artist {


    /**
     * href : https://api.spotify.com/v1/search?query=Within+Temptation&offset=0&limit=20&type=artist
     * items : [{"external_urls":{"spotify":"https://open.spotify.com/artist/3hE8S8ohRErocpkY7uJW4a"},"followers":{"href":null,"total":289543},"genres":["gothic metal","gothic symphonic metal","power metal","progressive metal","symphonic metal"],"href":"https://api.spotify.com/v1/artists/3hE8S8ohRErocpkY7uJW4a","id":"3hE8S8ohRErocpkY7uJW4a","images":[{"height":640,"url":"https://i.scdn.co/image/ffaf90c9047adffbccc3af6f6f783ec608ced282","width":640},{"height":320,"url":"https://i.scdn.co/image/7312779b10c0a90d1ef61f29addb0b1f9b17c3b3","width":320},{"height":160,"url":"https://i.scdn.co/image/452dda43bb548452614feb72a87ad93fb6515f7a","width":160}],"name":"Within Temptation","popularity":62,"type":"artist","uri":"spotify:artist:3hE8S8ohRErocpkY7uJW4a"},{"external_urls":{"spotify":"https://open.spotify.com/artist/2WJcE6jpjm4TqQL7QXMTNW"},"followers":{"href":null,"total":17},"genres":[],"href":"https://api.spotify.com/v1/artists/2WJcE6jpjm4TqQL7QXMTNW","id":"2WJcE6jpjm4TqQL7QXMTNW","images":[{"height":640,"url":"https://i.scdn.co/image/4a71388808f3008ea96c5186c74d9f69911472b5","width":640},{"height":300,"url":"https://i.scdn.co/image/8e5ae20701198f5e4ca2eb565922d00f214ceeb1","width":300},{"height":64,"url":"https://i.scdn.co/image/921e8dd66d93527eb47fd9314bc115076e3452f0","width":64}],"name":"Temptation Within Reach","popularity":0,"type":"artist","uri":"spotify:artist:2WJcE6jpjm4TqQL7QXMTNW"}]
     * limit : 20
     * next : null
     * offset : 0
     * previous : null
     * total : 2
     */

    private ArtistsBean artists;

    public ArtistsBean getArtists() {
        return artists;
    }

    public void setArtists(ArtistsBean artists) {
        this.artists = artists;
    }

    public static class ArtistsBean {
        private String href;
        private int limit;
        private Object next;
        private int offset;
        private Object previous;
        private int total;
        /**
         * external_urls : {"spotify":"https://open.spotify.com/artist/3hE8S8ohRErocpkY7uJW4a"}
         * followers : {"href":null,"total":289543}
         * genres : ["gothic metal","gothic symphonic metal","power metal","progressive metal","symphonic metal"]
         * href : https://api.spotify.com/v1/artists/3hE8S8ohRErocpkY7uJW4a
         * id : 3hE8S8ohRErocpkY7uJW4a
         * images : [{"height":640,"url":"https://i.scdn.co/image/ffaf90c9047adffbccc3af6f6f783ec608ced282","width":640},{"height":320,"url":"https://i.scdn.co/image/7312779b10c0a90d1ef61f29addb0b1f9b17c3b3","width":320},{"height":160,"url":"https://i.scdn.co/image/452dda43bb548452614feb72a87ad93fb6515f7a","width":160}]
         * name : Within Temptation
         * popularity : 62
         * type : artist
         * uri : spotify:artist:3hE8S8ohRErocpkY7uJW4a
         */

        private List<ItemsBean> items;

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public Object getNext() {
            return next;
        }

        public void setNext(Object next) {
            this.next = next;
        }

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public Object getPrevious() {
            return previous;
        }

        public void setPrevious(Object previous) {
            this.previous = previous;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class ItemsBean {
            /**
             * spotify : https://open.spotify.com/artist/3hE8S8ohRErocpkY7uJW4a
             */

            private ExternalUrlsBean external_urls;
            /**
             * href : null
             * total : 289543
             */

            private FollowersBean followers;
            private String href;
            private String id;
            private String name;
            private int popularity;
            private String type;
            private String uri;
            private List<String> genres;
            /**
             * height : 640
             * url : https://i.scdn.co/image/ffaf90c9047adffbccc3af6f6f783ec608ced282
             * width : 640
             */

            private List<ImagesBean> images;

            public ExternalUrlsBean getExternal_urls() {
                return external_urls;
            }

            public void setExternal_urls(ExternalUrlsBean external_urls) {
                this.external_urls = external_urls;
            }

            public FollowersBean getFollowers() {
                return followers;
            }

            public void setFollowers(FollowersBean followers) {
                this.followers = followers;
            }

            public String getHref() {
                return href;
            }

            public void setHref(String href) {
                this.href = href;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getPopularity() {
                return popularity;
            }

            public void setPopularity(int popularity) {
                this.popularity = popularity;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUri() {
                return uri;
            }

            public void setUri(String uri) {
                this.uri = uri;
            }

            public List<String> getGenres() {
                return genres;
            }

            public void setGenres(List<String> genres) {
                this.genres = genres;
            }

            public List<ImagesBean> getImages() {
                return images;
            }

            public void setImages(List<ImagesBean> images) {
                this.images = images;
            }

            public static class ExternalUrlsBean {
                private String spotify;

                public String getSpotify() {
                    return spotify;
                }

                public void setSpotify(String spotify) {
                    this.spotify = spotify;
                }
            }

            public static class FollowersBean {
                private Object href;
                private int total;

                public Object getHref() {
                    return href;
                }

                public void setHref(Object href) {
                    this.href = href;
                }

                public int getTotal() {
                    return total;
                }

                public void setTotal(int total) {
                    this.total = total;
                }
            }

            public static class ImagesBean {
                private int height;
                private String url;
                private int width;

                public int getHeight() {
                    return height;
                }

                public void setHeight(int height) {
                    this.height = height;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public int getWidth() {
                    return width;
                }

                public void setWidth(int width) {
                    this.width = width;
                }
            }
        }
    }
}
