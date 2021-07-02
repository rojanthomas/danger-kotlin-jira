package com.rojanthomas.dangerkotlinjira

import io.mockk.impl.annotations.InjectMockKs
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class JiraIssueCollectorTest {

    @InjectMockKs
    private lateinit var sut: JiraIssueCollector

    @Test
    fun `given empty projectKeys & empty source, when collect is invoked, then return an empty list`() {
        val expected = emptyList<String>()
        val actual = sut.collect(projectKeys = emptyList(), "")

        assertEquals(expected, actual)
    }

    @Test
    fun `given projectKeys & empty source, when collect is invoked, then return an empty list`() {
        val expected = emptyList<String>()
        val actual = sut.collect(projectKeys = PROJECT_KEYS, source = "")

        assertEquals(expected, actual)
    }

    @Nested
    @DisplayName("given a single projectKey")
    inner class GivenASingleProjectKey {

        private val singleProjectKey = PROJECT_KEYS.first()

        @Test
        fun `and a non-empty source with no JIRA issues, when collect is invoked, then return an empty list`() {
            val expected = emptyList<String>()
            val actual = sut.collect(projectKeys = listOf(singleProjectKey), SOURCE_WITH_NO_JIRA_OR_ISSUES)

            assertEquals(expected, actual)
        }

        @Test
        fun `and the source has a single JIRA issue of the projectKey, when collect is invoked, return just that JIRA issue`() {
            val expected = listOf("ABC-1")
            val actual = sut.collect(projectKeys = listOf(singleProjectKey), SOURCE_WITH_SINGLE_JIRA_AND_SINGLE_ISSUE)

            assertEquals(expected, actual)
        }

        @Test
        fun `and the source has a multiple JIRA issue of the projectKey, when collect is invoked, return the JIRA issue respecting the order in which projectKeys were provided`() {
            val expected = listOf("ABC-1", "ABC-12", "ABC-123", "ABC-1234")
            val actual =
                sut.collect(projectKeys = listOf(singleProjectKey), SOURCE_WITH_SINGLE_JIRA_AND_MULTIPLE_ISSUES)

            assertEquals(expected, actual)
        }
    }

    @Nested
    @DisplayName("given multiple projectKeys")
    inner class GivenMultipleProjectKeys {

        @Test
        fun `and a non-empty source with no JIRA issues, when collect is invoked, then return an empty list`() {
            val expected = emptyList<String>()
            val actual = sut.collect(projectKeys = PROJECT_KEYS, SOURCE_WITH_NO_JIRA_OR_ISSUES)

            assertEquals(expected, actual)
        }

        @Test
        fun `and the source has a single JIRA issue of one projectKey, when collect is invoked, return just that JIRA issue`() {
            val expected = listOf("ABC-1")
            val actual = sut.collect(projectKeys = PROJECT_KEYS, SOURCE_WITH_SINGLE_JIRA_AND_SINGLE_ISSUE)

            assertEquals(expected, actual)
        }

        @Test
        fun `and the source has a multiple JIRA issue of the projectKeys, when collect is invoked, return the JIRA issue respecting the order in which projectKeys were provided`() {
            val expected = listOf("ABC-1",
                "ABC-12",
                "ABC-123",
                "ABC-1234",
                "DEF-1",
                "DEF-12",
                "DEF-123",
                "DEF-1234",
                "GHI-1",
                "GHI-12",
                "GHI-123",
                "GHI-1234")
            val actual =
                sut.collect(projectKeys = PROJECT_KEYS, SOURCE_WITH_MULTIPLE_JIRAS_AND_ISSUES)

            assertEquals(expected, actual)
        }
    }

    private companion object {
        val PROJECT_KEYS = listOf("ABC", "DEF", "GHI")

        val PROJECT_KEY_TO_MOCK_ISSUES = PROJECT_KEYS.map {
            it to listOf("$it-1", "$it-12", "$it-123", "$it-1234")
        }

        const val SOURCE_WITH_NO_JIRA_OR_ISSUES = "This is a PR title that has no JIRA issues"

        val SOURCE_WITH_SINGLE_JIRA_AND_SINGLE_ISSUE =
            "This is a PR title that has some JIRA issues: ${PROJECT_KEY_TO_MOCK_ISSUES.first().second.first()}"

        val SOURCE_WITH_SINGLE_JIRA_AND_MULTIPLE_ISSUES =
            "This is a PR title that has the all JIRA issues: ${PROJECT_KEY_TO_MOCK_ISSUES.map { it.second }.first()}"

        val SOURCE_WITH_MULTIPLE_JIRAS_AND_ISSUES =
            "This is a PR title that has the all JIRA issues: ${PROJECT_KEY_TO_MOCK_ISSUES.flatMap { it.second }}"
    }
}
