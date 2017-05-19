package etchee.com.wasedabusschedule

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import etchee.com.wasedabusschedule.Data.DataContract

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById(R.id.button) as Button
        button.setOnClickListener { _ ->
            val intent = Intent(this, Main2Activity::class.java)
            val bundle = Bundle()
            bundle.putString(DataContract.GlobalConstants:: PASS_STRING.toString(), "Hello")
            startActivity(intent)
        }
    }

}


