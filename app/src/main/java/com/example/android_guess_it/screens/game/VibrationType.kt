package com.example.android_guess_it.screens.game

private val CORRECT_VIBRATION_PATTERN = longArrayOf(100, 100, 100, 100, 100, 100)
private val PANIC_VIBRATION_PATTERN = longArrayOf(0, 200)
private val GAME_OVER_VIBRATION_PATTERN = longArrayOf(0, 2000)
private val NO_VIBRATION_PATTERN = longArrayOf(0)

enum class VibrationType(val pattern: LongArray) {
    CORRECT(CORRECT_VIBRATION_PATTERN),
    GAME_OVER(GAME_OVER_VIBRATION_PATTERN),
    COUNTDOWN_PANIC(PANIC_VIBRATION_PATTERN),
    NO_VIBRATION(NO_VIBRATION_PATTERN)
}