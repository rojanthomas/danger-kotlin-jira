package com.rojanthomas.dangerkotlinjira

import systems.danger.kotlin.sdk.DangerPlugin

object JiraPlugin : DangerPlugin() {

    private val jiraIssuesToHtmlMapper = JiraIssuesToHtmlMapper()

    override val id: String
        get() = this.javaClass.name

    fun check(
        jiraUrl: String,
        projectKeys: Set<String>,
        sources: Set<String>,
    ) {
        context.message(
            message = jiraIssuesToHtmlMapper.map(
                jiraUrl = jiraUrl,
                projectKeys = projectKeys,
                sources = sources
            )
        )
    }
}