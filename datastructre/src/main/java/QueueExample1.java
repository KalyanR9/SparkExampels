public class QueueExample1<X> {
    private X[] data;
    private int first;
    private int end;

    public QueueExample1() {

    }

    public QueueExample1(int size) {
        this.data = (X[]) new Object[100];
        first = -1;
        end = -1;
    }

    public int size() {
        return end - first + 1;
    }

    public void enQueue(X item) {
        if (first == (end + 1) % data.length) {
            throw new IllegalArgumentException("");
        } else if (size() == 0) {
            first++;
            end++;
            data[first] = item;
        } else {
            end++;
            data[end] = item;
        }
    }

    public X deQueue() {
        X item = null;
        if (size() == 0) {
            throw new IllegalArgumentException("");
        } else if (first == end) {
            item = data[first];
            data[first] = null;
            first--;
            end--;
        } else {
            item = data[first];
            data[first] = null;
            end--;
        }
        return item;
    }

    public boolean find(X item) {
        boolean found = false;
        if (size() == 0) {
            return found;
        }
        for (int i = 0; i < size(); i++) {
            if (data[i].equals(item)) {
                found = true;
                break;
            }
        }
        return found;
    }

    public X access(int position) {
        if (size() == 0 || position>size()) {
            throw new IllegalArgumentException("");
        }
        int trueIndex = 0;
        for (int i = first; i < end; i++) {
            if (position == trueIndex) {
                return data[trueIndex];
            }
            trueIndex++;

        }
        throw new IllegalArgumentException("Could not get queue item at position: " + position);
    }
}
