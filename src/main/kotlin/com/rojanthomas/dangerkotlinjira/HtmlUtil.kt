package com.rojanthomas.dangerkotlinjira

internal object HtmlUtil {

    const val lineBreak = "<br>"

    fun getHyperlinkTextHtml(text: String, url: String): String {
        return "<a href=\"$url\">$text</a>"
    }
}