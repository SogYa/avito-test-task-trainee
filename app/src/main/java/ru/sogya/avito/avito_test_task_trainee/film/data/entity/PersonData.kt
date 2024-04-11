package ru.sogya.avito.avito_test_task_trainee.film.data.entity

import ru.sogya.avito.avito_test_task_trainee.film.domain.entity.Person

data class PersonData(
    override val photo: String,
    override val name: String,
    override val profession: String
): Person