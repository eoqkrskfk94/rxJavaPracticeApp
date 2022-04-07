package com.mj.rxjavapracticeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Predicate
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private lateinit var text: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        text = findViewById(R.id.text)

        var taskObservable: Observable<Task> = Observable
            .fromIterable(DataSource.createTasksList())
            .subscribeOn(Schedulers.io())
            .filter {
                try {
                    Thread.sleep(1000)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                it.isComplete
            }
            .observeOn(AndroidSchedulers.mainThread())

        taskObservable.subscribe(object : Observer<Task> {

            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: Task) {
                Log.d("TAG", "onNext" + Thread.currentThread().name)
                Log.d("TAG", "onNext" + t.description)

            }

            override fun onError(e: Throwable) {
                Log.e("TAG", "onError")
            }

            override fun onComplete() {

            }

        })
    }
}