-- Script that drops all tables (if they exist)
-- then creates the schema.

USE `museo`;

SET FOREIGN_KEY_CHECKS = 0; -- disable foreign key checks while we drop everything
DROP TABLE IF EXISTS `advertisement_click`;
DROP TABLE IF EXISTS `advertisement`;
DROP TABLE IF EXISTS `artist_concert_mapping`;
DROP TABLE IF EXISTS `artist_genre_mapping`;
DROP TABLE IF EXISTS `album_genre_mapping`;
DROP TABLE IF EXISTS `concert`;
DROP TABLE IF EXISTS `venue`;
DROP TABLE IF EXISTS `user_listening_history`;
DROP TABLE IF EXISTS `user_favorite_songs`;
DROP TABLE IF EXISTS `user_artist_following`;
DROP TABLE IF EXISTS `user_playlist_following`;
DROP TABLE IF EXISTS `song_playlist_mapping`;
DROP TABLE IF EXISTS `playlist`;
DROP TABLE IF EXISTS `song_album_mapping`;
DROP TABLE IF EXISTS `album_artist_mapping`;
DROP TABLE IF EXISTS `song`;
DROP TABLE IF EXISTS `genre`;
DROP TABLE IF EXISTS `album`;
DROP TABLE IF EXISTS `artist`;
DROP TABLE IF EXISTS `following`;
DROP TABLE IF EXISTS `user_payment_info`;
DROP TABLE IF EXISTS `user_role`;
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `geo_location`;
DROP TABLE IF EXISTS `role`;
DROP TABLE IF EXISTS `file`;
DROP TABLE IF EXISTS `mime_type`;
SET FOREIGN_KEY_CHECKS = 1; -- re enable foreign key checks


CREATE TABLE `mime_type` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

CREATE TABLE `file` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
--  `file_name` varchar(255) NOT NULL,
  `bytes` BLOB NOT NULL,
  `mime_type_id` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (mime_type_id) REFERENCES mime_type(id)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

