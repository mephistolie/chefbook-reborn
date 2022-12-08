package com.cactusknights.chefbook.data.datasources.local

import com.cactusknights.chefbook.core.coroutines.AppDispatchers
import com.cactusknights.chefbook.data.IFileSource
import com.cactusknights.chefbook.domain.entities.action.ActionStatus
import com.cactusknights.chefbook.domain.entities.action.DataResult
import com.cactusknights.chefbook.domain.entities.action.Failure
import com.cactusknights.chefbook.domain.entities.action.FileError
import com.cactusknights.chefbook.domain.entities.action.FileErrorType
import java.io.File
import javax.inject.Inject
import kotlinx.coroutines.withContext

class LocalFileSource @Inject constructor(
    private val dispatchers: AppDispatchers,
): IFileSource {

    override suspend fun getFile(path: String): ActionStatus<ByteArray> = withContext(dispatchers.io) {
        val file = File(path)
        if (!file.exists()) return@withContext Failure(FileError(FileErrorType.NOT_EXISTS))
        return@withContext try {
            DataResult(file.readBytes())
        } catch (e: OutOfMemoryError) {
            Failure(FileError(FileErrorType.BIG_FILE))
        }
    }

}
