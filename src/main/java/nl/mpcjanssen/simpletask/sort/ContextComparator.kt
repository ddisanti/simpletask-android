package nl.mpcjanssen.simpletask.sort

import nl.mpcjanssen.simpletask.dao.gen.TodoItem
import java.util.*

class ContextComparator(caseSensitive: Boolean) : Comparator<TodoItem> {

    private val mStringComparator: AlphabeticalStringComparator

    init {
        this.mStringComparator = AlphabeticalStringComparator(caseSensitive)
    }

    override fun compare(a: TodoItem?, b: TodoItem?): Int {
        if (a === b) {
            return 0
        } else if (a == null) {
            return -1
        } else if (b == null) {
            return 1
        }
        val contextsA = a.task.lists.toMutableList()
        val contextsB = b.task.lists.toMutableList()

        if (contextsA.isEmpty() && contextsB.isEmpty()) {
            return 0
        } else if (contextsA.isEmpty()) {
            return -1
        } else if (contextsB.isEmpty()) {
            return 1
        } else {
            contextsA.sort()
            contextsB.sort()
            return mStringComparator.compare(contextsA[0], contextsB[0])
        }
    }
}