CREATE TABLE `role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `role` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

CREATE TABLE `geo_location` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `latitude` DECIMAL(10,7) NOT NULL,
  `longitude` DECIMAL(10,7) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `profile_image_id` int(11) unsigned,
  `geo_location_id` int(11) unsigned,
  PRIMARY KEY (`id`),
  FOREIGN KEY (geo_location_id) REFERENCES geo_location(id),
  FOREIGN KEY (profile_image_id) REFERENCES file(id)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

CREATE TABLE `user_role` (
  `user_id` int(11) unsigned NOT NULL,
  `role_id` int(11) unsigned NOT NULL,
  FOREIGN KEY (user_id) REFERENCES user(id),
  FOREIGN KEY (role_id) REFERENCES role(id)
) DEFAULT CHARSET=utf8;

CREATE TABLE `user_payment_info` (
  `user_id` int(11) unsigned NOT NULL,
  `card_number` varchar(255) NOT NULL,
  `name_on_card` varchar(255) NOT NULL,
  `ccv` int(11) unsigned NOT NULL,
  `expiration_date` DATE NOT NULL,
  FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE
) DEFAULT CHARSET=utf8;

CREATE TABLE `following` (
  `follower` int(11) unsigned NOT NULL,
  `user_being_followed` int(11) unsigned NOT NULL,
  FOREIGN KEY (follower) REFERENCES user(id),
  FOREIGN KEY (user_being_followed) REFERENCES user(id)
) DEFAULT CHARSET=utf8;

CREATE TABLE `artist` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `mbid` char(36),
  `name` varchar(255) NOT NULL,
  `bio` text,
  `website` varchar(255),
  `artist_art_id` int(11) unsigned,
  `artist_art_thumbnail_id` int(11) unsigned,
  `artist_art_background_id` int(11) unsigned,
  `music_label_user_id` int(11) unsigned,
  `geo_location_id` int(11) unsigned,
  FOREIGN KEY (music_label_user_id) REFERENCES user(id),
  FOREIGN KEY (geo_location_id) REFERENCES geo_location(id),
  FOREIGN KEY (artist_art_id) REFERENCES file(id),
  FOREIGN KEY (artist_art_thumbnail_id) REFERENCES file(id),
  FOREIGN KEY (artist_art_background_id) REFERENCES file(id),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

CREATE TABLE `album` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `mbid` char(36),
  `title` varchar(255) NOT NULL,
--  `artist_id` int(11) unsigned NOT NULL,
  `album_art_id` int(11) unsigned,
  `album_art_thumbnail_id` int(11) unsigned,
  `release_date` DATE,
  PRIMARY KEY (`id`),
--  FOREIGN KEY (artist_id) REFERENCES artist(id),
  FOREIGN KEY (album_art_id) REFERENCES file(id),
  FOREIGN KEY (album_art_thumbnail_id) REFERENCES file(id)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

CREATE TABLE `song` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `mbid` char(36),
  `title` varchar(255) NOT NULL,
  `artist_id` int(11) unsigned NOT NULL,
--  `album_id` int(11) unsigned NOT NULL,
  `low_bitrate_file_id` int(11) unsigned,
  `high_bitrate_file_id` int(11) unsigned,
  `duration` int(11) DEFAULT NULL,
  `lyrics` varchar(4000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (artist_id) REFERENCES artist(id) ON DELETE CASCADE,
--  FOREIGN KEY (album_id) REFERENCES album(id) ON DELETE CASCADE,
  FOREIGN KEY (low_bitrate_file_id) REFERENCES file(id) ON DELETE CASCADE,
  FOREIGN KEY (high_bitrate_file_id) REFERENCES file(id) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

CREATE TABLE `genre` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

CREATE TABLE `artist_genre_mapping` (
  `genre_id` int(11) unsigned NOT NULL,
  `artist_id` int(11) unsigned NOT NULL,
  PRIMARY KEY (`genre_id`, `artist_id`),
  FOREIGN KEY (genre_id) REFERENCES genre(id),
  FOREIGN KEY (artist_id) REFERENCES artist(id)
) DEFAULT CHARSET=utf8;

CREATE TABLE `album_genre_mapping` (
  `genre_id` int(11) unsigned NOT NULL,
  `album_id` int(11) unsigned NOT NULL,
  PRIMARY KEY (`genre_id`, `album_id`),
  FOREIGN KEY (genre_id) REFERENCES genre(id),
  FOREIGN KEY (album_id) REFERENCES album(id)
) DEFAULT CHARSET=utf8;

CREATE TABLE `song_album_mapping` (
  `song_id` int(11) unsigned NOT NULL,
  `album_id` int(11) unsigned NOT NULL,
  `track_number` int(2) unsigned,
  PRIMARY KEY (`song_id`, `album_id`),
  FOREIGN KEY (song_id) REFERENCES song(id),
  FOREIGN KEY (album_id) REFERENCES album(id)
) DEFAULT CHARSET=utf8;

CREATE TABLE `album_artist_mapping` (
  `album_id` int(11) unsigned NOT NULL,
  `artist_id` int(11) unsigned NOT NULL,
  PRIMARY KEY (`album_id`, `artist_id`),
  FOREIGN KEY (album_id) REFERENCES album(id),
  FOREIGN KEY (artist_id) REFERENCES artist(id)
) DEFAULT CHARSET=utf8;

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

CREATE TABLE `song_playlist_mapping` (
  `song_id` int(11) unsigned NOT NULL,
  `playlist_id` int(11) unsigned NOT NULL,
  PRIMARY KEY (`song_id`, `playlist_id`),
  FOREIGN KEY (song_id) REFERENCES song(id),
  FOREIGN KEY (playlist_id) REFERENCES playlist(id)
) DEFAULT CHARSET=utf8;

CREATE TABLE `user_listening_history` (
  `user_id` int(11) unsigned NOT NULL,
  `song_id` int(11) unsigned NOT NULL,
  `timestamp` TIMESTAMP NOT NULL,
  FOREIGN KEY (user_id) REFERENCES user(id),
  FOREIGN KEY (song_id) REFERENCES song(id)
) DEFAULT CHARSET=utf8;

-- treat following and favorites as the same thing?
CREATE TABLE `user_playlist_following` (
  `user_id` int(11) unsigned NOT NULL,
  `playlist_id` int(11) unsigned NOT NULL,
  `time` TIMESTAMP NOT NULL,
  FOREIGN KEY (user_id) REFERENCES user(id),
  FOREIGN KEY (playlist_id) REFERENCES playlist(id)
) DEFAULT CHARSET=utf8;

CREATE TABLE `user_artist_following` (
  `user_id` int(11) unsigned NOT NULL,
  `artist_id` int(11) unsigned NOT NULL,
  `timestamp` TIMESTAMP NOT NULL,
  FOREIGN KEY (user_id) REFERENCES user(id),
  FOREIGN KEY (artist_id) REFERENCES artist(id)
) DEFAULT CHARSET=utf8;

CREATE TABLE `user_favorite_songs` (
  `user_id` int(11) unsigned NOT NULL,
  `song_id` int(11) unsigned NOT NULL,
  `timestamp` TIMESTAMP NOT NULL,
  FOREIGN KEY (user_id) REFERENCES user(id),
  FOREIGN KEY (song_id) REFERENCES song(id)
) DEFAULT CHARSET=utf8;

CREATE TABLE `venue` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `location_id` int(11) unsigned NOT NULL,
  FOREIGN KEY (location_id) REFERENCES geo_location(id),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

CREATE TABLE `concert` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `venue_id` int(11) unsigned NOT NULL,
  `date` DATETIME NOT NULL,
  FOREIGN KEY (venue_id) REFERENCES venue(id),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
 
CREATE TABLE `artist_concert_mapping` (
  `artist_id` int(11) unsigned NOT NULL,
  `concert_id` int(11) unsigned NOT NULL,
  FOREIGN KEY (artist_id) REFERENCES artist(id),
  FOREIGN KEY (concert_id) REFERENCES concert(id)
) DEFAULT CHARSET=utf8;

CREATE TABLE `advertisement` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `desription` varchar(4000) NOT NULL,
  `file` int(11) unsigned NOT NULL,
  FOREIGN KEY (file) REFERENCES file(id),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

CREATE TABLE `advertisement_click` (
  `advertisement_id` int(11) unsigned NOT NULL,
  `user_id` int(11) unsigned NOT NULL,
  `timestamp` TIMESTAMP NOT NULL,
  FOREIGN KEY (advertisement_id) REFERENCES advertisement(id),
  FOREIGN KEY (user_id) REFERENCES user(id)
) DEFAULT CHARSET=utf8;

commit;

INSERT INTO `role` VALUES (1,'ADMIN');
INSERT INTO `role` VALUES (2,'BASIC_USER');
INSERT INTO `role` VALUES (3,'PREMIUM_USER');
INSERT INTO `role` VALUES (4,'LABEL_USER');

commit;