package net.yupol.transmissionremote.app.security

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ServerConfigCipherTest {
    @Test
    fun encryptAndDecrypt_roundTripsWithoutExposingPlainText() {
        val plainText = "{\"host\":\"dl.svelc.si\",\"password\":\"private-value\"}"

        val encrypted = ServerConfigCipher.encrypt(plainText)

        assertThat(encrypted).doesNotContain("dl.svelc.si")
        assertThat(encrypted).doesNotContain("private-value")
        assertThat(ServerConfigCipher.isEncrypted(encrypted)).isTrue()
        assertThat(ServerConfigCipher.decrypt(encrypted)).isEqualTo(plainText)
    }

    @Test
    fun decrypt_returnsLegacyPlainTextForMigration() {
        val legacyValue = "{\"host\":\"legacy.example\"}"

        assertThat(ServerConfigCipher.decrypt(legacyValue)).isEqualTo(legacyValue)
    }
}
