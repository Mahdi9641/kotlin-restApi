package com.example.restcrudmahdi.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "MESSAGE")
class Message : BaseEntity() {

    @Column(name = "message")
    var message: String? = null

}