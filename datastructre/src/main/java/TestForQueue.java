//Java program for linked-list implementation of queue

//A linked list (LL) node to store a queue entry 
class QNode {
	int key;
	QNode next;

	// constructor to create a new linked list node
	public QNode(int key) {
		this.key = key;
		this.next = null;
	}
}

// A class to represent a queue
// The queue, front stores the front node of LL and rear stores ths
// last node of LL
class Test1 {
	QNode front, rear;

	public Test1() {
		this.front = this.rear = null;
	}

	// Method to add an key to the queue.
	void enqueue(int key) {
		// Create a new LL node
		QNode temp = new QNode(key);
		// If queue is empty, then new node is front and rear both
		if (this.rear == null) {
			this.front = this.rear = temp;
			return;
		}
		// Add the new node at the end of queue and change rear
		this.rear.next = temp;
		this.rear = this.rear.next;
	}

	// Method to remove an key from queue.
	QNode dequeue() {
		// If queue is empty, return NULL.
		if (this.front == null)
			return null;

		// Store previous front and move front one node ahead
		QNode temp = this.front;
		this.front = this.front.next;

		// If front becomes NULL, then change rear also as NULL
		if (this.front == null)
			this.rear = null;
		return temp;
	}
}

// Driver class
public class TestForQueue {
	public static void main(String[] args) {
		Test1 q = new Test1();
		q.enqueue(10);
		q.enqueue(20);
		q.dequeue();
		q.dequeue();
		q.enqueue(30);
		q.enqueue(40);
		q.enqueue(50);
		System.out.println("Dequeued item is " + q.dequeue().key);
	}
}
