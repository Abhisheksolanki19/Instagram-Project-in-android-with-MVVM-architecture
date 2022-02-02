package com.abhishekSolanki.instagramApp

import android.content.Context
import com.abhishekSolanki.instagramApp.di.component.DaggerTestComponent
import com.abhishekSolanki.instagramApp.di.component.TestComponent
import com.abhishekSolanki.instagramApp.di.module.ApplicationTestModule
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class TestComponentRule(private val context:Context):TestRule {

    private var testComponent:TestComponent?= null

    fun getContext()= context

    private fun setupDaggerTestComponentInApplication(){
        val application = context.applicationContext as InstagramApplication
        testComponent = DaggerTestComponent.builder()
            .applicationTestModule(ApplicationTestModule(application))
            .build()
        application.setComponent(testComponent!!)
    }

    override fun apply(base: Statement, description: Description?): Statement {
        return object : Statement(){
            @Throws(Throwable::class)
            override fun evaluate() {
                try {
                    setupDaggerTestComponentInApplication()
                    base.evaluate()
                }finally {
                    testComponent = null
                }
            }
        }
    }
}