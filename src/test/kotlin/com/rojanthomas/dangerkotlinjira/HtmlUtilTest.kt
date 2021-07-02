package com.rojanthomas.dangerkotlinjira

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class HtmlUtilTest {

    @Test
    fun `given a HtmlUtil object, when lineBreak is accessed, then return the correct html`() {
        val expected = "<br>"
        val actual = HtmlUtil.lineBreak

        assertEquals(expected, actual)
    }

    @Test
    fun `given empty text & url, when getHyperlinkTextHtml is invoked, then return the correct html`() {
        val text = ""
        val url = ""

        val expected = "<a href=\"$url\">$text</a>"
        val actual = HtmlUtil.getHyperlinkTextHtml(text = text, url = url)

        assertEquals(expected, actual)
    }

    @Test
    fun `given text but empty url, when getHyperlinkTextHtml is invoked, then return the correct html`() {
        val text = "This is a link"
        val url = ""

        val expected = "<a href=\"$url\">$text</a>"
        val actual = HtmlUtil.getHyperlinkTextHtml(text = text, url = url)

        assertEquals(expected, actual)
    }

    @Test
    fun `given text & url, when getHyperlinkTextHtml is invoked, then return the correct html`() {
        val text = "This is a link"
        val url = "https://www.rojan.me/"

        val expected = "<a href=\"$url\">$text</a>"
        val actual = HtmlUtil.getHyperlinkTextHtml(text = text, url = url)

        assertEquals(expected, actual)
    }
}