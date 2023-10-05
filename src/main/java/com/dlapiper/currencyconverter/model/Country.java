package com.dlapiper.currencyconverter.model;

import java.util.Arrays;

public enum Country {
    ABU_DHABI("Abu Dhabi", "Dirham", "AED"),
    ALBANIA("Albania", "Lek", "ALL"),
    ALGERIA("Algeria", "Dinar", "DZD"),
    ANGOLA("Angola", "Readj Kwanza", "AOA"),
    ANTIGUA("Antigua", "E Caribbean Dollar", "XCD"),
    ARGENTINA("Argentina", "Peso", "ARS"),
    ARMENIA("Armenia", "Dram", "AMD"),
    ARUBA("Aruba", "Florin", "AWG"),
    AUSTRALIA("Australia", "Dollar", "AUD"),
    AZERBAIJAN("Azerbaijan", "New Manat", "AZN"),
    BAHAMAS("Bahamas", "Dollar", "BSD"),
    BAHRAIN("Bahrain", "Dinar", "BHD"),
    BANGLADESH("Bangladesh", "Taka", "BDT"),
    BARBADOS("Barbados", "Dollar", "BBD"),
    BELARUS("Belarus", "Rouble", "BYN"),
    BELIZE("Belize", "Dollar", "BZD"),
    BENIN("Benin", "CFA Franc", "XOF"),
    BERMUDA("Bermuda", "Dollar (US)", "BMD"),
    BHUTAN("Bhutan", "Ngultrum", "BTN"),
    BOLIVIA("Bolivia", "Boliviano", "BOB"),
    BOSNIA_HERZEGOVINIA("Bosnia- Herzegovinia", "Marka", "BAM"),
    BOTSWANA("Botswana", "Pula", "BWP"),
    BRAZIL("Brazil", "Real", "BRL"),
    BRUNEI("Brunei", "Dollar", "BND"),
    BULGARIA("Bulgaria", "Lev", "BGN"),
    BURKINA_FASO("Burkina Faso", "CFA Franc", "XOF"),
    BURUNDI("Burundi", "Franc", "BIF"),
    CAMBODIA("Cambodia", "Riel", "KHR"),
    CAMEROON_REPUBLIC("Cameroon Republic", "CFA Franc", "XAF"),
    CANADA("Canada", "Dollar", "CAD"),
    CAPE_VERDE_ISLANDS("Cape Verde Islands", "Escudo", "CVE"),
    CAYMAN_ISLANDS("Cayman Islands", "Dollar", "KYD"),
    CENTRAL_AFRICAN_REPUBLIC("Central African Republic", "CFA Franc", "XAF"),
    CHAD("Chad", "CFA Franc", "XAF"),
    CHILE("Chile", "Peso", "CLP"),
    CHINA("China", "Yuan", "CNY"),
    COLOMBIA("Colombia", "Peso", "COP"),
    COMOROS("Comoros", "Franc", "KMF"),
    CONGO_BRAZAVILLE("Congo (Brazaville)", "CFA Franc", "XAF"),
    CONGO_DEMREP("Congo (DemRep)", "Congo Fr", "CDF"),
    COSTA_RICA("Costa Rica", "Colon", "CRC"),
    COTE_DIVOIRE("Cote d'Ivoire", "CFA Franc", "XOF"),
    CUBA("Cuba", "Peso", "CUP"),
    CZECH_REPUBLIC("Czech Republic", "Koruna", "CZK"),
    DENMARK("Denmark", "Krone", "DKK"),
    DJIBOUTI("Djibouti", "Franc", "DJF"),
    DOMINICA("Dominica", "E Caribbean Dollar", "XCD"),
    DOMINICAN_REPUBLIC("Dominican Republic", "Peso", "DOP"),
    DUBAI("Dubai", "Dirham", "AED"),
    EGYPT("Egypt", "Pound", "EGP"),
    EL_SALVADOR("El Salvador", "Colon", "SVC"),
    EQUATORIAL_GUINEA("Equatorial Guinea", "CFA Franc", "XAF"),
    ERITREA("Eritrea", "Nakfa", "ERN"),
    ETHIOPIA("Ethiopia", "Birr", "ETB"),
    EUROZONE("Eurozone", "Euro", "EUR"),
    FIJI_ISLANDS("Fiji Islands", "Dollar", "FJD"),
    FR_POLYNESIA("Fr. Polynesia", "CFP Franc", "XPF"),
    GABON("Gabon", "CFA Franc", "XAF"),
    GAMBIA("Gambia", "Dalasi", "GMD"),
    GEORGIA("Georgia", "Lari", "GEL"),
    GHANA("Ghana", "Cedi", "GHS"),
    GRENADA("Grenada", "E Caribbean Dollar", "XCD"),
    GUATEMALA("Guatemala", "Quetzal", "GTQ"),
    GUINEA_BISSAU("Guinea Bissau", "CFA Franc", "XOF"),
    GUINEA_REPUBLIC("Guinea Republic", "Franc", "GNF"),
    GUYANA("Guyana", "Dollar", "GYD"),
    HAITI("Haiti", "Gourde", "HTG"),
    HONDURAS("Honduras", "Lempira", "HNL"),
    HONG_KONG("Hong Kong", "Dollar", "HKD"),
    HUNGARY("Hungary", "Forint", "HUF"),
    ICELAND("Iceland", "Krona", "ISK"),
    INDIA("India", "Rupee", "INR"),
    INDONESIA("Indonesia", "Rupiah", "IDR"),
    IRAQ("Iraq", "Dinar", "IQD"),
    ISRAEL("Israel", "Shekel", "ILS"),
    JAMAICA("Jamaica", "Dollar", "JMD"),
    JAPAN("Japan", "Yen", "JPY"),
    JORDAN("Jordan", "Dinar", "JOD"),
    KAZAKHSTAN("Kazakhstan", "Tenge", "KZT"),
    KENYA("Kenya", "Schilling", "KES"),
    KUWAIT("Kuwait", "Dinar", "KWD"),
    KYRGYZ_REPUBLIC("Kyrgyz Republic", "Som", "KGS"),
    LAO_PEOPLES_DEM_REP("Lao People's Dem Rep", "Kip", "LAK"),
    LEBANON("Lebanon", "Pound", "LBP"),
    LESOTHO("Lesotho", "Loti", "LSL"),
    LIBERIA("Liberia", "Dollar (US)", "LRD"),
    LIBYA("Libya", "Dinar", "LYD"),
    MACAO("Macao", "Pataca", "MOP"),
    MACEDONIA("Macedonia", "Denar", "MKD"),
    MADAGASCAR("Madagascar", "Malagasy Ariary", "MGA"),
    MALAWI("Malawi", "Kwacha", "MWK"),
    MALAYSIA("Malaysia", "Ringgit", "MYR"),
    MALDIVE_ISLANDS("Maldive Islands", "Rufiyaa", "MVR"),
    MALI_REPUBLIC("Mali Republic", "CFA Franc", "XOF"),
    MAURITANIA("Mauritania", "Ouguiya", "MRU"),
    MAURITIUS("Mauritius", "Rupee", "MUR"),
    MEXICO("Mexico", "Mexican Peso", "MXN"),
    MOLDOVA("Moldova", "Leu", "MDL"),
    MONGOLIA("Mongolia", "Tugrik", "MNT"),
    MONTSERRAT("Montserrat", "E Caribbean Dollar", "XCD"),
    MOROCCO("Morocco", "Dirham", "MAD"),
    MOZAMBIQUE("Mozambique", "Metical", "MZN"),
    MYANMAR("Myanmar", "Kyat", "MMK"),
    NEPAL("Nepal", "Rupee", "NPR"),
    NEW_CALEDONIA("New Caledonia", "CFP Franc", "XPF"),
    NEW_ZEALAND("New Zealand", "Dollar", "NZD"),
    NICARAGUA("Nicaragua", "Gold Cordoba", "NIO"),
    NIGER_REPUBLIC("Niger Republic", "CFA Franc", "XOF"),
    NIGERIA("Nigeria", "Naira", "NGN"),
    NORWAY("Norway", "Norwegian Krone", "NOK"),
    OMAN("Oman", "Rial", "OMR"),
    PAKISTAN("Pakistan", "Rupee", "PKR"),
    PANAMA("Panama", "Balboa", "PAB"),
    PAPUA_NEW_GUINEA("Papua New Guinea", "Kina", "PGK"),
    PARAGUAY("Paraguay", "Guarani", "PYG"),
    PERU("Peru", "New Sol", "PEN"),
    PHILIPPINES("Philippines", "Peso", "PHP"),
    POLAND("Poland", "Zloty", "PLN"),
    QATAR("Qatar", "Riyal", "QAR"),
    ROMANIA("Romania", "New Leu", "RON"),
    RUSSIA("Russia", "Rouble", "RUB"),
    RWANDA("Rwanda", "Franc", "RWF"),
    SAO_TOME_AND_PRINCIPE("Saotome & Principe", "Dobra", "STD"),
    SAUDI_ARABIA("Saudi Arabia", "Riyal", "SAR"),
    SENEGAL("Senegal", "CFA Franc", "XOF"),
    SERBIA("Serbia", "Dinar", "RSD"),
    SEYCHELLES("Seychelles", "Rupee", "SCR"),
    SIERRA_LEONE("Sierra Leone", "Leone", "SLE"),
    SINGAPORE("Singapore", "Dollar", "SGD"),
    SOLOMAN_ISLANDS("Soloman Islands", "Dollar", "SBD"),
    SOMALI_REPUBLIC("Somali Republic", "Schilling", "SOS"),
    SOUTH_AFRICA("South Africa", "Rand", "ZAR"),
    SOUTH_KOREA("South Korea", "Won", "KRW"),
    SRI_LANKA("Sri Lanka", "Rupee", "LKR"),
    ST_CHRISTOPHER_AND_ANGUILLA("St Christopher & Anguilla", "E Caribbean Dollar", "XCD"),
    ST_LUCIA("St Lucia", "E Caribbean Dollar", "XCD"),
    ST_VINCENT("St Vincent", "E Caribbean Dollar", "XCD"),
    SUDAN_REPUBLIC("Sudan Republic", "Pound", "SDG"),
    SURINAM("Surinam", "Dollar", "SRD"),
    SWAZILAND("Swaziland", "Lilangeni", "SZL"),
    SWEDEN("Sweden", "Krona", "SEK"),
    SWITZERLAND("Switzerland", "Franc", "CHF"),
    TAIWAN("Taiwan", "Dollar", "TWD"),
    TANZANIA("Tanzania", "Schilling", "TZS"),
    THAILAND("Thailand", "Baht", "THB"),
    TOGO_REPUBLIC("Togo Republic", "CFA Franc", "XOF"),
    TONGA_ISLANDS("Tonga Islands", "Pa'anga (AUS)", "TOP"),
    TRINIDAD_TOBAGO("Trinidad/Tobago", "Dollar", "TTD"),
    TUNISIA("Tunisia", "Dinar", "TND"),
    TURKEY("Turkey", "Turkish Lira", "TRY"),
    TURKMENISTAN("Turkmenistan", "New Manat", "TMT"),
    UAE("UAE", "Dirham", "AED"),
    UGANDA("Uganda", "Schilling", "UGX"),
    UK("United Kingdom", "Pound", "GBP"),
    UKRAINE("Ukraine", "Hryvnia", "UAH"),
    URUGUAY("Uruguay", "Peso", "UYU"),
    USA("USA", "Dollar", "USD"),
    UZBEKISTAN("Uzbekistan", "Sum", "UZS"),
    VANUATU("Vanuatu", "Vatu", "VUV"),
    VENEZUELA("Venezuela", "Bolivar Fuerte", "VEF"),
    VIETNAM("Vietnam", "Dong", "VND"),
    WALLIS_AND_FUTUNA_ISLANDS("Wallis & Futuna Islands", "CFP Franc", "XPF"),
    WESTERN_SAMOA("Western Samoa", "Tala", "WST"),
    YEMEN_REP_OF("Yemen (Rep of)", "Rial", "YER"),
    ZAMBIA("Zambia", "Kwacha", "ZMW"),
    ZIMBABWE("Zimbabwe", "Dollar", "ZWL");

