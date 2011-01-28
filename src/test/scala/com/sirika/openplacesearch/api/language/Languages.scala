package com.sirika.openplacesearch.api.language

object Languages {
    def french(): Language = Language(name = "French", alpha3Code = "fra", alpha2Code = Some("fr"))
    def english(): Language = Language(name = "English", alpha3Code = "eng", alpha2Code = Some("en"))
    def spanish(): Language = Language(name = "Spanish", alpha3Code = "spa", alpha2Code = Some("es"))
    def hawaiian(): Language = Language(name = "Hawaiian", alpha3Code = "haw")
    def guotuo(): Language = Language(name = "Ghotuo", alpha3Code = "aaa")
}