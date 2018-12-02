package com.akimov.geoquiz.domain.repositories

import com.akimov.geoquiz.domain.models.Question

interface IQuestionRepository {
  fun getAllQuestions(): List<Question>
}