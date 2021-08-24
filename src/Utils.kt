import net.nshc.xshield.cli.App
import java.util.regex.Pattern

fun String.matchResults(regex: String): List<String> {
    val result = mutableListOf<String>()
    val pattern = Pattern.compile(regex)
    val matcher = pattern.matcher(this)

    while (matcher.find()) {
        result.add(matcher.group())
    }

    return result
}