package de.ph1b.audiobook.playback.player

import rx.Observable
import rx.subjects.PublishSubject
import java.io.File
import java.io.IOException


/**
 * Abstraction of android.media.MediaPlayer.
 *
 * @author Paul Woitaschek
 */
abstract class Player {

    protected val completionSubject = PublishSubject.create<Unit>()

    protected val errorSubject = PublishSubject.create<Unit>()

    protected val preparedSubject = PublishSubject.create<Unit>()

    abstract fun seekTo(to: Int)

    abstract fun isPlaying(): Boolean

    abstract fun start()

    abstract fun pause()

    @Throws(IOException::class)
    abstract fun prepare(file: File)

    abstract fun reset()

    abstract fun setWakeMode(mode: Int)

    abstract val currentPosition: Int

    abstract val duration: Int

    abstract var playbackSpeed: Float

    val onError: Observable<Unit> = errorSubject.asObservable()

    val onCompletion: Observable<Unit> = completionSubject.asObservable()
}