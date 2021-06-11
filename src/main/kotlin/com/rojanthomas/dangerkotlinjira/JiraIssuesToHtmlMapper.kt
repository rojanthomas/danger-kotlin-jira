package com.rojanthomas.dangerkotlinjira

internal class JiraIssuesToHtmlMapper {

    private val jiraIssueCollector = JiraIssueCollector()

    fun map(
        jiraUrl: String,
        projectKeys: Set<String>,
        sources: Set<String>,
    ): String {
        return jiraIssueCollector.collect(projectKeys = projectKeys, sources = sources)
            .joinToString(HtmlUtil.lineBreak) { HtmlUtil.getHyperlinkTextHtml(text = it, url = jiraUrl + it) }
    }
}