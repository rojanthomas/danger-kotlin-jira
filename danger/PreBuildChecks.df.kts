@file:DependsOn("danger-kotlin-jira-0.1.0.jar")

import com.rojanthomas.dangerkotlinjira.JiraPlugin
import systems.danger.kotlin.danger
import systems.danger.kotlin.register

register plugin JiraPlugin

danger(args) {
    JiraPlugin.check(
        "https://rojanthomas.atlassian.net/browse",
        setOf("ROJ"),
        setOf(
            this.github.pullRequest.title,
            this.github.pullRequest.body,
        )
    )
}
