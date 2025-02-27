package com.soha.infotech.newsapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Part 4.1.1 : Create a Source data class
 */

@Parcelize
data class Source(
    val id: String,
    val name: String
): Parcelable