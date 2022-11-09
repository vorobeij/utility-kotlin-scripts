object Sentences : Table() {

    val text: Column<String> = varchar("text", 200)
    override val primaryKey = PrimaryKey(text)
    val timestamp: Column<Long> = long("timestamp")
    val sourceLanguage: Column<String> = varchar("sourceLanguage", 200)
    val videoUriString: Column<String> = varchar("videoUriString", 200)
    val timeStartSeconds: Column<Long> = long("timeStartSeconds")
    val timeEndSeconds: Column<Long> = long("timeEndSeconds")
    val audioUriString: Column<String> = varchar("audioUriString", 200)
    val canExtractAudio: Column<Long> = long("canExtractAudio")
    val translation: Column<String> = varchar("translation", 200)
}

object Words : Table() {

    val text: Column<String> = varchar("text", 200)
    override val primaryKey = PrimaryKey(text)
    val timestamp: Column<Long> = long("timestamp")
    val sourceLanguage: Column<String> = varchar("sourceLanguage", 200)
    val translation: Column<String> = varchar("translation", 200)
    val repeated: Column<Long> = long("repeated")
    val repeatedTotalTimes: Column<Long> = long("repeatedTotalTimes")
    val timeToRepeat: Column<Long> = long("timeToRepeat")
    val repeatStage: Column<Long> = long("repeatStage")
    val videoId: Column<String> = varchar("videoId", 200)
}

object SearchHistory : Table() {

    val query: Column<String> = varchar("query", 200)
    override val primaryKey = PrimaryKey(query)
    val timestamp: Column<Long> = long("timestamp")
}

object YouTubeVideo : Table() {

    val id: Column<String> = varchar("id", 200)
    override val primaryKey = PrimaryKey(id)
    val title: Column<String> = varchar("title", 200)
    val description: Column<String> = varchar("description", 200)
    val channelTitle: Column<String> = varchar("channelTitle", 200)
    val subsUriString: Column<String> = varchar("subsUriString", 200)
    val previewUrl: Column<String> = varchar("previewUrl", 200)
    val channelId: Column<String> = varchar("channelId", 200)
    val sourceLang: Column<String> = varchar("sourceLang", 200)
    val durationMs: Column<Long> = long("durationMs")
    val openedAtTs: Column<Long> = long("openedAtTs")
    val publishedAtTs: Column<Long> = long("publishedAtTs")
    val difficulty: Column<Long> = long("difficulty")
    val autogeneratedSubtitles: Column<Boolean> = bool("autogeneratedSubtitles")
    val banned: Column<Boolean> = bool("banned")
    val reportedTimes: Column<Long> = long("reportedTimes")
    val requiresModeration: Column<Boolean> = bool("requiresModeration")
}

object VideoMeta : Table() {

    val videoId: Column<String> = varchar("videoId", 200)
    override val primaryKey = PrimaryKey(videoId)
    val searchTokens: null
}

object Subtitles : Table() {

    val videoId: Column<String> = varchar("videoId", 200)
    override val primaryKey = PrimaryKey(videoId)
    val subtitles: Column<String> = varchar("subtitles", 200)
}

object Topics : Table() {

    val name: Column<String> = varchar("name", 200)
    override val primaryKey = PrimaryKey(name)
}

object VideoTopic : Table() {

    val id: Column<Long> = long("id")
    override val primaryKey = PrimaryKey(id)
    val videoId: Column<String> = varchar("videoId", 200)
    val topicId: Column<String> = varchar("topicId", 200)
}

object TopicTranslation : Table() {

    val id: Column<Long> = long("id")
    override val primaryKey = PrimaryKey(id)
    val topicId: Column<String> = varchar("topicId", 200)
    val language: Column<String> = varchar("language", 200)
    val name: Column<String> = varchar("name", 200)
}

object UserSettings : Table() {

    val userId: Column<String> = varchar("userId", 200)
    override val primaryKey = PrimaryKey(userId)
    val lanOrig: Column<Long> = long("lanOrig")
    val lanTran: Column<Long> = long("lanTran")
}

object UserTopics : Table() {

    val id: null
    override val primaryKey = PrimaryKey(id)
    val userId: Column<String> = varchar("userId", 200)
    val topicId: Column<String> = varchar("topicId", 200)
}

object User : Table() {

    val id: Column<String> = varchar("id", 200)
    override val primaryKey = PrimaryKey(id)
    val name: Column<String> = varchar("name", 200)
    val registerTs: Column<Long> = long("registerTs")
}

object UserWatchHistory : Table() {

    val id: Column<Long> = long("id")
    override val primaryKey = PrimaryKey(id)
    val userId: Column<String> = varchar("userId", 200)
    val videoId: Column<String> = varchar("videoId", 200)
    val watchedTs: Column<Long> = long("watchedTs")
    val seekPositionPercent: Column<Long> = long("seekPositionPercent")
    val saved: Column<Boolean> = bool("saved")
}

object RecommendLess : Table() {

    val id: Column<Long> = long("id")
    override val primaryKey = PrimaryKey(id)
    val userId: Column<String> = varchar("userId", 200)
    val videoId: Column<String> = varchar("videoId", 200)
}

object Language : Table() {

    val id: Column<Long> = long("id")
    override val primaryKey = PrimaryKey(id)
    val displayName: Column<String> = varchar("displayName", 200)
    val source: Column<Boolean> = bool("source")
    val translate: Column<Boolean> = bool("translate")
}

object Playlist : Table() {

    val id: Column<String> = varchar("id", 200)
    override val primaryKey = PrimaryKey(id)
    val imageUrl: Column<String> = varchar("imageUrl", 200)
    val videosCount: Column<Long> = long("videosCount")
    val title: Column<String> = varchar("title", 200)
    val subtitle: Column<String> = varchar("subtitle", 200)
    val language: Column<String> = varchar("language", 200)
    val timestamp: Column<Long> = long("timestamp")
}

object PlaylistVideo : Table() {

    val playlistId: Column<String> = varchar("playlistId", 200)
    override val primaryKey = PrimaryKey(playlistId)
    val videoId: Column<String> = varchar("videoId", 200)
}

object WordUsages : Table() {

    val word: Column<String> = varchar("word", 200)
    override val primaryKey = PrimaryKey(word)
    val sentence: Column<String> = varchar("sentence", 200)
}

object DailyWordsLearned : Table() {

    val date: Column<String> = varchar("date", 200)
    override val primaryKey = PrimaryKey(date)
    val wordsCount: Column<Long> = long("wordsCount")
}