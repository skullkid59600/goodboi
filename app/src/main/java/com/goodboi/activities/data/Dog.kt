package com.goodboi.activities.data

import java.net.URL

class Dog(var id: Int, val image: String) {
    var point = 1000.0
    var games = 0 //Number of played games = 0
    var win = 0// Number of games loose = 0
    var cute = 0

    fun play(point: Double) {
        this.point = point
        games++
    }

    fun versus(vote: Int, b: Dog): Boolean {
        //vote : 0, 1e gage, 1, 2e gagne.
        val k = 32
        var ra = point
        var rb = b.point
        val sa = 1 - vote
        if (id == b.id) return false
        val ea = Math.abs(1 / (1 + Math.pow(10.0, (rb - ra) / 400)))
        val eb = Math.abs(1 / (1 + Math.pow(10.0, (ra - rb) / 400)))
        ra = ra + k * (sa - ea)
        rb = rb + k * (vote - eb)
        play(ra)
        b.play(rb)
        if (sa == 1 && vote == 0) win++ else b.win++
        return true
    }

    override fun toString(): String {
        var rate = 0.0
        if (games != 0) {
            rate = win.toDouble() / games.toDouble()
        }
        return "Dog{" +
                "point=" + point +
                ", id=" + id +
//                ", games=" + games +
//                ", win=" + win +
//                ", rate=" + rate +
//                ", cute=" + cute +
                ", image=" + image +
                '}'
    }

    /*fun main() {//TODO modifier Main de test
        val dogs = arrayOfNulls<Dog>(10)
        for (i in 0..9) {
            dogs[i] = Dog(i, "")
            dogs[i]!!.cute = i * 10
            println(dogs[i])
        }
        println("Starting versus : ")
        var iteration = 0
        while (iteration < 10000) {
            for (i in 0..8) {
                val b = i + 1
                val diff = Math.abs(dogs[i]!!.cute - dogs[b]!!.cute)
                var vote = 0
                if (Math.floor(Math.random() * 100).toInt() > diff) vote = 1
                dogs[i]!!.versus(vote,
                        dogs[b]!!)
                iteration++
            }
        }
        for (i in 0..9) {
            println(dogs[i])
        }
    }*/
}

