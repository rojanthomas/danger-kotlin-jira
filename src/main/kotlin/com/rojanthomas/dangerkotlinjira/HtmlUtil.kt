package com.rojanthomas.dangerkotlinjira

internal object HtmlUtil {

    const val lineBreak = "<br>"

    fun getListElement(html: String): String {
        return "<li>$html</li>"
    }

    fun getUnorderedList(html: String): String {
        return "<ul>$html</ul>"
    }

    fun getHyperlinkTextHtml(text: String, url: String): String {
        return "<a href=\"$url\">$text</a>"
    }

    fun getHeading(text: String, style: String = "h2"): String {
        return "<$style>$text</$style>"
    }
}