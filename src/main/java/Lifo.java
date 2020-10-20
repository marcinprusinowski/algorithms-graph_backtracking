import java.util.ArrayList;
import java.util.List;

public class Lifo {
    List<Integer> lifo = new ArrayList<>();
    public void destroy(){
        this.lifo.clear();
    }

    public void push(int value){
        lifo.add(value);
    }

    public int pop(){
        int lastIndex = lifo.size() -1;
        int item = lifo.get(lastIndex);
        lifo.remove(lastIndex);
        return item;
    }

    public int peek(){
        int lastIndex = lifo.size();
        return lifo.get(lastIndex-1);
    }

    public int size(){
        return lifo.size();
    }

    public boolean isEmpty(){
        return ( lifo.size() == 0 ) ;
    }

    public void print(){
        for (Integer integer : lifo) {
            System.out.print(integer);
        }
        System.out.println();
    }
}
