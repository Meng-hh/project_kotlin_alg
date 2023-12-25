package leetcode

/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */


fun main() {
    val newNode = MyLinkedList2()
    newNode.addAtTail(1)
    newNode.addAtHead(2)
    newNode.deleteAtIndex(0)
    val get0 = newNode.get(0)


}

fun removeElements(head: ListNode?, `val`: Int): ListNode? {
    var headNode = if (head?.`val` != `val`) head else head.next

    var loopNode = headNode
    while (loopNode?.next != null) {
        if (loopNode.next!!.`val` == `val`) {
            val temp = if (loopNode.next!!.next != null) loopNode.next!!.next else null
            loopNode.next = temp
            continue
        }
        loopNode = loopNode.next
    }
    headNode = if (headNode?.`val` == `val`) headNode.next else headNode
    return headNode
}

//没有虚拟头节点
class MyLinkedList {
    var `val`: Int? = null
    var next: MyLinkedList? = null
    var pre: MyLinkedList? = null

    fun get(index: Int): Int {
        return getByIndex(index)?.`val` ?: -1
    }

    private fun getByIndex(index: Int): MyLinkedList? {
        if (index < 0) return null
        var loop: MyLinkedList? = this.getHead()
        var count = 0
        var res: MyLinkedList? = null
        while (loop != null) {
            if (count == index) {
                res = loop
                break
            }
            loop = loop.next
            count++
        }
        return res
    }

    fun addAtHead(`val`: Int) {
        val head = getHead()
        val flag = head?.`val` == null
        val newNode = if (flag) head.apply { this?.`val` = `val` } else MyLinkedList().apply {
            this.`val` = `val`
            this.next = head
        }
        if (!flag) {
            head?.pre = newNode
            newNode?.next = head
        }
    }

    private fun getHead(): MyLinkedList? {
        var loop = this
        while (loop.pre != null) {
            loop = loop.pre!!
        }
        return loop
    }

    private fun getTail(): MyLinkedList? {
        var loop = this
        while (loop.next != null) {
            loop = loop.next!!
        }
        return loop
    }

    fun addAtTail(`val`: Int) {
        val tail = getTail()
        val flag = tail?.`val` == null
        val newNode = if (flag) tail.apply { this?.`val` = `val` } else MyLinkedList().apply {
            this.`val` = `val`
            this.pre = tail
        }
        if (!flag) {
            tail?.next = newNode
            newNode?.pre = tail
        }
    }

    fun addAtIndex(index: Int, `val`: Int) {
        val target = getByIndex(index)
        val newNode = MyLinkedList().apply { this@apply.`val` = `val` }
        val preNode = getByIndex(index - 1)
        if (target == null) {
            if (preNode != null) {
                preNode.next = newNode
                newNode.pre = preNode
            }
        } else {
            if (preNode != null) {
                preNode.next = newNode
                newNode.pre = preNode
                newNode.next = target
                target.pre = newNode
            } else {
                newNode.next = target
                target.pre = newNode
            }
        }
    }

    fun deleteAtIndex(index: Int) {
        val target = getByIndex(index) ?: return
        val preNode = getByIndex(index - 1)
        if (preNode != null) {
            if (target == this) {
                target.`val` = target.pre!!.`val`
                val pre1 = preNode.pre
                preNode.pre = null
                preNode.next = null
                target.pre = pre1
                pre1?.next = target
            } else {
                preNode.next = target.next
                target.next?.pre = preNode
            }
        } else {
            if (target == this) {
                if (target.next != null) {
                    target.`val` = target.next!!.`val`
                    val next1 = target.next!!.next
                    target.next?.pre = null
                    target.next?.next = null
                    target.next = next1
                    target.pre = null
                } else {
                    target.`val` = null
                    target.next = null
                    target.pre = null
                }
            } else {
                target.next?.pre = null
                target.next = null
            }
        }
    }
 }


class MyLinkedList2 {
    var `val`: Int? = null
    var next: MyLinkedList2? = null
    var pre: MyLinkedList2? = null
    private var virHeadNode: MyLinkedList2? = null

    constructor() {
        //这里应该是具有虚拟节点的其他类，并且只适用于单链表，故放弃此种写法
        virHeadNode = MyLinkedList2()
    }

    fun get(index: Int): Int {
        return getByIndex(index)?.`val` ?: -1
    }

    private fun getByIndex(index: Int): MyLinkedList2? {
        if (index < 0) return null
        var count = 0
        var loop = virHeadNode!!.next
        while (loop != null) {
            loop = loop.next
            if (index == count)
                return loop
            count++
        }
        return null
    }

    fun addAtHead(`val`: Int) {
        val head = virHeadNode!!.next
        val newNode = MyLinkedList2().apply { this.`val` = `val` }
        if (head != null) {
            head.pre = newNode
            newNode.next = head
        }
        virHeadNode!!.next = newNode
        newNode.pre = virHeadNode
    }

    fun addAtTail(`val`: Int) {
        val newNode = MyLinkedList2().apply { this.`val` = `val` }
        getTail().let { tail ->
            tail.next = newNode
            newNode.pre = tail
        }
    }

    private fun getTail(): MyLinkedList2 {
        var loop = virHeadNode
        while (loop!!.next != null) {
            loop = loop.next
        }
        return loop
    }

    fun addAtIndex(index: Int, `val`: Int) {
        val indexNode = getByIndex(index)
        val preNode = getByIndex(index - 1)
        val newNode = MyLinkedList2().apply { this.`val` = `val` }
        if (indexNode != null) {
            if (preNode != null) {
                preNode.next = newNode
                newNode.pre = preNode
            } else {
                newNode.pre = virHeadNode
                virHeadNode!!.next = newNode
            }
            newNode.next = indexNode
            indexNode.pre = newNode
        } else {
            if (preNode != null) {
                preNode.next = newNode
                newNode.pre = preNode
            } else {
                newNode.pre = virHeadNode
                virHeadNode!!.next = newNode
            }
        }

    }

    fun deleteAtIndex(index: Int) {
        val indexNode = getByIndex(index) ?: return
        val preNode = getByIndex(index - 1)
        if (preNode != null) {
            val nextNode = indexNode.next
            preNode.next = nextNode
            nextNode?.pre = preNode
        } else {
            virHeadNode!!.next = indexNode.next
            indexNode.next?.pre = virHeadNode
        }
        indexNode.pre = null
        indexNode.next = null
        indexNode.`val` = null
    }
}
