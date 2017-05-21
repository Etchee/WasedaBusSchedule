package etchee.com.wasedabusschedule

/*
 * Copyright 2016 Realm Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import etchee.com.wasedabusschedule.Data.Schedule
import io.realm.Realm
import io.realm.Sort
import io.realm.examples.kotlin.model.Person
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    private var realm: Realm by Delegates.notNull()
    private var TAG = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close() // Remember to close Realm when done.
    }



    private fun showStatus(txt: String) {
        Log.i(TAG, txt)
        val tv = TextView(this)
        tv.text = txt

    }

    private fun basicCRUD(realm: Realm) {
        showStatus("Perform basic Create/Read/Update/Delete (CRUD) operations...")

        // All writes must be wrapped in a transaction to facilitate safe multi threading
        realm.executeTransaction {
            // Add a person
            val person = realm.createObject(Person::class.java, 0)
            person.name = "Young Person"
            person.age = 14
        }

        // Find the first person (no query conditions) and read a field
        val person = realm.where(Person::class.java).findFirst()
        showStatus(person.name + ": " + person.age)

        // Update person in a transaction
        realm.executeTransaction {
            person.name = "Senior Person"
            person.age = 99
            showStatus(person.name + " got older: " + person.age)
        }
    }

    private fun basicQuery(realm: Realm) {
        showStatus("\nPerforming basic Query operation...")
        showStatus("Number of persons: ${realm.where(Schedule::class.java).count()}")

        val results = realm.where(Schedule::class.java).equalTo("age", 99).findAll()

        showStatus("Size of result set: " + results.size)
    }

    private fun basicLinkQuery(realm: Realm) {
        showStatus("\nPerforming basic Link Query operation...")
        showStatus("Number of persons: ${realm.where(Schedule::class.java).count()}")

        val results = realm.where(Schedule::class.java).equalTo("cats.name", "Tiger").findAll()

        showStatus("Size of result set: ${results.size}")
    }

}
