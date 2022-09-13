package com.example.weatherapp.model


data class City(
    val name: String?,
    val imageURLS: ImageURLs,
    val population: Int?,
    val country_code: String?,
    val androidImageURLs: AndroidImageURLs?,
    val timezone: String?,
    val elevation: String?
)
/*

data class City(
    @SerializedName("admin1 code")
    var admin1Code: String?, // AL
    @SerializedName("admin2 code")
    var admin2Code: Int?, // 117
    @SerializedName("admin3 code")
    var admin3Code: Int?, // 21504
    @SerializedName("admin4 code")
    var admin4Code: String?,
    var alternatenames: String?, // Buxahatchie,Calera,Kalera,Lime Kiln,Lime Plant,Lime Station,ka lai la,kalra,kalyra,kyalera,Калера,كاليرا,کالرا,क्यालेरा,卡莱拉
    var asciiname: String?, // Calera
    var cc2: String?,
    @SerializedName("country code")
    var countryCode: String?, // US
    var dem: Int?, // 156
    var elevation: Int?, // 154
    @SerializedName("country class")
    var featureClass: String?, // P
    @SerializedName("feature code")
    var featureCode: String?, // PPL
    var geonameid: Int?, // 4053050
    var imageURLs: ImageURLs?,
    var latitude: Double?, // 33.1029
    var longitude: Double?, // -86.7536
    @SerializedName("modification date")
    var modificationDate: String?, // 3/9/17
    var name: String?, // Calera
    var population: Int?, // 13213
    var timezone: String? // America/Chicago
)*/
