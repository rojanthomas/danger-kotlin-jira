package com.rojanthomas.dangerkotlinjira

internal class JiraIssueCollector {

    fun collect(
        projectKeys: List<String>,
        source: String,
    ): List<String> {
        if (projectKeys.isEmpty() || source.isEmpty()) return emptyList()

        println("Project keys: $projectKeys")
        println("Source: $source")

        return projectKeys.createJoinedRegex()
            .findAll(source)
            .map { matchResult ->
                println(matchResult.groupValues)
                matchResult.groupValues
            }
            .toList()
            .flatten()
            .distinct()
    }

    private fun List<String>.createJoinedRegex(): Regex {
        return Regex(joinToString(separator = "|") { "$it-\\d+" })
    }
}
