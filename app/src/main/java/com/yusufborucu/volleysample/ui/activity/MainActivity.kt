package com.yusufborucu.volleysample.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.yusufborucu.volleysample.R
import com.yusufborucu.volleysample.model.User
import com.yusufborucu.volleysample.ui.adapter.UserAdapter
import org.json.JSONArray
import org.json.JSONException

class MainActivity : AppCompatActivity() {

    val users = mutableListOf<User>()
    var recylerView: RecyclerView? = null
    val URL = "https://jsonplaceholder.typicode.com/users"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recylerView = findViewById(R.id.rvUsers)

        getUsers()
    }

    private fun getUsers() {
        val stringRequest = StringRequest(
            Request.Method.GET,
            URL,
            Response.Listener<String> { s ->
                try {
                    val array = JSONArray(s)
                    for (i in 0 until array.length()) {
                        val obj = array.getJSONObject(i)
                        users.add(
                            User(
                                obj["name"].toString(),
                                obj["email"].toString(),
                                obj["phone"].toString()
                            )
                        )
                    }
                    recylerView?.layoutManager = LinearLayoutManager(this)
                    recylerView?.adapter = UserAdapter(users)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            Response.ErrorListener { error ->
                Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
            })

        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add<String>(stringRequest)
    }
}
