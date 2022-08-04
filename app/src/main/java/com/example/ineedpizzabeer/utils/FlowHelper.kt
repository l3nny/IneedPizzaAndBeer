package com.example.ineedpizzabeer.utils

import com.example.ineedpizzabeer.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart

/**
 * extension function for Flow Class to emit loading state before the flow starts
 */

fun <T> Flow<ViewStateResult<T>>.onFlowStarts() =
    onStart {
        emit(ViewStateResult.Loading)
    }.catch {
        emit(ViewStateResult.Error(ResultException.Unexpected(R.string.app_name)))
    }