package com.example.week12

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnGet: Button = findViewById(R.id.btnGet)
        btnGet.setOnClickListener {
            val rq: RequestQueue = Volley.newRequestQueue(this)

            val objRequest = JsonArrayRequest(
                Request.Method.GET,
                "http://demo.onmyfinger.com/home/getAll",
                null,
                Response.Listener { response ->
                    try {

                        var nameList: StringBuffer = StringBuffer()

                        for (i in 0 until response.length()) {

                            val objStudent: JSONObject = response.getJSONObject(i)
                            nameList.append(objStudent.getString("name") + "\n")
                        }
                        findViewById<TextView>(R.id.tvResult).setText(nameList)

                    } catch (e: JSONException) {
                        findViewById<TextView>(R.id.tvResult).setText(e.message)

                    }
                },
                Response.ErrorListener { error ->
                    findViewById<TextView>(R.id.tvResult).setText(error.message)
                }

            )

            rq?.add(objRequest)


        }


        val btnSearch: Button = findViewById(R.id.btnSearch)

        btnSearch.setOnClickListener {
            val strId: String = findViewById<TextView>(R.id.tfID).text.toString()

            val rq: RequestQueue = Volley.newRequestQueue(this)

            val objRequest = JsonObjectRequest(
                Request.Method.GET,
                "http://demo.onmyfinger.com/home/getById?id=" + strId,
                null,
                Response.Listener { response ->
                    try {

                        val objStudent: JSONObject = response
                        val name: String = objStudent.get("name").toString()
                        val programme: String = objStudent.get("programme").toString()

                        findViewById<TextView>(R.id.tfName).setText(name)
                        findViewById<TextView>(R.id.tfProgramme).setText(programme)


                    } catch (e: JSONException) {
                        findViewById<TextView>(R.id.tvResult).setText(e.message)

                    }
                },
                Response.ErrorListener { error ->
                    findViewById<TextView>(R.id.tvResult).setText(error.message)
                }

            )

            rq?.add(objRequest)
        }

        val btnAdd: Button = findViewById(R.id.btnAdd)

        btnAdd.setOnClickListener {
            val strId: String = findViewById<TextView>(R.id.tfID).text.toString()
            val strName: String = findViewById<TextView>(R.id.tfName).text.toString()
            val strProgramme: String = findViewById<TextView>(R.id.tfProgramme).text.toString()

            val rq: RequestQueue = Volley.newRequestQueue(this)

            val objRequest = JsonObjectRequest(
                Request.Method.GET,
                "http://demo.onmyfinger.com/home/Add?id=" + strId
                        + "&name=" + strName + "&programme=" + strProgramme,

                null,
                Response.Listener { response ->
                    try {

                        Toast.makeText(this, "Record Added", Toast.LENGTH_LONG).show()


                    } catch (e: JSONException) {
                        findViewById<TextView>(R.id.tvResult).setText(e.message)

                    }
                },
                Response.ErrorListener { error ->
                    findViewById<TextView>(R.id.tvResult).setText(error.message)
                }

            )

            rq?.add(objRequest)
        }
    }


}