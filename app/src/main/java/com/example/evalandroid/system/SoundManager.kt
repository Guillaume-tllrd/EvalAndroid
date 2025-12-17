package com.example.evalandroid.system

import android.content.Context
import android.media.MediaPlayer
import com.example.evalandroid.R

/**
 * Manager simple pour gérer les effets sonores de l'app.
 *
 * Le but est de centraliser le code audio ici pour ne pas polluer les ViewModels
 * ou les écrans avec de la logique technique (MediaPlayer).
 *
 * @property context Le contexte Android nécessaire pour accéder au dossier `res/raw`.
 */
class SoundManager(private val context: Context) {

    fun playClickSound() {

        val mediaPlayer = MediaPlayer.create(context, R.raw.mouse_click)

        mediaPlayer.start()

        mediaPlayer.setOnCompletionListener { mp ->
            mp.release()
        }
    }
}