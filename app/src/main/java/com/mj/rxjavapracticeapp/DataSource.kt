package com.mj.rxjavapracticeapp

class DataSource {

    companion object {
        fun createTasksList(): List<Task> {

            var task = mutableListOf<Task>()

            task.add(Task("a", true, 3))
            task.add(Task("b", false, 2))
            task.add(Task("c", true, 1))
            task.add(Task("d", false, 5))

            return task
        }
    }

}