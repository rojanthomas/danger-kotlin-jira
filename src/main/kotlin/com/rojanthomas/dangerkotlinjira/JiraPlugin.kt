package com.rojanthomas.dangerkotlinjira

import systems.danger.kotlin.sdk.DangerPlugin

object JiraPlugin : DangerPlugin() {

    private val jiraIssuesToHtmlMapper = JiraIssuesToHtmlMapper(
        JiraIssueCollector(
        )
    )

    override val id: String
        get() = this.javaClass.name

    /**
     * @param jiraUrl the full base JIRA url e.g. https://myjira.atlassian.net/
     * @param projectKeys a set of project keys e.g setOf("ABCD", "EFG"). The order of this list will determine the order of the JIRA
     * keys in the Danger comment.
     * @param sources a list of strings to scan for JIRA keys.
     */
    fun check(
        jiraUrl: String,
        projectKeys: List<String>,
        sources: List<String>,
    ) {
        context.message(
            message = jiraIssuesToHtmlMapper.map(
                jiraUrl = jiraUrl,
                projectKeys = projectKeys,
                sources = sources
            ).also {
                println("Message: $it")
            }
        )
    }
}