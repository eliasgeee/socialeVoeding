package com.socialevoeding.bap.ui

import android.os.Build
import android.speech.tts.TextToSpeech
import android.speech.tts.Voice
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.util.*

abstract class BaseFragment() : Fragment(), TextToSpeech.OnInitListener {

    private var textToSpeech: TextToSpeech? = null
    private var result: Int? = null
    private var playButton : View? = null

    fun initTextToSpeech(playButton: View, isMale : Boolean) {

        playButton.setOnClickListener {
            speak()
        }

        this.playButton = playButton

        textToSpeech = TextToSpeech(context, this, "com.google.android.tts")

        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val language = Locale.getDefault().language
         //   val country = Locale.getDefault().country
            val locale: String = "$language"
            var voice: Voice? = null
            for (tmpVoice in textToSpeech!!.voices) {
                if (tmpVoice.name.contains("#male") && tmpVoice.name.contains(locale)) {
                    voice = tmpVoice
                    break
                }
            }
            if (voice != null) {
                textToSpeech!!.voice = voice
            }
        }*/
    }

    override fun onInit(status: Int) {
            if (status == TextToSpeech.SUCCESS) {
                result = textToSpeech!!.setLanguage(Locale.getDefault())

                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED)
                    showError("Language cannot be played")
                else
                    playButton?.isEnabled = true
            } else
                showError("Init failed")
    }

    private fun showError(errorString: String) {
        Toast.makeText(context, errorString, Toast.LENGTH_SHORT).show()
    }

    private fun speak() {
        val speechStatus = textToSpeech?.speak("Dit is een test", TextToSpeech.QUEUE_FLUSH, null)

        if(speechStatus == TextToSpeech.ERROR)
            showError("Uitspreken tekst gaat niet")
    }

    fun changeSpeed(speed : Float){
        textToSpeech?.setSpeechRate(speed)
    }

    override fun onPause() {
        super.onPause()
        textToSpeech?.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        textToSpeech?.stop()
        textToSpeech?.shutdown()
    }
}