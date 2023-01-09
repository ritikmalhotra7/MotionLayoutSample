package com.example.motionlayoutsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.MotionLayout.TransitionListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.motionlayoutsample.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private val list = arrayListOf<String>()
    private val fruits = arrayListOf("Apple", "Orange", "Banana", "Strawberry", "Blueberry", "Raspberry", "Mango", "Pineapple", "Kiwi", "Melon", "Peach", "Plum", "Cherry", "Grape", "Watermelon", "Pomegranate", "Lemon", "Lime", "Grapefruit", "Tangerine")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //initialising adapter
        recyclerViewAdapter = RecyclerViewAdapter(list)
        //setting recyclerView
        binding.rvList.apply {
            this.adapter = recyclerViewAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
        //attaching transition listener
        binding.root.addTransitionListener(object : TransitionListener {

            //when the animation is triggered
            override fun onTransitionStarted(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int
            ) {
            }

            //in between transition
            override fun onTransitionChange(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int,
                progress: Float
            ) {
            }

            //when the transition is completed
            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                if (currentId == motionLayout!!.endState) {
                    CoroutineScope(Dispatchers.Main).launch {
                        delay(3000)
                        list.clear()
                        val random = Random().nextInt(fruits.size)
                        for (i in 1..10) list.add("${fruits[random]} $i")
                        recyclerViewAdapter.notifyItemRangeChanged(0,10)
                        motionLayout.transitionToStart()
                    }
                }
            }

            //not to use
            override fun onTransitionTrigger(
                motionLayout: MotionLayout?,
                triggerId: Int,
                positive: Boolean,
                progress: Float
            ) {
            }
        })
    }
}