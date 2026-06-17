package com.example.myapp_learnhtml.data.local

import androidx.room.TypeConverter
import com.example.myapp_learnhtml.data.model.GlosariumItem
import org.json.JSONArray
import org.json.JSONObject

class Converters {

    @TypeConverter
    fun fromGlosariumList(value: List<GlosariumItem>): String {
        val jsonArray = JSONArray()
        for (item in value) {
            val obj = JSONObject()
            obj.put("istilah", item.istilah)
            obj.put("definisi", item.definisi)
            jsonArray.put(obj)
        }
        return jsonArray.toString()
    }

    @TypeConverter
    fun toGlosariumList(value: String): List<GlosariumItem> {
        val list = mutableListOf<GlosariumItem>()
        val jsonArray = JSONArray(value)
        for (i in 0 until jsonArray.length()) {
            val obj = jsonArray.getJSONObject(i)
            list.add(
                GlosariumItem(
                    istilah = obj.getString("istilah"),
                    definisi = obj.getString("definisi")
                )
            )
        }
        return list
    }

    @TypeConverter
    fun fromStringList(value: List<String>): String {
        val jsonArray = JSONArray()
        value.forEach { jsonArray.put(it) }
        return jsonArray.toString()
    }

    @TypeConverter
    fun toStringList(value: String): List<String> {
        val jsonArray = JSONArray(value)
        return List(jsonArray.length()) { index -> jsonArray.getString(index) }
    }
}
