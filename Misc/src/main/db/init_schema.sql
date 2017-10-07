DROP DATABASE IF EXISTS `webspotify`;
CREATE DATABASE `webspotify`;
USE `webspotify`;

DROP TABLE IF EXISTS `user_type`;
CREATE TABLE `user_type` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_type_id` int(11) unsigned,
  `username` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (user_type_id) REFERENCES user_type(id)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `user_payment_info`;
CREATE TABLE `user_payment_info` (
  `user_id` int(11) unsigned NOT NULL,
  `card_number` varchar(255) NOT NULL,
  `name_on_card` varchar(255) NOT NULL,
  `ccv` int(11) unsigned NOT NULL,
  `expiration_date` DATE NOT NULL,
  FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE
) DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `friends`;
CREATE TABLE `friends` (
  `user_id_1` int(11) unsigned NOT NULL,
  `user_id_2` int(11) unsigned NOT NULL,
  FOREIGN KEY (user_id_1) REFERENCES user(id),
  FOREIGN KEY (user_id_2) REFERENCES user(id)
) DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `artist`;
CREATE TABLE `artist` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `bio` varchar(4000),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `album`;
CREATE TABLE `album` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `artist_id` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (artist_id) REFERENCES artist(id)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `mime_type`;
CREATE TABLE `mime_type` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `file`;
CREATE TABLE `file` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `file_name` varchar(255) NOT NULL,
  `bytes` BLOB NOT NULL,
  `mime_type_id` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (mime_type_id) REFERENCES mime_type(id)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `genre`;
CREATE TABLE `genre` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `song`;
CREATE TABLE `song` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `genre_id` int(11) unsigned NOT NULL,
  `album_id` int(11) unsigned NOT NULL,
  `file_id` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (genre_id) REFERENCES genre(id) ON DELETE CASCADE,
  FOREIGN KEY (album_id) REFERENCES album(id) ON DELETE CASCADE,
  FOREIGN KEY (file_id) REFERENCES file(id) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `song_album_mapping`;
CREATE TABLE `song_album_mapping` (
  `song_id` int(11) unsigned NOT NULL,
  `album_id` int(11) unsigned NOT NULL,
  PRIMARY KEY (`song_id`, `album_id`),
  FOREIGN KEY (song_id) REFERENCES song(id),
  FOREIGN KEY (album_id) REFERENCES album(id)
) DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `playlist`;
CREATE TABLE `playlist` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `owner_id` int(11) unsigned NOT NULL,
  `is_private` tinyint(1) DEFAULT 0,
  `is_collaborative` tinyint(1) DEFAULT 0,
  `num_songs` int(11) NOT NULL DEFAULT 0,
  FOREIGN KEY (owner_id) REFERENCES user(id),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `song_playlist_mapping`;
CREATE TABLE `song_playlist_mapping` (
  `song_id` int(11) unsigned NOT NULL,
  `playlist_id` int(11) unsigned NOT NULL,
  PRIMARY KEY (`song_id`, `playlist_id`),
  FOREIGN KEY (song_id) REFERENCES song(id),
  FOREIGN KEY (playlist_id) REFERENCES playlist(id)
) DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `user_listening_history`;
CREATE TABLE `user_listening_history` (
  `user_id` int(11) unsigned NOT NULL,
  `song_id` int(11) unsigned NOT NULL,
  `time` TIMESTAMP NOT NULL,
  FOREIGN KEY (user_id) REFERENCES user(id),
  FOREIGN KEY (song_id) REFERENCES song(id)
) DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `geo_location`; -- I think we are going to want a better solution
CREATE TABLE `geo_location` (        -- this will do for now.
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `latitude` DECIMAL(10,7) NOT NULL,
  `longitude` DECIMAL(10,7) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `venue`;
CREATE TABLE `venue` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `location_id` int(11) unsigned NOT NULL,
  FOREIGN KEY (location_id) REFERENCES geo_location(id),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `concert`;
CREATE TABLE `concert` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `venue_id` int(11) unsigned NOT NULL,
  `date` DATETIME NOT NULL,
  FOREIGN KEY (venue_id) REFERENCES venue(id),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
 
DROP TABLE IF EXISTS `artist_concert_mapping`;
CREATE TABLE `artist_concert_mapping` (
  `artist_id` int(11) unsigned NOT NULL,
  `concert_id` int(11) unsigned NOT NULL,
  PRIMARY KEY (`artist_id`, `concert_id`),
  FOREIGN KEY (artist_id) REFERENCES artist(id),
  FOREIGN KEY (concert_id) REFERENCES concert(id)
) DEFAULT CHARSET=utf8;

commit;