    private final String name;
    private final String currencyName;
    private final String currencyCode;

    Country(String name, String currencyName, String currencyCode) {
        this.name = name;
        this.currencyName = currencyName;
        this.currencyCode = currencyCode;
    }

    public String getName() {
        return name;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }
    public boolean matchesCurrencyCode(String searchTerm) {
        return currencyCode.equals(searchTerm);
    }

    public boolean matchesName(String searchTerm) {
        return name.equals(searchTerm);
    }
    public boolean matchesCurrencyName(String searchTerm) {
        return currencyName.equals(searchTerm);
    }

    @FunctionalInterface
    interface CountryFinder {
        boolean findCountry(Country country, String searchTerm);
    }

    static Country findByProperty(String searchTerm, CountryFinder finder) {
        return Arrays.stream(Country.values())
                .filter(country -> finder.findCountry(country, searchTerm))
                .findFirst()
                .orElse(null);
    }

    public static Country findCountryByCurrencyCode(String currencyCode) {
        return findByProperty(currencyCode, Country::matchesCurrencyCode);
    }

    public static Country findCountryByName(String name) {
        return findByProperty(name, Country::matchesName);
    }
    public static Country findCountryByCurrencyName(String currencyName) {
        return findByProperty(currencyName, Country::matchesCurrencyName);
    }

}
