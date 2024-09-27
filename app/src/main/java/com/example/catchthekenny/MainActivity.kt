package com.example.catchthekenny

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Random

class MainActivity : AppCompatActivity() {
    var scoreText: TextView? = null
    var timeText: TextView? = null
    var score: Int = 0
    var imageView: ImageView? = null
    var imageView2: ImageView? = null
    var imageView3: ImageView? = null
    var imageView4: ImageView? = null
    var imageView5: ImageView? = null
    var imageView6: ImageView? = null
    var imageView7: ImageView? = null
    var imageView8: ImageView? = null
    var imageView9: ImageView? = null
    var imageView10: ImageView? = null
    var imageView11: ImageView? = null
    var imageView12: ImageView? = null
    lateinit var imageArray: Array<ImageView?>
    var handler: Handler? = null
    var runnable: Runnable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //initialize
        timeText = findViewById<View>(R.id.timeText) as TextView
        scoreText = findViewById<View>(R.id.scoreText) as TextView
        imageView = findViewById<ImageView>(R.id.imageView7)
        imageView2 = findViewById<ImageView>(R.id.imageView8)
        imageView3 = findViewById<ImageView>(R.id.imageView9)
        imageView4 = findViewById<ImageView>(R.id.imageView10)
        imageView5 = findViewById<ImageView>(R.id.imageView11)
        imageView6 = findViewById<ImageView>(R.id.imageView12)
        imageView7 = findViewById<ImageView>(R.id.imageView13)
        imageView8 = findViewById<ImageView>(R.id.imageView14)
        imageView9 = findViewById<ImageView>(R.id.imageView15)
        imageView10 = findViewById<ImageView>(R.id.imageView16)
        imageView11 = findViewById<ImageView>(R.id.imageView17)
        imageView12 = findViewById<ImageView>(R.id.imageView18)

        imageArray = arrayOf(
            imageView,
            imageView2,
            imageView3,
            imageView4,
            imageView5,
            imageView6,
            imageView7,
            imageView8,
            imageView9,
            imageView10,
            imageView11,
            imageView12

        )

        hideImages()


        score = 0

        object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeText!!.text = "Time: " + millisUntilFinished / 1000
            }

            override fun onFinish() {
                timeText!!.text = "Time Off"
                handler!!.removeCallbacks(runnable!!)
                for (image in imageArray) {
                    image!!.visibility = View.INVISIBLE
                }


                val alert: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)

                alert.setTitle("Restart?")
                alert.setMessage("Are you sure to restart game?")
                alert.setPositiveButton("Yes",
                    DialogInterface.OnClickListener { dialog, which -> //restart
                        val intent = intent
                        finish()
                        startActivity(intent)
                    })

                alert.setNegativeButton("No",
                    DialogInterface.OnClickListener { dialog, which ->
                        Toast.makeText(
                            this@MainActivity,
                            "Game Over!",
                            Toast.LENGTH_SHORT
                        ).show()
                    })

                alert.show()
            }
        }.start()
    }

    fun increaseScore(view: View?) {
        score++

        //score = score + 1;
        scoreText!!.text = "Score: $score"
    }

    fun hideImages() {
        handler = Handler()

        runnable = object : Runnable {
            override fun run() {
                for (image in imageArray) {
                    image!!.visibility = View.INVISIBLE
                }

                val random = Random()
                val i = random.nextInt(9)
                imageArray[i]!!.visibility = View.VISIBLE

                handler!!.postDelayed(this, 500)
            }
        }


        handler!!.post(runnable as Runnable)
    }
}