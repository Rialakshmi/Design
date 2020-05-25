/*
Implement FreqStack, a class which simulates the operation of a stack-like data structure.

FreqStack has two functions:

    push(int x), which pushes an integer x onto the stack.
    pop(), which removes and returns the most frequent element in the stack.
        If there is a tie for most frequent element, the element closest to the top of the stack is removed and returned.

 

Example 1:

Input: 
["FreqStack","push","push","push","push","push","push","pop","pop","pop","pop"],
[[],[5],[7],[5],[7],[4],[5],[],[],[],[]]
Output: [null,null,null,null,null,null,null,5,7,5,4]
Explanation:
After making six .push operations, the stack is [5,7,5,7,4,5] from bottom to top.  Then:

pop() -> returns 5, as 5 is the most frequent.
The stack becomes [5,7,5,7,4].

pop() -> returns 7, as 5 and 7 is the most frequent, but 7 is closest to the top.
The stack becomes [5,7,5,4].

pop() -> returns 5.
The stack becomes [5,7,4].

pop() -> returns 4.
The stack becomes [5,7].

 

Note:

    Calls to FreqStack.push(int x) will be such that 0 <= x <= 10^9.
    It is guaranteed that FreqStack.pop() won't be called if the stack has zero elements.
    The total number of FreqStack.push calls will not exceed 10000 in a single test case.
    The total number of FreqStack.pop calls will not exceed 10000 in a single test case.
    The total number of FreqStack.push and FreqStack.pop calls will not exceed 150000 across all test cases.
*/
class FrequencyStack {
    PriorityQueue<Element> pq;
    HashMap<Integer,Integer> hm;
    int sequence;
    public FreqStack() {
        pq=new PriorityQueue<>(new ElementComparator());
        hm=new HashMap<>();
        sequence=0;
    }
    
    public void push(int x) {
        hm.put(x,hm.getOrDefault(x,0)+1);
        pq.offer(new Element(x,hm.get(x),sequence++));
    }
    
    public int pop() {
        int num = pq.poll().num;
        if(hm.get(num)>1)
            hm.put(num,hm.getOrDefault(num,0)-1);
        else
            hm.remove(num);
        return num;
    }
}

class Element {
    int num;
    int frequency;
    int sequence;
    public Element(int num,int frequency,int sequence) {
        this.num = num;
        this.frequency = frequency;
        this.sequence = sequence;
    }
}

class ElementComparator implements Comparator<Element> {
    @Override
    public int compare(Element e1,Element e2) {
        return e2.frequency - e1.frequency==0?e2.sequence - e1.sequence:e2.frequency - e1.frequency;
    }
}

/**
 * Your FreqStack object will be instantiated and called as such:
 * FreqStack obj = new FreqStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 */
