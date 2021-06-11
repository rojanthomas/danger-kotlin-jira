package com.rojanthomas.dangerkotlinjira

internal class JiraIssueRegexFactory {

    fun create(projectKey: String): Regex {
        return Regex("$projectKey-\\d+")
    }
}
