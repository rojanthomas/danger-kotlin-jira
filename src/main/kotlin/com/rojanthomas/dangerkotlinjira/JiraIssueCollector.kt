package com.rojanthomas.dangerkotlinjira

internal class JiraIssueCollector {

    private val jiraIssueRegexFactory = JiraIssueRegexFactory()

    fun collect(
        projectKeys: Set<String>,
        sources: Set<String>,
    ): MutableSet<String> {
        val projectKeyRegexes = projectKeys.map { jiraIssueRegexFactory.create(projectKey = it) }

        val jiraIssues = mutableSetOf<String>()

        sources.forEach { source ->
            projectKeyRegexes.forEach { projectKeyRegex ->
                val matchResults = projectKeyRegex.findAll(source)

                matchResults.forEach {
                    jiraIssues.add(it.value)
                }
            }
        }

        return jiraIssues
    }
}
