package com.pepoc.plugindemo

import org.gradle.api.Plugin
import org.gradle.api.Project

class PluginImpl implements Plugin<Project> {

    @Override
    void apply(Project project) {

//        project.task('testTask') << {
//            println "Hello gradle plugin"
//        }

        project.afterEvaluate {
            project.android.applicationVariants.each {
                variant ->
                    def preDexTask = project.tasks.findByName("preDex${variant.name.capitalize()}")
                    def dexTask = project.tasks.findByName("dex${variant.name.capitalize()}")
                    def proguardTask = project.tasks.findByName("proguard${variant.name.capitalize()}")
                    println "**************** ${variant.name.capitalize()} ********************"
                    println preDexTask
                    println dexTask
                    println proguardTask
                    println "**************** ${variant.name.capitalize()} ********************"

            }
        }
    }
}