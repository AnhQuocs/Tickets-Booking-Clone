package com.example.ticketbookingclone.domain

import java.io.Serializable

data class FlightModel(
    var AirlineLogo: String = "",
    var AirlineName: String = "",
    var ArriveTime: String = "",
    var ClassSeat: String = "",
    var Date: String = "",
    var From: String = "",
    var FromShort: String = "",
    var NumerSeat: Int = 0,
    var Price: Double = 0.0,
    var Passenger: String = "",
    var ReservedSeats: String = "",
    var Time: String = "",
    var To: String = "",
    var ToShort: String = ""
): Serializable
