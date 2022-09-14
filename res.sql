CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT);
INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '3251ce971a8cebeb2327014049a9488e')
CREATE TABLE IF NOT EXISTS Videos (`videoUriString` TEXT NOT NULL, `subsUriString` TEXT, `duration` INTEGER NOT NULL, `seekPosition` INTEGER NOT NULL, `opened` INTEGER NOT NULL, `seriesTitle` TEXT NOT NULL, `name` TEXT NOT NULL, `folderName` TEXT NOT NULL, PRIMARY KEY(`videoUriString`));
CREATE TABLE IF NOT EXISTS Sentences (`sentence` TEXT NOT NULL, `timestamp` INTEGER NOT NULL, `language2LC` TEXT, `videoUriString` TEXT, `timeStartSeconds` INTEGER, `timeEndSeconds` INTEGER, `audioUriString` TEXT, `canExtractAudio` INTEGER NOT NULL DEFAULT 1, `translation` TEXT, PRIMARY KEY(`sentence`));
CREATE TABLE IF NOT EXISTS Words (`word` TEXT NOT NULL, `timestamp` INTEGER NOT NULL, `language2LC` TEXT, `translation` TEXT, `repeated` INTEGER NOT NULL, `repeatedTotalTimes` INTEGER NOT NULL, `timeToRepeat` INTEGER NOT NULL, `repeatStage` INTEGER NOT NULL, `videoId` TEXT, PRIMARY KEY(`word`));
CREATE TABLE IF NOT EXISTS SearchHistory (`timestamp` INTEGER NOT NULL, `query` TEXT NOT NULL, PRIMARY KEY(`query`));
CREATE TABLE IF NOT EXISTS YouTubeVideos (`id` TEXT NOT NULL, `subsUriString` TEXT, `title` TEXT NOT NULL, `imageUrl` TEXT NOT NULL, `channelId` TEXT, `channelTitle` TEXT NOT NULL, `language` TEXT NOT NULL, `durationMs` INTEGER NOT NULL, `seekPosition` INTEGER NOT NULL, `opened` INTEGER NOT NULL, `publishedAt` INTEGER NOT NULL, `query` TEXT NOT NULL, `level` INTEGER, `autogenerated` INTEGER, PRIMARY KEY(`id`));
CREATE TABLE IF NOT EXISTS WatchedVideos (`id` TEXT NOT NULL, PRIMARY KEY(`id`));
CREATE TABLE IF NOT EXISTS ServiceJob (`taskId` INTEGER NOT NULL, `serviceId` INTEGER NOT NULL, `status` INTEGER NOT NULL, `code` INTEGER NOT NULL, PRIMARY KEY(`taskId`));
CREATE TABLE IF NOT EXISTS TopWords (`word` TEXT NOT NULL, `language` TEXT NOT NULL, `meaningEnglish` TEXT NOT NULL, `inDictionary` INTEGER NOT NULL, PRIMARY KEY(`word`));
CREATE TABLE IF NOT EXISTS Subscriptions (`feedId` TEXT NOT NULL, PRIMARY KEY(`feedId`), FOREIGN KEY(`feedId`) REFERENCES `Feeds`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE );
CREATE TABLE IF NOT EXISTS Feeds (`id` TEXT NOT NULL, `type` TEXT NOT NULL, `title` TEXT NOT NULL, `language` TEXT, PRIMARY KEY(`id`));
CREATE TABLE IF NOT EXISTS FeedYouTubeVideosRelations (`id` TEXT NOT NULL, `feedId` TEXT NOT NULL, `videoId` TEXT NOT NULL, PRIMARY KEY(`id`), FOREIGN KEY(`feedId`) REFERENCES `Feeds`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`videoId`) REFERENCES `YouTubeVideos`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE );
CREATE TABLE IF NOT EXISTS ExploreFeeds (`feedId` TEXT NOT NULL, PRIMARY KEY(`feedId`), FOREIGN KEY(`feedId`) REFERENCES `Feeds`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE );
CREATE TABLE IF NOT EXISTS Notifications (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `title` TEXT NOT NULL, `body` TEXT NOT NULL, `iconId` INTEGER NOT NULL, `image` TEXT NOT NULL, `uri` TEXT NOT NULL, `type` INTEGER NOT NULL, `timestamp` INTEGER NOT NULL);
CREATE TABLE IF NOT EXISTS FeedPages (`id` TEXT NOT NULL, `latestVideoId` TEXT NOT NULL, PRIMARY KEY(`id`));
CREATE TABLE IF NOT EXISTS FavouriteVideos (`videoId` TEXT NOT NULL, PRIMARY KEY(`videoId`));
CREATE TABLE IF NOT EXISTS Playlists (`playlistId` TEXT NOT NULL, `imageUrl` TEXT NOT NULL, `videosCount` INTEGER NOT NULL, `title` TEXT NOT NULL, `subtitle` TEXT NOT NULL, `language` TEXT NOT NULL, `timestamp` INTEGER NOT NULL, PRIMARY KEY(`playlistId`));
CREATE TABLE IF NOT EXISTS PlaylistVideos (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `playlistId` TEXT NOT NULL, `videoId` TEXT NOT NULL);
CREATE TABLE IF NOT EXISTS WordUsages (`word` TEXT NOT NULL, `sentence` TEXT NOT NULL, PRIMARY KEY(`word`), FOREIGN KEY(`word`) REFERENCES `Words`(`word`) ON UPDATE NO ACTION ON DELETE CASCADE );
CREATE TABLE IF NOT EXISTS DailyWordsLearned (`date` TEXT NOT NULL, `wordsCount` INTEGER NOT NULL, PRIMARY KEY(`date`))