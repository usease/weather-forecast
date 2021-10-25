package com.example.myapplication.domain.util

interface DomainMapper <T, DomainModel>{

    fun mapToDomainModel(model: T): DomainModel

    // For simplicity and brevity, mapping from domain model will be skipped.
//    fun mapFromDomainModel(domainModel: DomainModel): T
}