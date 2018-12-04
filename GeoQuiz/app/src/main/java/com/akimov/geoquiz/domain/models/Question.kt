package com.akimov.geoquiz.domain.models

/**
 * Created by lex on 1/5/18.
 */
open class Question(var id: Int,
                    var questionText: String,
                    var isAnswerTrue: Boolean,
                    var description: String,
                    var isAnswerShown: Boolean = false)