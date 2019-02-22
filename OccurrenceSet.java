import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class OccurrenceSet<T> implements Set<T> {
	HashMap<T, Integer> hash = new HashMap<T, Integer>();
	
	@Override
	public boolean add(T e) {
		if(hash.containsKey(e)){
			hash.put(e, hash.get(e)+1);
			return false;
		}else{
			hash.put(e,1);
			return true;
		}
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		boolean all = false;
		for(T t:c){
			all |= this.add(t);
		}
		return all;
	}
	
	@Override
	public boolean remove(Object o) {
		if(hash.remove(o)==null){
			return false;
		}
		return true;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		boolean all = false;
		for(Object o:c){
			all |= this.remove(o);
		}
		return all;
	}
	
	@Override
	public boolean contains(Object o) {
		return hash.containsKey(o);
	}
	
	@Override
	public boolean containsAll(Collection<?> c) {
		for(Object o : c){
			if(!this.contains(o)){
				return false;
			}
		}
		return true;
	}



	@Override
	public boolean retainAll(Collection<?> c) {
		boolean all = false;
		for(T t:this){
			if(!c.contains(t)){
				all = this.remove(t);
			}
		}
		return all;
	}
	@Override
	public Iterator<T> iterator() {
		ArrayList<T> list = new ArrayList<T>(hash.keySet());
		Collections.sort(list,new Comparator<T>(){
			@Override
			public int compare(T O1, T O2){
				return hash.get(O1)-hash.get(O2);
			}
		});
		return list.iterator();			
	}

	@Override
	public Object[] toArray() {
		ArrayList<T> list = new ArrayList<T>(hash.keySet());
		Collections.sort(list,new Comparator<T>(){
			@Override
			public int compare(T O1, T O2){
				return hash.get(O1)-hash.get(O2);
			}
		});
		return list.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		ArrayList<T> list = new ArrayList<T>((Collection<? extends T>) hash.keySet());
		Collections.sort(list,new Comparator<T>(){
			@Override
			public int compare(T O1, T O2){
				return hash.get(O1)-hash.get(O2);
			}
		});
		return list.toArray(a);
	}
	
	@Override
	public String toString(){
		int counter =0;
		Object[] array = this.toArray();
		String string = "[";
		for(;counter<this.size()-1;counter++){
			string+=array[counter]+", ";
		}
		string+=array[counter]+"]";
		return string;
	}
	
	@Override
	public int size() {
		return hash.size();
	}
	@Override
	public boolean isEmpty() {
		return hash.isEmpty();
	}
	
	@Override
	public void clear() {
		hash.clear();
	}

}
