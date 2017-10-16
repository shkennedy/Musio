
 USE `cse308`;



-- DROP TABLE IF EXISTS Artist;
CREATE TABLE Artist (
    ID char(36),
    Name varchar(255) NOT NULL,
    Bio TEXT,
    Artist_Art blob,
    Artist_Art_Thumbnail blob,
    Artist_Art_Background blob,
    PRIMARY KEY (`ID`)
);


-- DROP TABLE IF EXISTS Album;
CREATE TABLE Album (
    ID char(36),
    Title varchar(255) NOT NULL,
    Artist_ID char(36) NOT NULL,
    Album_Art blob,
    Album_Art_Thumbnail blob,
    PRIMARY KEY (ID),
    FOREIGN KEY (Artist_ID) REFERENCES Artist(ID)
);



-- DROP TABLE IF EXISTS Song;
CREATE TABLE Song (
    ID char(36) NOT NULL,
    Title varchar(255) NOT NULL,
    Artist_ID char(36) NOT NULL,
    Album_ID char(36) NOT NULL,
    Lyrics TEXT,
    Low_Bitrate BLOB,
    High_Bitrate BLOB,
    PRIMARY KEY (`ID`),
    FOREIGN KEY (Artist_ID) REFERENCES Artist(ID),
    FOREIGN KEY (Album_ID) REFERENCES Album(ID)
);




-- DROP TABLE IF EXISTS Genre;
CREATE TABLE Genre (
    ID int(11) unsigned NOT NULL AUTO_INCREMENT,
    Name VARCHAR(255) NOT NULL,
    Description TEXT,
    PRIMARY KEY (ID)
);

-- DROP TABLE IF EXISTS Genre_Artist_Mapping;
CREATE TABLE Genre_Artist_Mapping (
    Artist_ID char(36) NOT NULL,
    Genre_ID int(11) unsigned NOT NULL,
    PRIMARY KEY (Artist_ID, Genre_ID),
    FOREIGN KEY (Artist_ID) REFERENCES Artist(ID),
    FOREIGN KEY (Genre_ID) REFERENCES Genre(ID)
);

-- DROP TABLE IF EXISTS Genre_Album_Mapping;
CREATE TABLE Genre_Album_Mapping (
    Album_ID char(36) NOT NULL,
    Genre_ID int(11) unsigned NOT NULL,
    FOREIGN KEY (Album_ID) REFERENCES Album(ID),
    FOREIGN KEY (Genre_ID) REFERENCES Genre(ID)
);

-- DROP TABLE IF EXISTS Genre_Song_Mapping;
CREATE TABLE Genre_Song_Mapping (
    Song_ID char(36) NOT NULL,
    Genre_ID int(11) unsigned NOT NULL,
    FOREIGN KEY (Song_ID) REFERENCES Song(ID),
    FOREIGN KEY (Genre_ID) REFERENCES Genre(ID)
);




-- DROP TABLE IF EXISTS `Song_Album_Mapping`;
CREATE TABLE Song_Album_Mapping (
    Song_ID char(36) NOT NULL,
    Album_ID char(36) NOT NULL,
    TrackNumber int(2), 
    PRIMARY KEY (Song_ID, Album_ID),
    FOREIGN KEY (Song_ID) REFERENCES Song(ID),
    FOREIGN KEY (Album_ID) REFERENCES Album(ID)
);





