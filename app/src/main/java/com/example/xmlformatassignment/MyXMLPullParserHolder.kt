package com.example.xmlformatassignment

import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.io.InputStream

class MyXMLPullParserHolder {
    private val students = ArrayList<Student>()
    private var text: String? = null

    private var id = 0
    private var name = ""
    private var grade = 0.0

    fun parse(inputStream: InputStream) : List<Student> {
        try {
            val factory = XmlPullParserFactory.newInstance()
            val parser = factory.newPullParser()
            parser.setInput(inputStream, null)
            var eventType = parser.eventType
            while (eventType != XmlPullParser.END_DOCUMENT) {
                val tagName = parser.name
                when (eventType) {
                    XmlPullParser.TEXT -> text = parser.text
                    XmlPullParser.END_TAG -> when {
                        tagName.equals("id", ignoreCase = true) -> {
                            id = text!!.toInt()
                        }
                        tagName.equals("name", ignoreCase = true) -> {
                            name = text.toString()
                        }
                        tagName.equals("grade", ignoreCase = true) -> {
                            grade = text!!.toDouble()
                            students.add(Student(id, name, grade))
                        }
                        else -> {}
                    }

                    else -> {}
                }
                eventType = parser.next()
            }

        } catch (e: XmlPullParserException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return students
    }
}