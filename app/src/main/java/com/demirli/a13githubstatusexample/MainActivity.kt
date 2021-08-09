package com.demirli.a13githubstatusexample

import android.graphics.Color
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        githubStatus_btn.setOnClickListener {
            getDataFromGithubStatus()
        }
    }

    fun getDataFromGithubStatus(){
        val download1 = DownloadGithubStatus()
        val url1 = "https://kctbh9vrtdwd.statuspage.io/api/v2/status.json"
        download1.execute(url1)

        val download2 = DownloadGithubStatus2()
        val url2 = "https://kctbh9vrtdwd.statuspage.io/api/v2/summary.json"
        download2.execute(url2)

    }

    inner class DownloadGithubStatus(): AsyncTask<String, Void, String>() {

        override fun doInBackground(vararg params: String?): String {
            var result1 = ""

            var url1: URL

            var httpURLConnection1: HttpURLConnection

            url1 = URL(params[0])

            httpURLConnection1 = url1.openConnection() as HttpURLConnection
            val inputStream1 = httpURLConnection1.inputStream
            val inputStreamReader1 = InputStreamReader(inputStream1)
            var data1 = inputStreamReader1.read()

            while(data1 > 0){
                val character1 = data1.toChar()
                result1 += character1

                data1 = inputStreamReader1.read()
            }

            return result1
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            println(result)

            val jsonObject = JSONObject(result)
            val status = jsonObject.getString("status")
            val jsonObject2 = JSONObject(status)
            val description = jsonObject2.getString("description")

            githubStatus_tv.text = description
        }


    }

    inner class DownloadGithubStatus2(): AsyncTask<String, Void, String>() {

        override fun doInBackground(vararg params: String?): String {
            var result2 = ""

            var url2: URL

            var httpURLConnection2: HttpURLConnection

            url2 = URL(params[0])

            httpURLConnection2 = url2.openConnection() as HttpURLConnection
            val inputStream2 = httpURLConnection2.inputStream
            val inputStreamReader2 = InputStreamReader(inputStream2)
            var data2 = inputStreamReader2.read()

            while(data2 > 0){
                val character2 = data2.toChar()
                result2 += character2

                data2 = inputStreamReader2.read()
            }

            return result2
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            val jsonObject = JSONObject(result)
            val components = jsonObject.getString("components")
            val jsonArray = JSONArray(components)

            val gitoperations = jsonArray.getString(0)
            val jsonObject2 = JSONObject(gitoperations)
            val gitoperationsStatus = jsonObject2.getString("status")
            gitOperationsStatus_tv.text = gitoperationsStatus

            val apiRequests = jsonArray.getString(1)
            val jsonObject3 = JSONObject(apiRequests)
            val apiRequestsStatus = jsonObject3.getString("status")
            apiRequestsStatus_tv.text = apiRequestsStatus

        }
    }
}
