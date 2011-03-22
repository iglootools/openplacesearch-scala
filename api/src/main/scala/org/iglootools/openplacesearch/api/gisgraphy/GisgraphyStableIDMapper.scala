package org.iglootools.openplacesearch.api.gisgraphy;

class GisgraphyStableIDMapper extends StableIDMapper {
  private val Mapping: Map[String,String] =
    Map(
      // Andorra
      "3041563" -> "andorra-la-vella",
      // United Arab Emirates
      "292968" -> "abu-dhabi",
      // Afghanistan
      "1138958" -> "kabul",
      // Antigua and Barbuda
      "3576022" -> "saint-johns",
      // Anguilla
      "3573374" -> "the-valley",
      // Albania
      "3183875" -> "tirana",
      // Armenia
      "616052" -> "yerevan",
      // Angola
      "2240449" -> "luanda",
      // Argentina
      "3435910" -> "buenos-aires",
      // American Samoa
      "5881576" -> "pago-pago",
      // Austria
      "2761369" -> "vienna",
      // Australia
      "2172517" -> "canberra",
      // Aruba
      "3577154" -> "oranjestad",
      // Aland Islands
      "3041732" -> "mariehamn",
      // Azerbaijan
      "587084" -> "baku",
      // Bosnia and Herzegovina
      "3191281" -> "sarajevo",
      // Barbados
      "3374036" -> "bridgetown",
      // Bangladesh
      "1185241" -> "dhaka",
      // Belgium
      "2800866" -> "brussels",
      // Burkina Faso
      "2357048" -> "ouagadougou",
      // Bulgaria
      "727011" -> "sofia",
      // Bahrain
      "290340" -> "manama",
      // Burundi
      "425378" -> "bujumbura",
      // Benin
      "2392087" -> "porto-novo",
      // Saint Barthélemy
      "3579132" -> "gustavia",
      // Bermuda
      "2190324" -> "hamilton",
      // Brunei
      "1820906" -> "bandar-seri-begawan",
      // Bolivia
      "3911925" -> "la-paz",
      // Bonaire, Saint Eustatius and Saba
      // ??
      // Brazil
      "3469058" -> "brasilia",
      // Bahamas
      "3571824" -> "nassau",
      // Bhutan
      "1252416" -> "thimphu",
      // Bouvet Island
      // Botswana
      "933773" -> "gaborone",
      // Belarus
      "625144" -> "minsk",
      // Belize
      "3582672" -> "belmopan",
      // Canada
      // Canada-AB
      "5892532" -> "banff-AB-CA",
      "5914894" -> "canmore",
      "5913490" -> "calgary",
      "5946768" -> "edmonton",
      "6155033" -> "st-albert-AB-CA",
      "6157989" -> "strathcona",
      "5955895" -> "fort-mcmurray",
      "5964347" -> "grande-prairie",
      "6053154" -> "lethbridge-AB-CA",
      "6058024" -> "lloydminster",
      "6071618" -> "medicine-hat",
      "6118158" -> "red-deer",
      // Canada-BC
      "5931800" -> "cranbrook-BC-CA",
      "5881791" -> "abbotsford",
      "5921357" -> "chilliwack",
      "6049429" -> "langley",
      "5989045" -> "kamloops",
      "5990579" -> "kelowna",
      "6101141" -> "penticton",
      "6085772" -> "nanaimo",
      "6086871" -> "nelson-BC-CA",
      "6113365" -> "prince-george",
      "6173331" -> "vancouver",
      "6173864" -> "vernon-BC-CA",
      "6174041" -> "victoria-BC-CA",
      "6180144" -> "whistler",
      // Canada-PE
      "5920288" -> "charlottetown",
      "6159244" -> "summerside-PE-CA",
      // Canada-MB
      "5907896" -> "brandon-MB-CA",
      "6111529" -> "portage-la-prairie",
      "5954718" -> "flin-flon",
      "6165406" -> "thompson-MB-CA",
      "6183235" -> "winnipeg",
      // Canada-NB
      "6696259" -> "bathurst-NB-CA",
      "6545023" -> "edmundston",
      "5957776" -> "fredericton-NB-CA",
      "6075080" -> "miramichi-NB-CA",
      "6076211" -> "moncton",
      "6138517" -> "saint-john-NB-CA",
      // Canada-NS
      "5908723" -> "bridgewater-NS-CA",
      "6324729" -> "halifax",
      "6087579" -> "new-glasgow-NS-CA",
      "6169587" -> "truro-NS-CA",
      "6185217" -> "yarmouth-NS-CA",

      // Canada-ON
      "5894171" -> "barrie",
      "5897885" -> "belleville-ON-CA",
      "6168768" -> "trenton-ON-CA",
      "5907990" -> "brantford",
      "5909294" -> "brockville",
      "5920450" -> "chatham-kent-ON-CA",
      "5928065" -> "cornwall",
      "5967629" -> "guelph",
      "5969785" -> "hamilton",
      "5989403" -> "kapuskasing",
      "5991056" -> "kenora",
      "5992500" -> "kingston-ON-CA",
      "5965812" -> "greater-napanee",
      "5992996" -> "kitchener",
      "5913695" -> "cambridge-ON-CA",
      "6176823" -> "waterloo-ON-CA",
      "6157977" -> "stratford-ON-CA",
      "6051123" -> "leamington-ON-CA",
      "6058560" -> "london-ON-CA",
      "6089125" -> "norfolk-county",
      "6089426" -> "north-bay",
      "6094817" -> "ottawa",
      "6095645" -> "owen-sound",
      "6101645" -> "peterborough-ON-CA",
      "6100832" -> "pembroke",
      "6101607" -> "petawawa",
      "6119448" -> "renfrew-ON-CA",
      "6141190" -> "sarnia",
      "6141439" -> "sault-ste-marie-ON-CA",
      "6155721" -> "st-catharines",
      "5964700" -> "greater-sudbury",
      "6166142" -> "thunder-bay",
      "6166739" -> "timmins",
      "6167865" -> "toronto",
      "6066513" -> "markham-ON-CA",
      "6075357" -> "mississauga",
      "6092122" -> "oakville-ON-CA",
      "5969721" -> "halton-hills",
      "6094578" -> "oshawa",
      "6182962" -> "windsor",
      "6184365" -> "woodstock-ON-CA",
      // Canada-QC
      "5959974" -> "gatineau",
      "6128577" -> "rouyn-noranda",
      "6173017" -> "val-d-or-QC-CA",
      "5889745" -> "baie-comeau",
      "6325521" -> "levis",
      "6943827" -> "thetford-mines",
      "6620953" -> "st-georges-de-beauce",
      "5942845" -> "drummondville",
      "6174151" -> "victoriaville",
      "5964215" -> "granby-QC-CA",
      "6077243" -> "montreal",
      "6325494" -> "quebec",
      "6354895" -> "rimouski",
      "6137270" -> "saguenay",
      "6544161" -> "lac-saint-jean",
      "6138374" -> "saint-hyacinthe",
      "6138495" -> "saint-jean-sur-richelieu",
      "6144312" -> "sept-iles",
      "6146143" -> "sherbrooke",
      "6169141" -> "trois-rivieres",
      "6145489" -> "shawinigan",
      // Canada-SK
      "6050066" -> "la-ronge",
      "6071421" -> "meadow-lake-SK-CA",
      "6088469" -> "nipawin",
      "6113335" -> "prince-albert",
      "6078112" -> "moose-jaw",
      "6119109" -> "regina",
      "6141256" -> "saskatoon",
      "6160603" -> "swift-current",
      // Canada-NL
      "5927969" -> "corner-brook",
      "5959335" -> "ganger-NL-CA",
      "5994839" -> "labrador-city",
      "5970458" -> "goose-bay",
      "6324733" -> "st-johns-NL-CA",
      // Canada-NT
      "6185377" -> "yellowknife",
      // Canada-NU
      "5983720" -> "iqaluit",
      // Canada-YT
      "6180550" -> "whitehorse",
      // Cocos Islands
      "7304591" -> "west-island",
      // Democratic Republic of the Congo
      "2314302" -> "kinshasa",
      "2260535" -> "brazzaville",
      // Central African Republic
      "2389853" -> "bangui",
      // Switzerland
      "2661552" -> "berne",
      // Ivory Coast
      "2279755" -> "yamoussoukro",
      // Cook Islands
      "4035715" -> "avarua",
      // Chile
      "3871336" -> "santiago",
      // Cameroon
      "2220957" -> "yaounde",
      // China
      "1816670" -> "beijing",
      // Colombia
      "3688689" -> "bogota",
      // Costa Rica
      "3621849" -> "san-jose",
      // Cuba
      "3553478" -> "havana",
      // Cape Verde
      "3374333" -> "praia",
      // Curacao
      "3513090" -> "willemstad",
      // Christmas Island
      "2078127" -> "flying-fish-cove",
      // Cyprus
      "146268" -> "nicosia",
      // Czech Republic
      "3067696" -> "prague",
      // Germany
      "2950159" -> "berlin",
      // Djibouti
      "223817" -> "djibouti",
      // Denmark
      "2618425" -> "copenhagen",
      // Dominica
      "3575635" -> "roseau",
      // Dominican Republic
      "3492908" -> "santo-domingo",
      // Algeria
      "2507480" -> "algiers",
      // Ecuador
      "3652462" -> "quito",
      // Estonia
      "588409" -> "tallinn",
      // Egypt
      "360630" -> "cairo",
      // Western Sahara
      "2462881" -> "el-aaiun",
      // Eritrea
      "343300" -> "asmara",
      // Spain
      "3117735" -> "madrid",
      // Ethiopia
      "344979" -> "addis-ababa",
      // Finland
      "658225" -> "helsinki",
      // Fiji
      "2198148" -> "suva",
      // Falkland Islands
      "3426691" -> "stanley",
      // Micronesia
      "2081986" -> "palikir",
      // Faroe Islands
      "2611396" -> "torshavn",
      // France
      "2988507" -> "paris",
      // Gabon,
      "2399697" -> "libreville",
      // UK
      "2643743" -> "london", // clash with London, ON ?
      // Genada
      "3579925" -> "saint-georges",
      // Georgia
      "611717" -> "tbilisi",
      // French Guiana
      "3382160" -> "cayenne",
      // Guernsey
      "3042287" -> "saint-peter-port",
      // Guana
      "2306104" -> "accra",
      // Gibraltar
      "2411585" -> "gibraltar",
      // Greenland
      "3421319" -> "nuuk",
      // Gambia
      "2413876" -> "banjul",
      // Guinea
      "2422465" -> "conakry",
      // Guadeloupe
      "3579732" -> "basse-terre",
      // Equatorial Guinea
      "2309527" -> "malabo",
      // Greece
      "264371" -> "athens",
      // South Georgia and the South Sandwich Islands
      "3426466" -> "grytviken",
      // Guatemala City
      "3598132" -> "guatemala-city",
      // Guam
      "4044012" -> "hagatna",
      // Guinea-Bissau
      "2374775" -> "bissau",
      // Guyana
      "3378644" -> "georgetown",
      // Hong Kong
      "1819729" -> "hong-kong",
      // Heard Island and McDonald Islands
      // Honduras
      "3600949" -> "tegucigalpa",
      // Croatia
      "3186886" -> "zagreb",
      // Haiti
      "3718426" -> "port-au-prince",
      // Hungary
      "3054643" -> "budapest",
      // Indonesia
      "1642911" -> "jakarta",
      // Ireland
      "2964574" -> "dublin",
      // Israel
      "281184" -> "jerusalem",
      // Isle of Man
      "3042237" -> "douglas",
      // India
      "1261481" -> "new-delhi",
      // British Indian Ocean Territory
      // Iraq
      "98182" -> "baghdad",
      // Iran
      "112931" -> "tehran",
      // Iceland
      "3413829" -> "reykjavík",
      // Italy
      "3169070" -> "rome",
      // Jersey
      "3237864" -> "saint-helier",
      // Jamaica
      "3489854" -> "kingston-JM",
      // Jordan
      "250441" -> "amman",
      // Japan
      "1850147" -> "tokyo",
      // Kenya
      "184745" -> "nairobi",
      // Kyrgyzstan
      "1528675" -> "bishkek",
      // Cambodia
      "1821306" -> "phnom-penh",
      // Kiribati
      "2110257" -> "tarawa",
      // Comoros
      "921772" -> "moroni",
      // Saint Kitts and Nevis
      "3575551" -> "basseterre",
      // North Korea
      "1871859" -> "pyongyang",
      // South Korea
      "1835848" -> "seoul",
      // Kosovo
      "786714" -> "pristina",
      // Kuwait
      "285787" -> "kuwait-city",
      // Cayman Islands
      "1735106" -> "george-town",
      // Kazakhstan
      "1526273" -> "astana",
      // Laos
      "1651944" -> "vientiane",
      // Lebanon
      "276781" -> "beirut",
      // Saint Lucia
      "3576812" -> "castries",
      // Liechtenstein
      "3042030" -> "vaduz",
      // Sri Lanka
      "1248991" -> "colombo",
      // Liberia
      "2274895" -> "monrovia",
      // Lesotho
      "932505" -> "maseru",
      // Lithuania
      "593116" -> "vilnius",
      // Luxembourg
      "2960316" -> "luxembourg",
      // Latvia
      "456172" -> "riga",
      // Libya
      "2210247" -> "tripolis",
      // Morocco
      "2538475" -> "rabat",
      // Monaco
      "2993458" -> "monaco",
      // Moldova
      "618426" -> "chişinau",
      // Montenegro
      "3193044" -> "podgorica",
      // Saint Martin
      "3578851" -> "marigot",
      // Madagascar
      "1070940" -> "antananarivo",
      // Marshall Islands
      "2113779" -> "majuro",
      // Macedonia
      "785842" -> "skopje",
      // Mali
      "2460596" -> "bamako",
      // Myanmar
      "6611854" -> "nay-pyi-taw",
      // Mongolia
      "2028462" -> "ulan-bator",
      // Macao
      "1821274" -> "macao",
      // Northern Mariana Islands
      // Martinique
      "3570675" -> "fort-de-france",
      // Mauritania
      "2377450" -> "nouakchott",
      // Montserrat
      "3578069" -> "plymouth",
      // Malta
      "2562305" -> "valletta",
      // Mauritius
      "934154" -> "port-louis",
      // Maldives
      "1282027" -> "male",
      // Malawi
      "927967" -> "lilongwe",
      // Mexico
      "3530597" -> "mexico-city",
      // Malaysia
      "1735161" -> "kuala-lumpur",
      // Mozambique
      "1040652" -> "maputo",
      // Namibia
      "3352136" -> "windhoek",
      // New Caledonia
      "2139521" -> "noumea",
      // Niger
      "2440485" -> "niamey",
      // Norfolk Island
      "2161314" -> "kingston-NF",
      // Nigeria
      "2352778" -> "abuja",
      // Nicaragua
      "3617763" -> "managua",
      // Netherlands
      "2759794" -> "amsterdam",
      // Norway
      "3143244" -> "oslo",
      // Nepal
      "1283240" -> "kathmandu",
      // Nauru
      // Niue
      "4036284" -> "alofi",
      // New Zealand
      "2179537" -> "wellington",
      "2193733" -> "auckland",
      // Oman
      "287286" -> "muscat",
      // Panama
      "3703443" -> "panama-city",
      // Peru
      "3936456" -> "lima",
      // French Polynesia
      "4033936" -> "papeete",
      // Papua New Guinea
      "2088122" -> "port-moresby",
      // Philippines
      "1701668" -> "manila",
      // Pakistan
      "1176615" -> "islamabad",
      // Poland
      "756135" -> "warsaw",
      // Saint Pierre and Miquelon
      "3424934" -> "saint-pierre",
      // Pitcairn
      "4030723" -> "adamstown",
      // Puerto Rico
      "4568127" -> "san-juan",
      // Palestinian Territory
      "7303419" -> "east-jerusalem",
      // Portugal
      "2267057" -> "lisbon",
      // Palau
      "7303944" -> "ngerulmud",
      // Paraguay
      "3439389" -> "asuncion",
      // Qatar
      "doha" -> "doha",
      // Reunion
      "935264" -> "saint-denis",
      // Romania
      "683506" -> "bucharest",
      // Serbia
      "792680" -> "belgrade",
      // Russia
      "524901" -> "moscow",
      // Rwanda
      "202061" -> "kigali",
      // Saudi Arabia
      "108410" -> "riyadh",
      // Solomon Islands
      "2108502" -> "honiara",
      // Seychelles
      "241131" -> "victoria", // clash with Victoria, BC
      // Sudan
      "379252" -> "khartoum",
      // Sweden
      "2673730" -> "stockholm",
      // Singapore
      "1880252" -> "singapore",
      // Saint Helena
      "3370903" -> "jamestown",
      // Slovenia
      "3196359" -> "ljubljana",
      // Svalbard and Jan Mayen
      "2729907" -> "longyearbyen",
      // Slovakia
      "3060972" -> "bratislava",
      // Sierra Leone
      "2409306" -> "freetown",
      // San Marino
      "3168070" -> "san-marino",
      // Senegal
      "2253354" -> "dakar",
      //Somalia
      "53654" -> "mogadishu",
      // Suriname
      "3383330" -> "paramaribo",
      // Sao Tome and Principe
      "2410763" -> "sao-tome",
      // El Salvador
      "3583361" -> "san-salvador",
      // Sint Maarten
      "3513392" -> "philipsburg",
      // Syria
      "170654" -> "damascus",
      // Swaziland
      "934985" -> "mbabane",
      // Turks and Caicos Islands
      "3576994" -> "cockburn-town",
      // Chad
      "2427123" -> "n-djamena",
      // French Southern Territories
      "1546102" -> "port-aux-francais",
      // Togo
      "2365267" -> "lome",
      // Thailand
      "1609350" -> "bangkok",
      // Tajikistan
      "1221874" -> "dushanbe",
      // Tokelau
      // East Timor
      "1645457" -> "dili",
      // Turkmenistan
      "162183" -> "ashgabat",
      // Tunisia
      "2464470" -> "tunis",
      // Tonga
      "4032402" -> "nuku-alofa",
      // Turkey
      "323786"-> "ankara",
      // Trinidad and Tobago
      "3573890" -> "port-of-spain",
      // Tuvalu
      "2110394" -> "funafuti",
      // Taiwan
      "1668341" -> "taipei",
      // Tanzania
      "160196" -> "dodoma",
      // Ukraine
      "703448" -> "kiev",
      // Uganda
      "232422" -> "kampala",
      // United States Minor Outlying Islands
      // United States
      "4140963" -> "washington-dc",
      "5368361" -> "los-angeles",
      "5128581" -> "new-york",
      // Uruguay
      "3441575" -> "montevideo",
      // Uzbekistan
      "1512569" -> "tashkent",
      // Vatican
      "6691831" -> "vatican-city",
      // Saint Vincent and the Grenadines
      "3577887" -> "kingstown",
      // Venezuela
      "3646738" -> "caracas",
      // British Virgin Islands
      "3577430" -> "road-town",
      // U.S. Virgin Islands
      "4795467" -> "charlotte-amalie",
      // Vietnam
      "1581130" -> "hanoi",
      // Vanuatu
      "2135171" -> "port-vila",
      // Wallis and Futuna
      "4034821" -> "mata-utu",
      // Samoa
      "4035413" -> "apia",
      // Yemen
      "71137" -> "sanaa",
      // Mayotte
      "921815" -> "mamoudzou",
      // South Africa
      "964137" -> "pretoria",
      // Zambia
      "909137" -> "lusaka",
      // Zimbabwe
      "890299" -> "harare",
      // Serbia and Montenegro
      "792680" -> "belgrade",
      // Netherlands Antilles
      "3513090" -> "willemstad"
    )
  private val Reverse: Map[String,String] = Mapping.map { case (k,v) => (v,k) }

  assume(Mapping.size == Reverse.size, "Duplicate entries are not allowed: " + Mapping.size + " vs " + Reverse.size)

  def originalID(stableID: String): Option[String] = Reverse.get(stableID)

  def stableID(originalID: String): Option[String] = Mapping.get(originalID)
}