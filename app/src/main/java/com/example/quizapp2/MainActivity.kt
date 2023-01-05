package com.example.quizapp2

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.quizapp2.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var rightAnswer: String? = null
    private var rightAnswerCount = 0
    private var quizCount = 1
    private var quiz_total = 5
    private var quizData = mutableListOf(
        mutableListOf("Question1","AnswerR","Answer1","Answer2","Answer3"),
        mutableListOf("Question2","AnswerR","Answer1","Answer2","Answer3"),
        mutableListOf("Question3","AnswerR","Answer1","Answer2","Answer3"),
        mutableListOf("Question4"," A nswerR","Answer1","Answer2","Answer3"),
        mutableListOf("Question5","AnswerR","Answer1","Answer2","Answer3"),
        mutableListOf("Question6","AnswerR","Answer1","Answer2","Answer3"),
        mutableListOf("Question7","AnswerR","Answer1","Answer2","Answer3")
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        quizData.shuffle()
        showNextQuiz()
    }

    fun checkAnswer(view: View){
        val answerBtn: Button = findViewById(view.id)
        val btntext = answerBtn.text.toString()
        val alertTitle:String
        if (btntext == rightAnswer){
            alertTitle = "CORRECT"
            rightAnswerCount+=1
        }
        else{
            alertTitle = "Wrong"
        }

        //alertdialog
        AlertDialog.Builder(this)
            .setTitle(alertTitle)
            .setMessage("Answer : $rightAnswer")
            .setPositiveButton("OK"){
                dialogInterface, i -> checkQuizCount()
            }
            .setCancelable(false)
            .show()
    }
    fun showNextQuiz(){

        countLabel.text = getString(R.string.count_label,quizCount)
        val quiz = quizData[0]

        questionLabel.text = quiz[0]
        rightAnswer = quiz[1]

        quiz.removeAt(0)
        quiz.shuffle()
        answerbtn1.text = quiz[0]
        answerbtn2.text = quiz[1]
        answerbtn3.text = quiz[2]
        answerbtn4.text = quiz[3]

        quizData.removeAt(0)
    }
    fun checkQuizCount(){
        if (quizCount == quiz_total){
            val intent = Intent(this@MainActivity,MainActivity2::class.java)
            intent.putExtra("RIGHT_ANSWER_COUNT",rightAnswerCount)
            startActivity(intent)
        }
        else{
            quizCount++
            showNextQuiz()
        }
    }

}