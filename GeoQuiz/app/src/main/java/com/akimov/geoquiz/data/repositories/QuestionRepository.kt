package com.akimov.geoquiz.data.repositories

import com.akimov.geoquiz.domain.models.Question
import com.akimov.geoquiz.domain.repositories.IQuestionRepository

class QuestionRepository :IQuestionRepository{
  override fun getAllQuestions(): List<Question> {
    return listOf(
        Question(1, "Canberra is the capital of Australia.", true, "question_australia"),
        Question(2, "The Pacific Ocean is larger than the Atlantic Ocean.", true, "question_oceans"),
        Question(3, "The Suez Canal connects the Red Sea and the Indian Ocean.", false, "question_mideast"),
        Question(4, "The source of the Nile River is in Egypt.", false, "question_africa"),
        Question(5, "The Amazon River is the longest river in the Americas.", true, "question_americas"),
        Question(6, "Lake Baikal is the worls\\'s oldest and deepest freshwater lake.", true, "question_asia")
    )
  }

}