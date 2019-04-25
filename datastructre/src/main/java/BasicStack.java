import sun.misc.Queue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class BasicStack<X> {
	private X [] data;
	private int stackPointer;
	private Queue queue;
	private Stack stack;
	private HashMap map = new HashMap();
	private List<String> list = new ArrayList<>();

	
	public BasicStack() {
		data = (X[]) new Object[1000];
		stackPointer = 0;
	}
	
	public void push(X newItem) {
		data[stackPointer++] = newItem;
		Object x  = map.put(10,10);
		list.add("null");
	}
	
	public X pop() {
		if(stackPointer == 0) {
			throw new IllegalStateException("No more items on the stack"); 
		}

		return data[--stackPointer];
	}
	
	public boolean contains(X item) {
		boolean found = false;

		for(int i = 0; i < stackPointer; i++) {
			if(data[i].equals(item)) {
				found = true;
				break;
			}
		}
		
		return found;
	}
	
	public X access(X item) {
		while(stackPointer > 0) {
			X tmpItem = pop();
			if(item.equals(tmpItem)) {
				return tmpItem;
			}
		}

		//if we didn't find the item in the stack throw an exception
		throw new IllegalArgumentException("Could not find item on the stack: " + item);
		
	}
	
	public int size() {
		return stackPointer;
	}
	
}
