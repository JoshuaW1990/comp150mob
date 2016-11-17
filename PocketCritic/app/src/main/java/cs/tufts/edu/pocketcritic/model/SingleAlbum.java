package cs.tufts.edu.pocketcritic.model;

import java.util.List;

/**
 * Created by junwang on 11/16/16.
 */

public class SingleAlbum {

    /**
     * album_type : album
     * artists : [{"external_urls":{"spotify":"https://open.spotify.com/artist/2WX2uTcsvV5OnS0inACecP"},"href":"https://api.spotify.com/v1/artists/2WX2uTcsvV5OnS0inACecP","id":"2WX2uTcsvV5OnS0inACecP","name":"Birdy","type":"artist","uri":"spotify:artist:2WX2uTcsvV5OnS0inACecP"}]
     * available_markets : ["CA","ES","US"]
     * copyrights : [{"text":"2013 Jasmine Van den Bogaerde under exclusive licence to Warner Music UK Limited","type":"C"},{"text":"2013 Jasmine Van den Bogaerde under exclusive licence to Warner Music UK Limited","type":"P"}]
     * external_ids : {"upc":"825646289899"}
     * external_urls : {"spotify":"https://open.spotify.com/album/0r94AFhRLvpfXvha7vx2dK"}
     * genres : []
     * href : https://api.spotify.com/v1/albums/0r94AFhRLvpfXvha7vx2dK
     * id : 0r94AFhRLvpfXvha7vx2dK
     * images : [{"height":640,"url":"https://i.scdn.co/image/f20065d8eac54d258d50e96d0e44b8d4fa2d0350","width":640},{"height":300,"url":"https://i.scdn.co/image/6f4a4eb482c109a19a2f5aed1f296c14824fef9e","width":300},{"height":64,"url":"https://i.scdn.co/image/c4156603fd6e19252b478fa6f236c7f525e843ce","width":64}]
     * label : Atlantic Records
     * name : Fire Within
     * popularity : 65
     * release_date : 2013-09-23
     * release_date_precision : day
     * tracks : {"href":"https://api.spotify.com/v1/albums/0r94AFhRLvpfXvha7vx2dK/tracks?offset=0&limit=50","items":[{"artists":[{"external_urls":{"spotify":"https://open.spotify.com/artist/2WX2uTcsvV5OnS0inACecP"},"href":"https://api.spotify.com/v1/artists/2WX2uTcsvV5OnS0inACecP","id":"2WX2uTcsvV5OnS0inACecP","name":"Birdy","type":"artist","uri":"spotify:artist:2WX2uTcsvV5OnS0inACecP"}],"available_markets":["CA","ES","US"],"disc_number":1,"duration_ms":201080,"explicit":false,"external_urls":{"spotify":"https://open.spotify.com/track/1oBDaufG8Arkz3t52UbiQi"},"href":"https://api.spotify.com/v1/tracks/1oBDaufG8Arkz3t52UbiQi","id":"1oBDaufG8Arkz3t52UbiQi","name":"Skinny Love","preview_url":"https://p.scdn.co/mp3-preview/fa2ee949327d85e7bd22385f00737744ebf87d60","track_number":1,"type":"track","uri":"spotify:track:1oBDaufG8Arkz3t52UbiQi"},{"artists":[{"external_urls":{"spotify":"https://open.spotify.com/artist/2WX2uTcsvV5OnS0inACecP"},"href":"https://api.spotify.com/v1/artists/2WX2uTcsvV5OnS0inACecP","id":"2WX2uTcsvV5OnS0inACecP","name":"Birdy","type":"artist","uri":"spotify:artist:2WX2uTcsvV5OnS0inACecP"}],"available_markets":["CA","ES","US"],"disc_number":1,"duration_ms":252106,"explicit":false,"external_urls":{"spotify":"https://open.spotify.com/track/1D4XFKolp63mNqqMXJJL1V"},"href":"https://api.spotify.com/v1/tracks/1D4XFKolp63mNqqMXJJL1V","id":"1D4XFKolp63mNqqMXJJL1V","name":"Wings","preview_url":"https://p.scdn.co/mp3-preview/b383277697465b1737ed511714764255eb97c42f","track_number":2,"type":"track","uri":"spotify:track:1D4XFKolp63mNqqMXJJL1V"},{"artists":[{"external_urls":{"spotify":"https://open.spotify.com/artist/2WX2uTcsvV5OnS0inACecP"},"href":"https://api.spotify.com/v1/artists/2WX2uTcsvV5OnS0inACecP","id":"2WX2uTcsvV5OnS0inACecP","name":"Birdy","type":"artist","uri":"spotify:artist:2WX2uTcsvV5OnS0inACecP"}],"available_markets":["CA","ES","US"],"disc_number":1,"duration_ms":214160,"explicit":false,"external_urls":{"spotify":"https://open.spotify.com/track/2rrpQlayufAK1MiYI9fYOy"},"href":"https://api.spotify.com/v1/tracks/2rrpQlayufAK1MiYI9fYOy","id":"2rrpQlayufAK1MiYI9fYOy","name":"Heart Of Gold","preview_url":"https://p.scdn.co/mp3-preview/f47b639ec77d6757ed49854f8673639a5bcd1f25","track_number":3,"type":"track","uri":"spotify:track:2rrpQlayufAK1MiYI9fYOy"},{"artists":[{"external_urls":{"spotify":"https://open.spotify.com/artist/2WX2uTcsvV5OnS0inACecP"},"href":"https://api.spotify.com/v1/artists/2WX2uTcsvV5OnS0inACecP","id":"2WX2uTcsvV5OnS0inACecP","name":"Birdy","type":"artist","uri":"spotify:artist:2WX2uTcsvV5OnS0inACecP"}],"available_markets":["CA","ES","US"],"disc_number":1,"duration_ms":239022,"explicit":false,"external_urls":{"spotify":"https://open.spotify.com/track/6pQfHpMKbKsUIFHu2jQ2jW"},"href":"https://api.spotify.com/v1/tracks/6pQfHpMKbKsUIFHu2jQ2jW","id":"6pQfHpMKbKsUIFHu2jQ2jW","name":"Light Me Up (US Version)","preview_url":"https://p.scdn.co/mp3-preview/110f8a8bbacca3a1c2d26f7ef9d7b6105bab4f65","track_number":4,"type":"track","uri":"spotify:track:6pQfHpMKbKsUIFHu2jQ2jW"},{"artists":[{"external_urls":{"spotify":"https://open.spotify.com/artist/2WX2uTcsvV5OnS0inACecP"},"href":"https://api.spotify.com/v1/artists/2WX2uTcsvV5OnS0inACecP","id":"2WX2uTcsvV5OnS0inACecP","name":"Birdy","type":"artist","uri":"spotify:artist:2WX2uTcsvV5OnS0inACecP"}],"available_markets":["CA","ES","US"],"disc_number":1,"duration_ms":221660,"explicit":false,"external_urls":{"spotify":"https://open.spotify.com/track/7K4H981XtOLGZv3EmbAJpn"},"href":"https://api.spotify.com/v1/tracks/7K4H981XtOLGZv3EmbAJpn","id":"7K4H981XtOLGZv3EmbAJpn","name":"Words As Weapons (US Version)","preview_url":"https://p.scdn.co/mp3-preview/0ee340edf082e4aa156467f778512bbff7a2bd8a","track_number":5,"type":"track","uri":"spotify:track:7K4H981XtOLGZv3EmbAJpn"},{"artists":[{"external_urls":{"spotify":"https://open.spotify.com/artist/2WX2uTcsvV5OnS0inACecP"},"href":"https://api.spotify.com/v1/artists/2WX2uTcsvV5OnS0inACecP","id":"2WX2uTcsvV5OnS0inACecP","name":"Birdy","type":"artist","uri":"spotify:artist:2WX2uTcsvV5OnS0inACecP"}],"available_markets":["CA","ES","US"],"disc_number":1,"duration_ms":268711,"explicit":false,"external_urls":{"spotify":"https://open.spotify.com/track/6X39LR0glRm9Gfqbv0VCUe"},"href":"https://api.spotify.com/v1/tracks/6X39LR0glRm9Gfqbv0VCUe","id":"6X39LR0glRm9Gfqbv0VCUe","name":"All You Never Say","preview_url":"https://p.scdn.co/mp3-preview/e2b29b9f38777862a17e71d4c1061f894190b6df","track_number":6,"type":"track","uri":"spotify:track:6X39LR0glRm9Gfqbv0VCUe"},{"artists":[{"external_urls":{"spotify":"https://open.spotify.com/artist/2WX2uTcsvV5OnS0inACecP"},"href":"https://api.spotify.com/v1/artists/2WX2uTcsvV5OnS0inACecP","id":"2WX2uTcsvV5OnS0inACecP","name":"Birdy","type":"artist","uri":"spotify:artist:2WX2uTcsvV5OnS0inACecP"}],"available_markets":["CA","ES","US"],"disc_number":1,"duration_ms":183680,"explicit":false,"external_urls":{"spotify":"https://open.spotify.com/track/5qenS4Saf6EyIJ2yViBG9b"},"href":"https://api.spotify.com/v1/tracks/5qenS4Saf6EyIJ2yViBG9b","id":"5qenS4Saf6EyIJ2yViBG9b","name":"Strange Birds","preview_url":"https://p.scdn.co/mp3-preview/915fe85b1b5d5a98794c70964004437496e15f1c","track_number":7,"type":"track","uri":"spotify:track:5qenS4Saf6EyIJ2yViBG9b"},{"artists":[{"external_urls":{"spotify":"https://open.spotify.com/artist/2WX2uTcsvV5OnS0inACecP"},"href":"https://api.spotify.com/v1/artists/2WX2uTcsvV5OnS0inACecP","id":"2WX2uTcsvV5OnS0inACecP","name":"Birdy","type":"artist","uri":"spotify:artist:2WX2uTcsvV5OnS0inACecP"}],"available_markets":["CA","ES","US"],"disc_number":1,"duration_ms":194960,"explicit":false,"external_urls":{"spotify":"https://open.spotify.com/track/6RvPb011iGh9nGVARZQCqV"},"href":"https://api.spotify.com/v1/tracks/6RvPb011iGh9nGVARZQCqV","id":"6RvPb011iGh9nGVARZQCqV","name":"Maybe","preview_url":"https://p.scdn.co/mp3-preview/f1d4a2147e819875a1f8c11b6c050ee8e1a5ae9b","track_number":8,"type":"track","uri":"spotify:track:6RvPb011iGh9nGVARZQCqV"},{"artists":[{"external_urls":{"spotify":"https://open.spotify.com/artist/2WX2uTcsvV5OnS0inACecP"},"href":"https://api.spotify.com/v1/artists/2WX2uTcsvV5OnS0inACecP","id":"2WX2uTcsvV5OnS0inACecP","name":"Birdy","type":"artist","uri":"spotify:artist:2WX2uTcsvV5OnS0inACecP"}],"available_markets":["CA","ES","US"],"disc_number":1,"duration_ms":243200,"explicit":false,"external_urls":{"spotify":"https://open.spotify.com/track/4Xpvr0uY1qMA5gABUdsbUW"},"href":"https://api.spotify.com/v1/tracks/4Xpvr0uY1qMA5gABUdsbUW","id":"4Xpvr0uY1qMA5gABUdsbUW","name":"No Angel","preview_url":"https://p.scdn.co/mp3-preview/e91477396820cfe05240d8f8042e8120e65fbaca","track_number":9,"type":"track","uri":"spotify:track:4Xpvr0uY1qMA5gABUdsbUW"},{"artists":[{"external_urls":{"spotify":"https://open.spotify.com/artist/2WX2uTcsvV5OnS0inACecP"},"href":"https://api.spotify.com/v1/artists/2WX2uTcsvV5OnS0inACecP","id":"2WX2uTcsvV5OnS0inACecP","name":"Birdy","type":"artist","uri":"spotify:artist:2WX2uTcsvV5OnS0inACecP"}],"available_markets":["CA","ES","US"],"disc_number":1,"duration_ms":277666,"explicit":false,"external_urls":{"spotify":"https://open.spotify.com/track/3WmeTmPFJegeMM88qz8J0p"},"href":"https://api.spotify.com/v1/tracks/3WmeTmPFJegeMM88qz8J0p","id":"3WmeTmPFJegeMM88qz8J0p","name":"All About You","preview_url":"https://p.scdn.co/mp3-preview/ad6f585e9c4e7198fd4f15dc8b4ccadb77040ebf","track_number":10,"type":"track","uri":"spotify:track:3WmeTmPFJegeMM88qz8J0p"},{"artists":[{"external_urls":{"spotify":"https://open.spotify.com/artist/2WX2uTcsvV5OnS0inACecP"},"href":"https://api.spotify.com/v1/artists/2WX2uTcsvV5OnS0inACecP","id":"2WX2uTcsvV5OnS0inACecP","name":"Birdy","type":"artist","uri":"spotify:artist:2WX2uTcsvV5OnS0inACecP"}],"available_markets":["CA","ES","US"],"disc_number":1,"duration_ms":244080,"explicit":false,"external_urls":{"spotify":"https://open.spotify.com/track/1jw3IuhB1taTK2Or4XDLr0"},"href":"https://api.spotify.com/v1/tracks/1jw3IuhB1taTK2Or4XDLr0","id":"1jw3IuhB1taTK2Or4XDLr0","name":"Standing In The Way of The Light","preview_url":"https://p.scdn.co/mp3-preview/25f749f75406c6568718b53834189b3bd737377e","track_number":11,"type":"track","uri":"spotify:track:1jw3IuhB1taTK2Or4XDLr0"},{"artists":[{"external_urls":{"spotify":"https://open.spotify.com/artist/2WX2uTcsvV5OnS0inACecP"},"href":"https://api.spotify.com/v1/artists/2WX2uTcsvV5OnS0inACecP","id":"2WX2uTcsvV5OnS0inACecP","name":"Birdy","type":"artist","uri":"spotify:artist:2WX2uTcsvV5OnS0inACecP"}],"available_markets":["CA","ES","US"],"disc_number":1,"duration_ms":244386,"explicit":false,"external_urls":{"spotify":"https://open.spotify.com/track/1tqxGKQSnyFCAte5nkQbhF"},"href":"https://api.spotify.com/v1/tracks/1tqxGKQSnyFCAte5nkQbhF","id":"1tqxGKQSnyFCAte5nkQbhF","name":"Shine","preview_url":"https://p.scdn.co/mp3-preview/41fcf1a9c75be3908b702fc062ef4f68d56b2d0c","track_number":12,"type":"track","uri":"spotify:track:1tqxGKQSnyFCAte5nkQbhF"},{"artists":[{"external_urls":{"spotify":"https://open.spotify.com/artist/2WX2uTcsvV5OnS0inACecP"},"href":"https://api.spotify.com/v1/artists/2WX2uTcsvV5OnS0inACecP","id":"2WX2uTcsvV5OnS0inACecP","name":"Birdy","type":"artist","uri":"spotify:artist:2WX2uTcsvV5OnS0inACecP"}],"available_markets":["CA","ES","US"],"disc_number":1,"duration_ms":256853,"explicit":false,"external_urls":{"spotify":"https://open.spotify.com/track/65BMD6q7gRTUn9uZmEdjEz"},"href":"https://api.spotify.com/v1/tracks/65BMD6q7gRTUn9uZmEdjEz","id":"65BMD6q7gRTUn9uZmEdjEz","name":"People Help The People","preview_url":"https://p.scdn.co/mp3-preview/f4ca3ea54912a5e4b88bc7a61851b652f4be7dfa","track_number":13,"type":"track","uri":"spotify:track:65BMD6q7gRTUn9uZmEdjEz"}],"limit":50,"next":null,"offset":0,"previous":null,"total":13}
     * type : album
     * uri : spotify:album:0r94AFhRLvpfXvha7vx2dK
     */

