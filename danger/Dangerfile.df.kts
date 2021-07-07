@file:DependsOn(artifactsCoordinates = ["io.github.rojanthomas:danger-kotlin-jira:0.1.0"])

import com.rojanthomas.dangerkotlinjira.JiraPlugin
import systems.danger.kotlin.danger
import systems.danger.kotlin.register

register plugin JiraPlugin

danger(args) {
    JiraPlugin.check(
        "https://rojanthomas.atlassian.net/browse/",
        listOf("ROJ", "THO"),
        listOf(
            this.github.pullRequest.title,
            this.github.pullRequest.body,
        )
    )
}
