package com.akimov.geoquiz.data.repositories

import com.akimov.geoquiz.domain.models.Question
import com.akimov.geoquiz.domain.repositories.IQuestionRepository

class QuestionRepository : IQuestionRepository {

  private var questionList = mutableListOf(Question(1, "Canberra is the capital of Australia.", true, "question_australia", true),
      Question(2, "The Pacific Ocean is larger than the Atlantic Ocean.", true, "question_oceans"),
      Question(3, "The Suez Canal connects the Red Sea and the Indian Ocean.", false, "question_mideast"),
      Question(4, "The source of the Nile River is in Egypt.", false, "question_africa"),
      Question(5, "The Amazon River is the longest river in the Americas.", true, "question_americas"),
      Question(6, "Lake Baikal is the worls's oldest and deepest freshwater lake.", true, "question_asia")
  )

  override fun getAllQuestions(): List<Question> {
    return questionList
  }

  override fun updateQuestion(question: Question): Boolean {
    var foundQuestion = questionList.find { it.id == question.id }

    foundQuestion?.apply {
      description = question.description
      questionText = question.questionText
      isAnswerTrue = question.isAnswerTrue
      isAnswerShown = question.isAnswerShown
      return true
    }
    return false
  }
}