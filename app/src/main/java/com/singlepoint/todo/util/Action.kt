package com.singlepoint.todo.util

enum class Action {
    ADD,
    UPDATE,
    DELETE,
    DELETE_ALL,
    SELECT,
    UNDO,
    NO_ACTION
}

fun  String?.toAction(): Action {
    return when (this) {
        "ADD" -> Action.ADD
        "UPDATE" -> Action.UPDATE
        "DELETE" -> Action.DELETE
        "DELETE_ALL" -> Action.DELETE_ALL
        "SELECT" -> Action.SELECT
        else -> {
            Action.NO_ACTION
        }
    }
}