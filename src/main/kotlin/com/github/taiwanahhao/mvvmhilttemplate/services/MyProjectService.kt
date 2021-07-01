package com.github.taiwanahhao.mvvmhilttemplate.services

import com.github.taiwanahhao.mvvmhilttemplate.MyBundle
import com.intellij.openapi.project.Project

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
