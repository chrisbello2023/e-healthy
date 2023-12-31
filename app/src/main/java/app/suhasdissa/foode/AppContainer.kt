package app.suhasdissa.foode

import android.content.ClipboardManager
import app.suhasdissa.foode.backend.database.BarcodeDatabase
import app.suhasdissa.foode.backend.database.ENumberDatabase
import app.suhasdissa.foode.backend.repositories.AdditivesRepository
import app.suhasdissa.foode.backend.repositories.BarcodeHistoryRepository
import app.suhasdissa.foode.backend.repositories.BarcodeHistoryRepositoryImpl
import app.suhasdissa.foode.backend.repositories.LocalAdditivesRepository
import app.suhasdissa.foode.backend.repositories.OpenFoodFactRepository
import app.suhasdissa.foode.backend.repositories.OpenFoodFactRepositoryImpl
import app.suhasdissa.foode.backend.repositories.TranslationRepository
import app.suhasdissa.foode.backend.repositories.TranslationRepositoryImpl

interface AppContainer {
    val additivesRepository: AdditivesRepository
    val clipboardManager: ClipboardManager
    val openFoodFactRepository: OpenFoodFactRepository
    val barcodeHistoryRepository: BarcodeHistoryRepository
    val translationRepository: TranslationRepository
}

class DefaultAppContainer(
    database: ENumberDatabase,
    barcodeDatabase: BarcodeDatabase,
    clipboardManager: ClipboardManager
) : AppContainer {
    override val additivesRepository: AdditivesRepository by lazy {
        LocalAdditivesRepository(
            database.additivesDao()
        )
    }
    override val barcodeHistoryRepository: BarcodeHistoryRepository by lazy {
        BarcodeHistoryRepositoryImpl(
            barcodeDatabase.barcodeHistoryDao()
        )
    }
    override val translationRepository: TranslationRepository by lazy {
        TranslationRepositoryImpl()
    }
    override val clipboardManager: ClipboardManager by lazy {
        clipboardManager
    }
    override val openFoodFactRepository: OpenFoodFactRepository by lazy {
        OpenFoodFactRepositoryImpl()
    }
}
