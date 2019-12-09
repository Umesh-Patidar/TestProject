package mytestproj.com.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.ViewTreeObserver
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import kotlinx.android.synthetic.main.activity_spring_animation.*
import mytestproj.com.R

class SpringAnimationActivity : AppCompatActivity() {

    private companion object Params {
        val STIFFNESS = SpringForce.STIFFNESS_MEDIUM
        val DAMPING_RATIO = SpringForce.DAMPING_RATIO_NO_BOUNCY
    }

    lateinit var xAnimation: SpringAnimation
    lateinit var yAnimation: SpringAnimation


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spring_animation)


        linear.viewTreeObserver.addOnGlobalLayoutListener(object: ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                xAnimation = createSpringAnimation(
                    linear, SpringAnimation.X, linear.x, STIFFNESS, DAMPING_RATIO)
                yAnimation = createSpringAnimation(
                    linear, SpringAnimation.Y, linear.y, STIFFNESS, DAMPING_RATIO)
                linear.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })

        var dX = 0f
        var dY = 0f

        linear.setOnTouchListener { view, event ->
            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
                    // capture the difference between view's top left corner and touch point
                    dX = view.x - event.rawX
                    dY = view.y - event.rawY

                    // cancel animations so we can grab the view during previous animation
                    xAnimation.cancel()
                    yAnimation.cancel()
                }
                MotionEvent.ACTION_MOVE -> {
                    //  a different approach would be to change the view's LayoutParams.
                    linear.animate()
                        .x(event.rawX + dX)
                        .y(event.rawY + dY)
                        .setDuration(0)
                        .start()
                }
                MotionEvent.ACTION_UP -> {
                    xAnimation.start()
                    yAnimation.start()
                }
            }
            true
        }
    }

    fun createSpringAnimation(view: View,
                              property: DynamicAnimation.ViewProperty,
                              finalPosition: Float,
                              stiffness: Float,
                              dampingRatio: Float): SpringAnimation {
        val animation = SpringAnimation(view, property)
        val spring = SpringForce(finalPosition)
        spring.stiffness = stiffness
        spring.dampingRatio = dampingRatio
        animation.spring = spring
        return animation
    }
}
