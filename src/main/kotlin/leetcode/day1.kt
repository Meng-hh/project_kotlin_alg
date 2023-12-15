package leetcode

import javax.xml.stream.events.Characters
import kotlin.math.pow

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

fun main() {
    val l1Head = ListNode(9)
    val l2Head = ListNode(1)
    val l2 = ListNode(9)
    val l3 = ListNode(9)
    val l4 = ListNode(9)
    val l5 = ListNode(9)
    val l6 = ListNode(9)
    val l7 = ListNode(9)
    val l8 = ListNode(9)
    val l9 = ListNode(9)
    val l10 = ListNode(9)
    l2Head.next = l2
    l2.next = l3
    l3.next = l4
    l4.next = l5
    l5.next = l6
    l6.next = l7
    l7.next = l8
    l8.next = l9
    l9.next = l10
    addTwoNumbers(l1Head, l2Head)
}

//不能用int表示，用list<Int>
fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
    var weiShu = 0
    var listNode1 = l1
    var listNode2 = l2
    var curResListNode: ListNode? = null
    var headNode : ListNode? = null
    while (listNode1 != null || listNode2 != null) {
        when {
            listNode1 != null && listNode2 != null -> {
                val v = listNode1.`val` + listNode2.`val`+ weiShu
                val currValue = if (v >= 10) v - 10 else v
                val newNode = ListNode(currValue)
                if (curResListNode == null){
                    curResListNode = newNode
                    headNode = newNode
                }
                else{
                    curResListNode.next = newNode
                    curResListNode = curResListNode.next
                }
                weiShu = if (v >= 10) 1 else 0
                listNode1 = listNode1.next
                listNode2 = listNode2.next
            }

            listNode1 == null -> {
                val v = listNode2!!.`val` + weiShu
                val currValue = if (v >= 10) v - 10 else v
                val newNode = ListNode(currValue)
                if (curResListNode == null){
                    headNode = newNode
                    curResListNode = newNode
                }
                else {
                    curResListNode.next = newNode
                    curResListNode = curResListNode.next
                }
                weiShu = if (v >= 10) 1 else 0
                listNode2 = listNode2.next
            }

            else -> {
                val v = listNode1.`val` + weiShu
                val currValue = if (v >= 10) v - 10 else v
                val newNode = ListNode(currValue)
                if (curResListNode == null){
                    headNode = newNode
                    curResListNode = newNode
                }
                else {
                    curResListNode.next = newNode
                    curResListNode = curResListNode.next
                }
                weiShu = if (v >= 10) 1 else 0
                listNode1 = listNode1.next
            }
        }
    }
    if (weiShu ==1) curResListNode?.next = ListNode(weiShu)
    return headNode
}