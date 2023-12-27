package leetcode


fun main() {
    val l1 = ListNode(1)
    val l2 = ListNode(2)
    val l3 = ListNode(3)
    val l4 = ListNode(4)
    val l5 = ListNode(5)
    l1.next = l2
    l2.next = l3
    l3.next = l4
    l4.next = l5
    reverseList(l1)
}

//使用快慢指针
fun reverseList(head: ListNode?): ListNode? {
    var slow:ListNode? = null

    var fast = head
    while (fast != null) {
        val temp = fast.next
        fast.next = slow
        slow = fast
        fast = temp
    }
    return slow
}