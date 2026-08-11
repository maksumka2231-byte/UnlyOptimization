package com.unlymng.optimization.data.service

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

/**
 * Сервис для управления кодами активации (150 одноразовых + 5 пробных)
 * Коды действуют навсегда после активации
 */
class ActivationCodeService @Inject constructor(
    context: Context
) {
    private val prefs: SharedPreferences = context.getSharedPreferences(
        "activation_codes",
        Context.MODE_PRIVATE
    )

    // 150 одноразовых кодов активации - действуют навсегда
    private val permanentCodes = listOf(
        "UNLY-2024-0001", "UNLY-2024-0002", "UNLY-2024-0003", "UNLY-2024-0004", "UNLY-2024-0005",
        "UNLY-2024-0006", "UNLY-2024-0007", "UNLY-2024-0008", "UNLY-2024-0009", "UNLY-2024-0010",
        "UNLY-2024-0011", "UNLY-2024-0012", "UNLY-2024-0013", "UNLY-2024-0014", "UNLY-2024-0015",
        "UNLY-2024-0016", "UNLY-2024-0017", "UNLY-2024-0018", "UNLY-2024-0019", "UNLY-2024-0020",
        "UNLY-2024-0021", "UNLY-2024-0022", "UNLY-2024-0023", "UNLY-2024-0024", "UNLY-2024-0025",
        "UNLY-2024-0026", "UNLY-2024-0027", "UNLY-2024-0028", "UNLY-2024-0029", "UNLY-2024-0030",
        "UNLY-2024-0031", "UNLY-2024-0032", "UNLY-2024-0033", "UNLY-2024-0034", "UNLY-2024-0035",
        "UNLY-2024-0036", "UNLY-2024-0037", "UNLY-2024-0038", "UNLY-2024-0039", "UNLY-2024-0040",
        "UNLY-2024-0041", "UNLY-2024-0042", "UNLY-2024-0043", "UNLY-2024-0044", "UNLY-2024-0045",
        "UNLY-2024-0046", "UNLY-2024-0047", "UNLY-2024-0048", "UNLY-2024-0049", "UNLY-2024-0050",
        "UNLY-2024-0051", "UNLY-2024-0052", "UNLY-2024-0053", "UNLY-2024-0054", "UNLY-2024-0055",
        "UNLY-2024-0056", "UNLY-2024-0057", "UNLY-2024-0058", "UNLY-2024-0059", "UNLY-2024-0060",
        "UNLY-2024-0061", "UNLY-2024-0062", "UNLY-2024-0063", "UNLY-2024-0064", "UNLY-2024-0065",
        "UNLY-2024-0066", "UNLY-2024-0067", "UNLY-2024-0068", "UNLY-2024-0069", "UNLY-2024-0070",
        "UNLY-2024-0071", "UNLY-2024-0072", "UNLY-2024-0073", "UNLY-2024-0074", "UNLY-2024-0075",
        "UNLY-2024-0076", "UNLY-2024-0077", "UNLY-2024-0078", "UNLY-2024-0079", "UNLY-2024-0080",
        "UNLY-2024-0081", "UNLY-2024-0082", "UNLY-2024-0083", "UNLY-2024-0084", "UNLY-2024-0085",
        "UNLY-2024-0086", "UNLY-2024-0087", "UNLY-2024-0088", "UNLY-2024-0089", "UNLY-2024-0090",
        "UNLY-2024-0091", "UNLY-2024-0092", "UNLY-2024-0093", "UNLY-2024-0094", "UNLY-2024-0095",
        "UNLY-2024-0096", "UNLY-2024-0097", "UNLY-2024-0098", "UNLY-2024-0099", "UNLY-2024-0100",
        "UNLY-2024-0101", "UNLY-2024-0102", "UNLY-2024-0103", "UNLY-2024-0104", "UNLY-2024-0105",
        "UNLY-2024-0106", "UNLY-2024-0107", "UNLY-2024-0108", "UNLY-2024-0109", "UNLY-2024-0110",
        "UNLY-2024-0111", "UNLY-2024-0112", "UNLY-2024-0113", "UNLY-2024-0114", "UNLY-2024-0115",
        "UNLY-2024-0116", "UNLY-2024-0117", "UNLY-2024-0118", "UNLY-2024-0119", "UNLY-2024-0120",
        "UNLY-2024-0121", "UNLY-2024-0122", "UNLY-2024-0123", "UNLY-2024-0124", "UNLY-2024-0125",
        "UNLY-2024-0126", "UNLY-2024-0127", "UNLY-2024-0128", "UNLY-2024-0129", "UNLY-2024-0130",
        "UNLY-2024-0131", "UNLY-2024-0132", "UNLY-2024-0133", "UNLY-2024-0134", "UNLY-2024-0135",
        "UNLY-2024-0136", "UNLY-2024-0137", "UNLY-2024-0138", "UNLY-2024-0139", "UNLY-2024-0140",
        "UNLY-2024-0141", "UNLY-2024-0142", "UNLY-2024-0143", "UNLY-2024-0144", "UNLY-2024-0145",
        "UNLY-2024-0146", "UNLY-2024-0147", "UNLY-2024-0148", "UNLY-2024-0149", "UNLY-2024-0150"
    )

    // 5 пробных кодов - действуют 7 дней
    private val trialCodes = listOf(
        "UNLY-TRIAL-001",
        "UNLY-TRIAL-002",
        "UNLY-TRIAL-003",
        "UNLY-TRIAL-004",
        "UNLY-TRIAL-005"
    )

    /**
     * Проверяет валидность кода активации
     * @return true если код валиден, false если уже использован или неверный
     */
    fun validateCode(code: String): Boolean {
        val trimmedCode = code.trim().uppercase()
        
        // Проверяем, не был ли код уже использован
        val usedCodes = getUsedCodes()
        if (usedCodes.contains(trimmedCode)) {
            return false
        }

        // Проверяем, есть ли код в списке постоянных
        if (permanentCodes.contains(trimmedCode)) {
            markCodeAsUsed(trimmedCode, isPermanent = true)
            return true
        }

        // Проверяем, есть ли код в списке пробных
        if (trialCodes.contains(trimmedCode)) {
            markCodeAsUsed(trimmedCode, isPermanent = false, trialDays = 7)
            return true
        }

        return false
    }

    /**
     * Проверяет, активирована ли прибыль на устройстве
     */
    fun isActivated(): Boolean {
        return prefs.getBoolean("is_activated", false)
    }

    /**
     * Проверяет, находится ли пробный период еще в силе
     */
    fun isTrialValid(): Boolean {
        val trialEndTime = prefs.getLong("trial_end_time", 0)
        if (trialEndTime == 0L) return false
        return System.currentTimeMillis() < trialEndTime
    }

    /**
     * Получает количество оставшихся дней пробного периода
     */
    fun getRemainingTrialDays(): Int {
        val trialEndTime = prefs.getLong("trial_end_time", 0)
        if (trialEndTime == 0L) return 0
        val remainingMs = trialEndTime - System.currentTimeMillis()
        return (remainingMs / (1000 * 60 * 60 * 24)).toInt()
    }

    /**
     * Отмечает код как использованный
     */
    private fun markCodeAsUsed(code: String, isPermanent: Boolean, trialDays: Int = 0) {
        val usedCodes = getUsedCodes().toMutableSet()
        usedCodes.add(code)
        prefs.edit().putStringSet("used_codes", usedCodes).apply()

        if (isPermanent) {
            prefs.edit().putBoolean("is_activated", true).apply()
        } else {
            val trialEndTime = System.currentTimeMillis() + (trialDays * 24 * 60 * 60 * 1000)
            prefs.edit().putLong("trial_end_time", trialEndTime).apply()
        }
    }

    /**
     * Получает список использованных кодов
     */
    private fun getUsedCodes(): Set<String> {
        return prefs.getStringSet("used_codes", emptySet()) ?: emptySet()
    }

    /**
     * Получает список все�� доступных кодов (для администратора)
     */
    fun getAllCodes(): Map<String, String> {
        val codes = mutableMapOf<String, String>()
        permanentCodes.forEach { codes[it] = "permanent" }
        trialCodes.forEach { codes[it] = "trial" }
        return codes
    }
}
