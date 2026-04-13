package com.example.wellnessapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class MainActivity : AppCompatActivity() {

    private var mInterstitialAd: InterstitialAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        Find the buttons by the use of their ids
        val healthButton = findViewById<Button>(R.id.health_Recipe)
        val nutritionButton = findViewById<Button>(R.id.nutrition_advice)
        val meditationButton = findViewById<Button>(R.id.meditation)
        val hydrationButton = findViewById<Button>(R.id.hydration_alert)
        val exerciseButton = findViewById<Button>(R.id.start_exercise)
        val dailyMotivationButton = findViewById<Button>(R.id.daily_motivation)
        val weeklyMotivationButton = findViewById<Button>(R.id.weekly_goals)
        val checkProgressButton = findViewById<Button>(R.id.check_progress)
        val learnMoreButton = findViewById<Button>(R.id.learn_more)


//        Set onClick listener to the button as you do the intent to the different pages/activities
        healthButton.setOnClickListener {
            val intent = Intent(applicationContext, HealthActivity::class.java)
            startActivity(intent)

            showInterstitialAd()
        }
//        =================================================
        nutritionButton.setOnClickListener {
            val intent = Intent(applicationContext, nutritionButton::class.java)
            startActivity(intent)
        }
//        ======================================================
        meditationButton.setOnClickListener {
            val intent = Intent(applicationContext, meditationButton::class.java)
            startActivity(intent)
        }
//        ====================================================================
        hydrationButton.setOnClickListener {
            val intent = Intent(applicationContext, hydrationButton::class.java)
            startActivity(intent)
        }
//        =====================================================================
        exerciseButton.setOnClickListener {
            val intent = Intent(applicationContext, exerciseButton::class.java)
            startActivity(intent)
        }
//        ====================================================================
        dailyMotivationButton.setOnClickListener {
            val intent = Intent(applicationContext, dailyMotivationButton::class.java)
            startActivity(intent)
        }
//        =======================================================================
        weeklyMotivationButton.setOnClickListener {
            val intent = Intent(applicationContext, weeklyMotivationButton::class.java)
            startActivity(intent)
        }
//        ================================================================
        checkProgressButton.setOnClickListener {
            val intent = Intent(applicationContext, checkProgressButton::class.java)
            startActivity(intent)
        }

//        below is an implicit intent. When the button learn more is clicked, it takes us to the default browser

        learnMoreButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.healthline.com/health/how-to-maintain-a-healthy-lifestyle"))
            startActivity(intent)
        }

//        Below is the implementation of the banner add
        MobileAds.initialize(this)
        val adView = findViewById<AdView>(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

//        Load your ad
        loadInterstitialAd()

        }

    fun loadInterstitialAd() {
        val adRequest = AdRequest.Builder().build()
        //Requests interstitial ads
        InterstitialAd.load(
            this,
            "ca-app-pub-3940256099942544/1033173712", // Test ID
            adRequest,
            object : InterstitialAdLoadCallback() {

                override fun onAdLoaded(ad: InterstitialAd) {
                    mInterstitialAd = ad
                }

                override fun onAdFailedToLoad(error: LoadAdError) {
                    mInterstitialAd = null
                }
            }
        )
    }
    //Function checks if ad already running not to run anothet one and overlap - which is wrong
    fun showInterstitialAd() {
        if (mInterstitialAd != null) {
            mInterstitialAd?.show(this)
        }
    }
}