package cs.tufts.edu.pocketcritic.model;

import java.util.List;

/**
 * Created by junwang on 11/3/16.
 */

public class Album {

    /**
     * href : https://api.spotify.com/v1/search?query=album%3Agold+artist%3Aabba&offset=0&limit=20&type=album
     * items : [{"album_type":"compilation","artists":[{"external_urls":{"spotify":"https://open.spotify.com/artist/0LcJLqbBmaGUft1e9Mm8HV"},"href":"https://api.spotify.com/v1/artists/0LcJLqbBmaGUft1e9Mm8HV","id":"0LcJLqbBmaGUft1e9Mm8HV","name":"ABBA","type":"artist","uri":"spotify:artist:0LcJLqbBmaGUft1e9Mm8HV"}],"available_markets":["CA","MX","US"],"external_urls":{"spotify":"https://open.spotify.com/album/2cKZfaz7GiGtZEeQNj1RyR"},"href":"https://api.spotify.com/v1/albums/2cKZfaz7GiGtZEeQNj1RyR","id":"2cKZfaz7GiGtZEeQNj1RyR","images":[{"height":640,"url":"https://i.scdn.co/image/4db90273419a26fe4b6b998f5cfc359a19d3c817","width":640},{"height":300,"url":"https://i.scdn.co/image/0336d17e26bdd21ad334815ba95d579d5b68a0ce","width":300},{"height":64,"url":"https://i.scdn.co/image/4e31df94e01d6ec001044ea44a1565bdf7498936","width":64}],"name":"ABBA Gold","type":"album","uri":"spotify:album:2cKZfaz7GiGtZEeQNj1RyR"},{"album_type":"compilation","artists":[{"external_urls":{"spotify":"https://open.spotify.com/artist/0LcJLqbBmaGUft1e9Mm8HV"},"href":"https://api.spotify.com/v1/artists/0LcJLqbBmaGUft1e9Mm8HV","id":"0LcJLqbBmaGUft1e9Mm8HV","name":"ABBA","type":"artist","uri":"spotify:artist:0LcJLqbBmaGUft1e9Mm8HV"}],"available_markets":["CA","MX","US"],"external_urls":{"spotify":"https://open.spotify.com/album/5Q5uVB5bN6ce3JzPKj2UUG"},"href":"https://api.spotify.com/v1/albums/5Q5uVB5bN6ce3JzPKj2UUG","id":"5Q5uVB5bN6ce3JzPKj2UUG","images":[{"height":640,"url":"https://i.scdn.co/image/416de492e474fe6d168b45e0bb566234348537ae","width":640},{"height":300,"url":"https://i.scdn.co/image/b9ac3e71b38706c912df4ba815961c4686c95fb5","width":300},{"height":64,"url":"https://i.scdn.co/image/471d9464ec9df6d366530d73a912bdf3a72e57ad","width":64}],"name":"More ABBA Gold","type":"album","uri":"spotify:album:5Q5uVB5bN6ce3JzPKj2UUG"}]
     * limit : 20
     * next : null
     * offset : 0
     * previous : null
     * total : 2
     */

    private AlbumsBean albums;

    public AlbumsBean getAlbums() {
        return albums;
    }

    public void setAlbums(AlbumsBean albums) {
        this.albums = albums;
    }

    public static class AlbumsBean {
        private String href;
        private int limit;
        private Object next;
        private int offset;
        private Object previous;
        private int total;
        /**
         * album_type : compilation
         * artists : [{"external_urls":{"spotify":"https://open.spotify.com/artist/0LcJLqbBmaGUft1e9Mm8HV"},"href":"https://api.spotify.com/v1/artists/0LcJLqbBmaGUft1e9Mm8HV","id":"0LcJLqbBmaGUft1e9Mm8HV","name":"ABBA","type":"artist","uri":"spotify:artist:0LcJLqbBmaGUft1e9Mm8HV"}]
         * available_markets : ["CA","MX","US"]
         * external_urls : {"spotify":"https://open.spotify.com/album/2cKZfaz7GiGtZEeQNj1RyR"}
         * href : https://api.spotify.com/v1/albums/2cKZfaz7GiGtZEeQNj1RyR
         * id : 2cKZfaz7GiGtZEeQNj1RyR
         * images : [{"height":640,"url":"https://i.scdn.co/image/4db90273419a26fe4b6b998f5cfc359a19d3c817","width":640},{"height":300,"url":"https://i.scdn.co/image/0336d17e26bdd21ad334815ba95d579d5b68a0ce","width":300},{"height":64,"url":"https://i.scdn.co/image/4e31df94e01d6ec001044ea44a1565bdf7498936","width":64}]
         * name : ABBA Gold
         * type : album
         * uri : spotify:album:2cKZfaz7GiGtZEeQNj1RyR
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
            private String album_type;
            /**
             * spotify : https://open.spotify.com/album/2cKZfaz7GiGtZEeQNj1RyR
             */

            private ExternalUrlsBean external_urls;
            private String href;
            private String id;
            private String name;
            private String type;
            private String uri;
            /**
             * external_urls : {"spotify":"https://open.spotify.com/artist/0LcJLqbBmaGUft1e9Mm8HV"}
             * href : https://api.spotify.com/v1/artists/0LcJLqbBmaGUft1e9Mm8HV
             * id : 0LcJLqbBmaGUft1e9Mm8HV
             * name : ABBA
             * type : artist
             * uri : spotify:artist:0LcJLqbBmaGUft1e9Mm8HV
             */

            private List<ArtistsBean> artists;
            private List<String> available_markets;
            /**
             * height : 640
             * url : https://i.scdn.co/image/4db90273419a26fe4b6b998f5cfc359a19d3c817
             * width : 640
             */

            private List<ImagesBean> images;

            public String getAlbum_type() {
                return album_type;
            }

            public void setAlbum_type(String album_type) {
                this.album_type = album_type;
            }

            public ExternalUrlsBean getExternal_urls() {
                return external_urls;
            }

            public void setExternal_urls(ExternalUrlsBean external_urls) {
                this.external_urls = external_urls;
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

            public List<ArtistsBean> getArtists() {
                return artists;
            }

            public void setArtists(List<ArtistsBean> artists) {
                this.artists = artists;
            }

            public List<String> getAvailable_markets() {
                return available_markets;
            }

            public void setAvailable_markets(List<String> available_markets) {
                this.available_markets = available_markets;
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

            public static class ArtistsBean {
                /**
                 * spotify : https://open.spotify.com/artist/0LcJLqbBmaGUft1e9Mm8HV
                 */

                private ExternalUrlsBean external_urls;
                private String href;
                private String id;
                private String name;
                private String type;
                private String uri;

                public ExternalUrlsBean getExternal_urls() {
                    return external_urls;
                }

                public void setExternal_urls(ExternalUrlsBean external_urls) {
                    this.external_urls = external_urls;
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

                public static class ExternalUrlsBean {
                    private String spotify;

                    public String getSpotify() {
                        return spotify;
                    }

                    public void setSpotify(String spotify) {
                        this.spotify = spotify;
                    }
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
