package com.rojanthomas.dangerkotlinjira

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class JiraIssuesToHtmlMapperTest {

    @MockK
    private lateinit var jiraIssueCollector: JiraIssueCollector

    @InjectMockKs
    private lateinit var sut: JiraIssuesToHtmlMapper

    @Test
    fun `given an empty jiraUrl, projectKeys & sources, when map is invoked and the collector returns empty, then return an empty string`() {
        every { jiraIssueCollector.collect(any(), any()) } returns emptyList()

        val expected = "<h2>JIRA issues</h2><ul><li>None</li></ul>"
        val actual = sut.map(
            jiraUrl = "",
            projectKeys = emptyList(),
            sources = emptyList(),
        )

        assertEquals(expected, actual)

        verify(exactly = 1) { jiraIssueCollector.collect(emptyList(), "") }
    }

    @Test
    fun `give a non-empty jiraUrl, projectKeys & sources, when map is invoked and the collector returns empty, then return an empty string`() {
        val jiraUrl = "https://rojan.atlassian.net/"
        val projectKeys = listOf("key1", "key2", "key3")
        val sources = listOf(
            "some source",
            "another source"
        )

        every { jiraIssueCollector.collect(any(), any()) } returns emptyList()

        val expected = "<h2>JIRA issues</h2><ul><li>None</li></ul>"
        val actual = sut.map(
            jiraUrl = jiraUrl,
            projectKeys = projectKeys,
            sources = sources,
        )

        assertEquals(expected, actual)

        verify(exactly = 1) { jiraIssueCollector.collect(projectKeys, sources.joinToString()) }
    }

    @Test
    fun `give a non-empty jiraUrl, projectKeys & sources, when map is invoked and the collector returns JIRA issues, then return the HTML for these issues`() {
        val jiraUrl = "https://rojan.atlassian.net/"
        val projectKeys = listOf("ABC", "DEF", "GHI")
        val sources = listOf(
            "some source",
            "another source"
        )

        every { jiraIssueCollector.collect(any(), any()) } returns listOf(
            "ABC-1",
            "DEF-2"
        )

        val expected = "<h2>JIRA issues</h2><ul><li><a href=\"https://rojan.atlassian.net/ABC-1\">ABC-1</a></li><li><a href=\"https://rojan.atlassian.net/DEF-2\">DEF-2</a></li></ul>"
        val actual = sut.map(
            jiraUrl = jiraUrl,
            projectKeys = projectKeys,
            sources = sources,
        )

        assertEquals(expected, actual)

        verify(exactly = 1) { jiraIssueCollector.collect(projectKeys, sources.joinToString()) }
    }
}