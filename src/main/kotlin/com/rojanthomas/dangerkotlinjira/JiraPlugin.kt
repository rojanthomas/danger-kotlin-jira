package com.rojanthomas.dangerkotlinjira

import systems.danger.kotlin.sdk.DangerPlugin

object JiraPlugin : DangerPlugin() {

    override val id: String
        get() = this.javaClass.name

    fun check(
        jiraUrl: String,
        projectKeys: Set<String>,
        sources: Set<String>,
    ) {
        val projectKeyRegexes = projectKeys.map { getJiraRegex(projectKey = it) }

        val jiraIssues = mutableSetOf<JiraIssue>()

        sources.forEach { source ->
            projectKeyRegexes.forEach { projectKeyRegex ->
                val matchResults = projectKeyRegex.findAll(source)

                matchResults.forEach {
                    jiraIssues.add(JiraIssue(it.value, jiraUrl))
                }
            }
        }

        context.message(jiraIssues.joinToString(", ") { it.asHyperlink })
    }

    private fun getJiraRegex(projectKey: String): Regex {
        return Regex("$projectKey-\\d+")
    }
}