    private String album_type;
    /**
     * upc : 825646289899
     */

    private ExternalIdsBean external_ids;
    /**
     * spotify : https://open.spotify.com/album/0r94AFhRLvpfXvha7vx2dK
     */

    private ExternalUrlsBean external_urls;
    private String href;
    private String id;
    private String label;
    private String name;
    private int popularity;
    private String release_date;
    private String release_date_precision;
    /**
     * href : https://api.spotify.com/v1/albums/0r94AFhRLvpfXvha7vx2dK/tracks?offset=0&limit=50
     * items : [{"artists":[{"external_urls":{"spotify":"https://open.spotify.com/artist/2WX2uTcsvV5OnS0inACecP"},"href":"https://api.spotify.com/v1/artists/2WX2uTcsvV5OnS0inACecP","id":"2WX2uTcsvV5OnS0inACecP","name":"Birdy","type":"artist","uri":"spotify:artist:2WX2uTcsvV5OnS0inACecP"}],"available_markets":["CA","ES","US"],"disc_number":1,"duration_ms":201080,"explicit":false,"external_urls":{"spotify":"https://open.spotify.com/track/1oBDaufG8Arkz3t52UbiQi"},"href":"https://api.spotify.com/v1/tracks/1oBDaufG8Arkz3t52UbiQi","id":"1oBDaufG8Arkz3t52UbiQi","name":"Skinny Love","preview_url":"https://p.scdn.co/mp3-preview/fa2ee949327d85e7bd22385f00737744ebf87d60","track_number":1,"type":"track","uri":"spotify:track:1oBDaufG8Arkz3t52UbiQi"},{"artists":[{"external_urls":{"spotify":"https://open.spotify.com/artist/2WX2uTcsvV5OnS0inACecP"},"href":"https://api.spotify.com/v1/artists/2WX2uTcsvV5OnS0inACecP","id":"2WX2uTcsvV5OnS0inACecP","name":"Birdy","type":"artist","uri":"spotify:artist:2WX2uTcsvV5OnS0inACecP"}],"available_markets":["CA","ES","US"],"disc_number":1,"duration_ms":252106,"explicit":false,"external_urls":{"spotify":"https://open.spotify.com/track/1D4XFKolp63mNqqMXJJL1V"},"href":"https://api.spotify.com/v1/tracks/1D4XFKolp63mNqqMXJJL1V","id":"1D4XFKolp63mNqqMXJJL1V","name":"Wings","preview_url":"https://p.scdn.co/mp3-preview/b383277697465b1737ed511714764255eb97c42f","track_number":2,"type":"track","uri":"spotify:track:1D4XFKolp63mNqqMXJJL1V"},{"artists":[{"external_urls":{"spotify":"https://open.spotify.com/artist/2WX2uTcsvV5OnS0inACecP"},"href":"https://api.spotify.com/v1/artists/2WX2uTcsvV5OnS0inACecP","id":"2WX2uTcsvV5OnS0inACecP","name":"Birdy","type":"artist","uri":"spotify:artist:2WX2uTcsvV5OnS0inACecP"}],"available_markets":["CA","ES","US"],"disc_number":1,"duration_ms":214160,"explicit":false,"external_urls":{"spotify":"https://open.spotify.com/track/2rrpQlayufAK1MiYI9fYOy"},"href":"https://api.spotify.com/v1/tracks/2rrpQlayufAK1MiYI9fYOy","id":"2rrpQlayufAK1MiYI9fYOy","name":"Heart Of Gold","preview_url":"https://p.scdn.co/mp3-preview/f47b639ec77d6757ed49854f8673639a5bcd1f25","track_number":3,"type":"track","uri":"spotify:track:2rrpQlayufAK1MiYI9fYOy"},{"artists":[{"external_urls":{"spotify":"https://open.spotify.com/artist/2WX2uTcsvV5OnS0inACecP"},"href":"https://api.spotify.com/v1/artists/2WX2uTcsvV5OnS0inACecP","id":"2WX2uTcsvV5OnS0inACecP","name":"Birdy","type":"artist","uri":"spotify:artist:2WX2uTcsvV5OnS0inACecP"}],"available_markets":["CA","ES","US"],"disc_number":1,"duration_ms":239022,"explicit":false,"external_urls":{"spotify":"https://open.spotify.com/track/6pQfHpMKbKsUIFHu2jQ2jW"},"href":"https://api.spotify.com/v1/tracks/6pQfHpMKbKsUIFHu2jQ2jW","id":"6pQfHpMKbKsUIFHu2jQ2jW","name":"Light Me Up (US Version)","preview_url":"https://p.scdn.co/mp3-preview/110f8a8bbacca3a1c2d26f7ef9d7b6105bab4f65","track_number":4,"type":"track","uri":"spotify:track:6pQfHpMKbKsUIFHu2jQ2jW"},{"artists":[{"external_urls":{"spotify":"https://open.spotify.com/artist/2WX2uTcsvV5OnS0inACecP"},"href":"https://api.spotify.com/v1/artists/2WX2uTcsvV5OnS0inACecP","id":"2WX2uTcsvV5OnS0inACecP","name":"Birdy","type":"artist","uri":"spotify:artist:2WX2uTcsvV5OnS0inACecP"}],"available_markets":["CA","ES","US"],"disc_number":1,"duration_ms":221660,"explicit":false,"external_urls":{"spotify":"https://open.spotify.com/track/7K4H981XtOLGZv3EmbAJpn"},"href":"https://api.spotify.com/v1/tracks/7K4H981XtOLGZv3EmbAJpn","id":"7K4H981XtOLGZv3EmbAJpn","name":"Words As Weapons (US Version)","preview_url":"https://p.scdn.co/mp3-preview/0ee340edf082e4aa156467f778512bbff7a2bd8a","track_number":5,"type":"track","uri":"spotify:track:7K4H981XtOLGZv3EmbAJpn"},{"artists":[{"external_urls":{"spotify":"https://open.spotify.com/artist/2WX2uTcsvV5OnS0inACecP"},"href":"https://api.spotify.com/v1/artists/2WX2uTcsvV5OnS0inACecP","id":"2WX2uTcsvV5OnS0inACecP","name":"Birdy","type":"artist","uri":"spotify:artist:2WX2uTcsvV5OnS0inACecP"}],"available_markets":["CA","ES","US"],"disc_number":1,"duration_ms":268711,"explicit":false,"external_urls":{"spotify":"https://open.spotify.com/track/6X39LR0glRm9Gfqbv0VCUe"},"href":"https://api.spotify.com/v1/tracks/6X39LR0glRm9Gfqbv0VCUe","id":"6X39LR0glRm9Gfqbv0VCUe","name":"All You Never Say","preview_url":"https://p.scdn.co/mp3-preview/e2b29b9f38777862a17e71d4c1061f894190b6df","track_number":6,"type":"track","uri":"spotify:track:6X39LR0glRm9Gfqbv0VCUe"},{"artists":[{"external_urls":{"spotify":"https://open.spotify.com/artist/2WX2uTcsvV5OnS0inACecP"},"href":"https://api.spotify.com/v1/artists/2WX2uTcsvV5OnS0inACecP","id":"2WX2uTcsvV5OnS0inACecP","name":"Birdy","type":"artist","uri":"spotify:artist:2WX2uTcsvV5OnS0inACecP"}],"available_markets":["CA","ES","US"],"disc_number":1,"duration_ms":183680,"explicit":false,"external_urls":{"spotify":"https://open.spotify.com/track/5qenS4Saf6EyIJ2yViBG9b"},"href":"https://api.spotify.com/v1/tracks/5qenS4Saf6EyIJ2yViBG9b","id":"5qenS4Saf6EyIJ2yViBG9b","name":"Strange Birds","preview_url":"https://p.scdn.co/mp3-preview/915fe85b1b5d5a98794c70964004437496e15f1c","track_number":7,"type":"track","uri":"spotify:track:5qenS4Saf6EyIJ2yViBG9b"},{"artists":[{"external_urls":{"spotify":"https://open.spotify.com/artist/2WX2uTcsvV5OnS0inACecP"},"href":"https://api.spotify.com/v1/artists/2WX2uTcsvV5OnS0inACecP","id":"2WX2uTcsvV5OnS0inACecP","name":"Birdy","type":"artist","uri":"spotify:artist:2WX2uTcsvV5OnS0inACecP"}],"available_markets":["CA","ES","US"],"disc_number":1,"duration_ms":194960,"explicit":false,"external_urls":{"spotify":"https://open.spotify.com/track/6RvPb011iGh9nGVARZQCqV"},"href":"https://api.spotify.com/v1/tracks/6RvPb011iGh9nGVARZQCqV","id":"6RvPb011iGh9nGVARZQCqV","name":"Maybe","preview_url":"https://p.scdn.co/mp3-preview/f1d4a2147e819875a1f8c11b6c050ee8e1a5ae9b","track_number":8,"type":"track","uri":"spotify:track:6RvPb011iGh9nGVARZQCqV"},{"artists":[{"external_urls":{"spotify":"https://open.spotify.com/artist/2WX2uTcsvV5OnS0inACecP"},"href":"https://api.spotify.com/v1/artists/2WX2uTcsvV5OnS0inACecP","id":"2WX2uTcsvV5OnS0inACecP","name":"Birdy","type":"artist","uri":"spotify:artist:2WX2uTcsvV5OnS0inACecP"}],"available_markets":["CA","ES","US"],"disc_number":1,"duration_ms":243200,"explicit":false,"external_urls":{"spotify":"https://open.spotify.com/track/4Xpvr0uY1qMA5gABUdsbUW"},"href":"https://api.spotify.com/v1/tracks/4Xpvr0uY1qMA5gABUdsbUW","id":"4Xpvr0uY1qMA5gABUdsbUW","name":"No Angel","preview_url":"https://p.scdn.co/mp3-preview/e91477396820cfe05240d8f8042e8120e65fbaca","track_number":9,"type":"track","uri":"spotify:track:4Xpvr0uY1qMA5gABUdsbUW"},{"artists":[{"external_urls":{"spotify":"https://open.spotify.com/artist/2WX2uTcsvV5OnS0inACecP"},"href":"https://api.spotify.com/v1/artists/2WX2uTcsvV5OnS0inACecP","id":"2WX2uTcsvV5OnS0inACecP","name":"Birdy","type":"artist","uri":"spotify:artist:2WX2uTcsvV5OnS0inACecP"}],"available_markets":["CA","ES","US"],"disc_number":1,"duration_ms":277666,"explicit":false,"external_urls":{"spotify":"https://open.spotify.com/track/3WmeTmPFJegeMM88qz8J0p"},"href":"https://api.spotify.com/v1/tracks/3WmeTmPFJegeMM88qz8J0p","id":"3WmeTmPFJegeMM88qz8J0p","name":"All About You","preview_url":"https://p.scdn.co/mp3-preview/ad6f585e9c4e7198fd4f15dc8b4ccadb77040ebf","track_number":10,"type":"track","uri":"spotify:track:3WmeTmPFJegeMM88qz8J0p"},{"artists":[{"external_urls":{"spotify":"https://open.spotify.com/artist/2WX2uTcsvV5OnS0inACecP"},"href":"https://api.spotify.com/v1/artists/2WX2uTcsvV5OnS0inACecP","id":"2WX2uTcsvV5OnS0inACecP","name":"Birdy","type":"artist","uri":"spotify:artist:2WX2uTcsvV5OnS0inACecP"}],"available_markets":["CA","ES","US"],"disc_number":1,"duration_ms":244080,"explicit":false,"external_urls":{"spotify":"https://open.spotify.com/track/1jw3IuhB1taTK2Or4XDLr0"},"href":"https://api.spotify.com/v1/tracks/1jw3IuhB1taTK2Or4XDLr0","id":"1jw3IuhB1taTK2Or4XDLr0","name":"Standing In The Way of The Light","preview_url":"https://p.scdn.co/mp3-preview/25f749f75406c6568718b53834189b3bd737377e","track_number":11,"type":"track","uri":"spotify:track:1jw3IuhB1taTK2Or4XDLr0"},{"artists":[{"external_urls":{"spotify":"https://open.spotify.com/artist/2WX2uTcsvV5OnS0inACecP"},"href":"https://api.spotify.com/v1/artists/2WX2uTcsvV5OnS0inACecP","id":"2WX2uTcsvV5OnS0inACecP","name":"Birdy","type":"artist","uri":"spotify:artist:2WX2uTcsvV5OnS0inACecP"}],"available_markets":["CA","ES","US"],"disc_number":1,"duration_ms":244386,"explicit":false,"external_urls":{"spotify":"https://open.spotify.com/track/1tqxGKQSnyFCAte5nkQbhF"},"href":"https://api.spotify.com/v1/tracks/1tqxGKQSnyFCAte5nkQbhF","id":"1tqxGKQSnyFCAte5nkQbhF","name":"Shine","preview_url":"https://p.scdn.co/mp3-preview/41fcf1a9c75be3908b702fc062ef4f68d56b2d0c","track_number":12,"type":"track","uri":"spotify:track:1tqxGKQSnyFCAte5nkQbhF"},{"artists":[{"external_urls":{"spotify":"https://open.spotify.com/artist/2WX2uTcsvV5OnS0inACecP"},"href":"https://api.spotify.com/v1/artists/2WX2uTcsvV5OnS0inACecP","id":"2WX2uTcsvV5OnS0inACecP","name":"Birdy","type":"artist","uri":"spotify:artist:2WX2uTcsvV5OnS0inACecP"}],"available_markets":["CA","ES","US"],"disc_number":1,"duration_ms":256853,"explicit":false,"external_urls":{"spotify":"https://open.spotify.com/track/65BMD6q7gRTUn9uZmEdjEz"},"href":"https://api.spotify.com/v1/tracks/65BMD6q7gRTUn9uZmEdjEz","id":"65BMD6q7gRTUn9uZmEdjEz","name":"People Help The People","preview_url":"https://p.scdn.co/mp3-preview/f4ca3ea54912a5e4b88bc7a61851b652f4be7dfa","track_number":13,"type":"track","uri":"spotify:track:65BMD6q7gRTUn9uZmEdjEz"}]
     * limit : 50
     * next : null
     * offset : 0
     * previous : null
     * total : 13
     */

