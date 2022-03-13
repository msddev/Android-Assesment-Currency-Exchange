package com.mkdev.remote.mappers

interface EntityMapper<M, E> {
    fun mapFromResponse(data: M): E
}
