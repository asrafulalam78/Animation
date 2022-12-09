package com.mdasrafulalam78.animation

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.*
import com.mdasrafulalam78.animation.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bowlerGif.setOnClickListener {
            binding.ball.visibility = View.VISIBLE
            binding.fielderGif.visibility =View.VISIBLE
            changeColor(binding.root)
            slideInAnimation(binding.ball)//with xml animation layout
            rotateWithoutXMl(binding.ball) //rotate without using xml layout
            android.os.Handler().postDelayed({
                slideOutAnimation(binding.ball)
                runAfterTheBall(binding.fielderGif) //without animation xml
                android.os.Handler().postDelayed({
                    binding.ball.visibility = View.GONE
                    binding.fielderGif.visibility = View.GONE
                }, 1800)
            }, 2000)
        }
    }
    @SuppressLint("ObjectAnimatorBinding")
    private fun changeColor(view: View) {
        val animator = ObjectAnimator.ofArgb(
            view.parent,
            "backgroundColor", Color.WHITE, Color.GREEN
        )
        animator.duration = 1000
        animator.repeatCount = 3
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.start()
    }
    private fun rotateWithoutXMl(view: View) {
        val animator = ObjectAnimator.ofFloat(view, View.ROTATION, 0f,360f)
        animator.repeatCount = ValueAnimator.INFINITE
        animator.duration = 500
        animator.repeatMode = ValueAnimator.RESTART
        animator.start()
    }
    private fun runAfterTheBall(view: View) {
        val animator = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, 0f,800f)
        animator.repeatCount = 1
        animator.duration = 1000
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.start()
    }

    private fun slideInAnimation(view: View) {
        val bounceAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_in)
        view.startAnimation(bounceAnimation)
    }

    private fun slideOutAnimation(view: View) {
        val bounceAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_out)
        view.startAnimation(bounceAnimation)
    }

}