    private TracksBean tracks;
    private String type;
    private String uri;
    /**
     * external_urls : {"spotify":"https://open.spotify.com/artist/2WX2uTcsvV5OnS0inACecP"}
     * href : https://api.spotify.com/v1/artists/2WX2uTcsvV5OnS0inACecP
     * id : 2WX2uTcsvV5OnS0inACecP
     * name : Birdy
     * type : artist
     * uri : spotify:artist:2WX2uTcsvV5OnS0inACecP
     */

    private List<ArtistsBean> artists;
    private List<String> available_markets;
    /**
     * text : 2013 Jasmine Van den Bogaerde under exclusive licence to Warner Music UK Limited
     * type : C
     */

    private List<CopyrightsBean> copyrights;
    private List<?> genres;
    /**
     * height : 640
     * url : https://i.scdn.co/image/f20065d8eac54d258d50e96d0e44b8d4fa2d0350
     * width : 640
     */

    private List<ImagesBean> images;

    public String getAlbum_type() {
        return album_type;
    }

    public void setAlbum_type(String album_type) {
        this.album_type = album_type;
    }

    public ExternalIdsBean getExternal_ids() {
        return external_ids;
    }

    public void setExternal_ids(ExternalIdsBean external_ids) {
        this.external_ids = external_ids;
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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getRelease_date_precision() {
        return release_date_precision;
    }

    public void setRelease_date_precision(String release_date_precision) {
        this.release_date_precision = release_date_precision;
    }

    public TracksBean getTracks() {
        return tracks;
    }

    public void setTracks(TracksBean tracks) {
        this.tracks = tracks;
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

    public List<CopyrightsBean> getCopyrights() {
        return copyrights;
    }

    public void setCopyrights(List<CopyrightsBean> copyrights) {
        this.copyrights = copyrights;
    }

    public List<?> getGenres() {
        return genres;
    }

    public void setGenres(List<?> genres) {
        this.genres = genres;
    }

    public List<ImagesBean> getImages() {
        return images;
    }

    public void setImages(List<ImagesBean> images) {
        this.images = images;
    }

    public static class ExternalIdsBean {
        private String upc;

        public String getUpc() {
            return upc;
        }

        public void setUpc(String upc) {
            this.upc = upc;
        }
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

    public static class TracksBean {
        private String href;
        private int limit;
        private Object next;
        private int offset;
        private Object previous;
        private int total;
        /**
         * artists : [{"external_urls":{"spotify":"https://open.spotify.com/artist/2WX2uTcsvV5OnS0inACecP"},"href":"https://api.spotify.com/v1/artists/2WX2uTcsvV5OnS0inACecP","id":"2WX2uTcsvV5OnS0inACecP","name":"Birdy","type":"artist","uri":"spotify:artist:2WX2uTcsvV5OnS0inACecP"}]
         * available_markets : ["CA","ES","US"]
         * disc_number : 1
         * duration_ms : 201080
         * explicit : false
         * external_urls : {"spotify":"https://open.spotify.com/track/1oBDaufG8Arkz3t52UbiQi"}
         * href : https://api.spotify.com/v1/tracks/1oBDaufG8Arkz3t52UbiQi
         * id : 1oBDaufG8Arkz3t52UbiQi
         * name : Skinny Love
         * preview_url : https://p.scdn.co/mp3-preview/fa2ee949327d85e7bd22385f00737744ebf87d60
         * track_number : 1
         * type : track
         * uri : spotify:track:1oBDaufG8Arkz3t52UbiQi
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
            private int disc_number;
            private int duration_ms;
            private boolean explicit;
            /**
             * spotify : https://open.spotify.com/track/1oBDaufG8Arkz3t52UbiQi
             */

            private ExternalUrlsBean external_urls;
            private String href;
            private String id;
            private String name;
            private String preview_url;
            private int track_number;
            private String type;
            private String uri;
            /**
             * external_urls : {"spotify":"https://open.spotify.com/artist/2WX2uTcsvV5OnS0inACecP"}
             * href : https://api.spotify.com/v1/artists/2WX2uTcsvV5OnS0inACecP
             * id : 2WX2uTcsvV5OnS0inACecP
             * name : Birdy
             * type : artist
             * uri : spotify:artist:2WX2uTcsvV5OnS0inACecP
             */

            private List<ArtistsBean> artists;
            private List<String> available_markets;

            public int getDisc_number() {
                return disc_number;
            }

            public void setDisc_number(int disc_number) {
                this.disc_number = disc_number;
            }

            public int getDuration_ms() {
                return duration_ms;
            }

            public void setDuration_ms(int duration_ms) {
                this.duration_ms = duration_ms;
            }

            public boolean isExplicit() {
                return explicit;
            }

            public void setExplicit(boolean explicit) {
                this.explicit = explicit;
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

            public String getPreview_url() {
                return preview_url;
            }

            public void setPreview_url(String preview_url) {
                this.preview_url = preview_url;
            }

            public int getTrack_number() {
                return track_number;
            }

            public void setTrack_number(int track_number) {
                this.track_number = track_number;
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
                 * spotify : https://open.spotify.com/artist/2WX2uTcsvV5OnS0inACecP
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
        }
    }

    public static class ArtistsBean {
        /**
         * spotify : https://open.spotify.com/artist/2WX2uTcsvV5OnS0inACecP
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

    public static class CopyrightsBean {
        private String text;
        private String type;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
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
