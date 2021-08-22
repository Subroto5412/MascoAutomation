package com.bd.mascogroup.automation.utils

object AppConstants {

    const val DB_VERSION = 1
    val DB_NAME = String.format("mbpd.db", DB_VERSION)

    enum class APIStatus(val type: Int) {
        SUCCESS(0),

        E1(1),
        E2(2),

        E100(100),
        E101(101),
        E102(102),


        E110(110),
        E111(111),

        E999(999);

        companion object {
            fun fromInt(type: Int): APIStatus {
                return values().first { it.type == type }
            }
        }
    }

    enum class Number(val value: Int) {
        ZERO(0),
        ONE(1),
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8),
        NINE(9),
        TEN(10),
        ELEVEN(11),
        TWELVE(12),
        THIRTEEN(13),
        FOURTEEN(14),
        FIFTEEN(15),
        SIXTEEN(16),
        THIRTY(30),
        THIRTYSIX(36),
        FOURTYTWO(42),
        FIFTY(50),
        HUNDRED(100),
        FIVE_HUNDRED(500),
        THOUSAND(1000),
        FIFTEEN_HUNDRED(1500)
    }


    //API
    //API RELATED
    const val API_CACHE_SIZE_IN_MB = 10
    const val API_CACHE_EXPIRE_IN_MINUTE = 30
    const val API_CONNECT_TIMEOUT_IN_SECOND = 30
}