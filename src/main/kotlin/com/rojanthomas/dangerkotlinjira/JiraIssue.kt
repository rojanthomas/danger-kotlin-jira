package com.rojanthomas.dangerkotlinjira

data class JiraIssue(val jiraIssue: String, val jiraUrl: String)

val JiraIssue.jiraIssueUrl: String
    get() = jiraUrl + jiraIssue

val JiraIssue.asHyperlink: String
    get() = "<a href=\"${jiraIssueUrl}\">$jiraIssue</a>"
