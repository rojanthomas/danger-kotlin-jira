package com.rojanthomas.dangerkotlinjira

import io.mockk.clearAllMocks
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import systems.danger.kotlin.sdk.DangerContext

@ExtendWith(MockKExtension::class)
internal class JiraPluginTest {

    @RelaxedMockK
    private lateinit var context: DangerContext

    @BeforeEach
    fun setUp() {
        JiraPlugin.context = context
    }

    @AfterEach
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `given a jiraUrl, multiple project keys & sources, when check is invoked, then return the correct message`() {
        JiraPlugin.check(
            "https://comparethemarket.atlassian.net/browse/",
            projectKeys = listOf("PA", "DKJ"),
            sources = listOf(
                "PR title with JIRA issue PA-123",
                "this is a jira issue PA-456",
                "this is a jira issue PA-789, DKJ-123",
                "this is text with unrelated JIRA issues referenced CA-123",
                "this is text with no JIRA issues referenced"
            )
        )

        verify { context.message("<h2>JIRA issues</h2><ul><li><a href=\"https://comparethemarket.atlassian.net/browse" +
                "/PA-123\">PA-123</a></li><li><a href=\"https://comparethemarket.atlassian.net/browse/PA-456\">PA-456" +
                "</a></li><li><a href=\"https://comparethemarket.atlassian.net/browse/PA-789\">PA-789</a></li><li><a " +
                "href=\"https://comparethemarket.atlassian.net/browse/DKJ-123\">DKJ-123</a></li></ul>") }
    }
}