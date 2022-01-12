package com.example.testbts.model

class RegisterModel {
    var email: String = ""
    var username: String = ""
    var password: String = ""

    constructor(email: String, username: String, password: String) {
        this.email = email
        this.username = username
        this.password = password
    }
}