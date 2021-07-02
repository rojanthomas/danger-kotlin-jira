package com.rojanthomas.dangerkotlinjira

internal class JiraIssuesToHtmlMapper(private val jiraIssueCollector: JiraIssueCollector) {

    fun map(
        jiraUrl: String,
        projectKeys: List<String>,
        sources: List<String>,
    ): String {
        val jiraIssues = jiraIssueCollector.collect(projectKeys = projectKeys, source = sources.joinToString())

        return StringBuilder().append(HtmlUtil.getHeading("JIRA issues"))
            .append(
                HtmlUtil.getUnorderedList(
                    jiraIssues.takeIf { it.isNotEmpty() }
                        ?.joinToString(separator = "") { jiraIssue ->
                            HtmlUtil.getListElement(
                                HtmlUtil.getHyperlinkTextHtml(text = jiraIssue, url = jiraUrl + jiraIssue)
                            )
                        }
                        ?: HtmlUtil.getListElement("None")
                )
            )
            .toString()
    }
}
