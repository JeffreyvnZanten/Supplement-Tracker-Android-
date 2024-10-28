// In Utils.kt
import java.time.LocalDate
import java.time.ZoneId
import java.time.Instant
import java.time.format.DateTimeFormatter

object Utils {
    // ... bestaande functies ...

    private val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

    fun formatTime(timestamp: Long): String {
        return try {
            val instant = Instant.ofEpochMilli(timestamp)
            timeFormatter.format(instant.atZone(ZoneId.systemDefault()))
        } catch (e: Exception) {
            "No time"
        }
    }
}