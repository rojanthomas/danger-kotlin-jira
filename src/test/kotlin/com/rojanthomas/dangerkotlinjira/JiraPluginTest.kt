package com.rojanthomas.dangerkotlinjira

import io.mockk.clearAllMocks
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
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
    fun test() {
        JiraPlugin.check(
            "https://comparethemarket.atlassian.net/browse/",
            projectKeys = setOf("MA", "TA"),
            sources = setOf(
                "hello",
                "this is a jira issue MA-4458",
                "this is a jira issue TA-4451, TA-4450"
            )
        )

        verify { context.message("<a href='https://comparethemarket.atlassian.net/browse/MA-4458'>MA-4458</a>") }
    }
}