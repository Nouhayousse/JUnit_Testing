package TestingLab;

import java.util.ArrayList;
import java.util.List;

public class Basket {
    private final List<String> items=new ArrayList<>();

    public void add(String item){
        if(item==null || item.isBlank()){
            throw new IllegalArgumentException("The article shouldn't be Empty !");
        }
        items.add(item);
    }

    public int size(){
        return items.size();
    }

    public boolean isEmpty(){
        return items.isEmpty();
    }

    public List<String> items(){
        return List.copyOf(items);
    }
}
