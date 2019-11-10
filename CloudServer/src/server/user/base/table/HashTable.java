package server.user.base.table;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.zip.CRC32;


public class HashTable<Key, Val> implements Serializable
{

	private class Entry<Key, Val>
	{
		public Key key;
		public Val val;
		
		public Entry(Key k, Val v)
		{
			key = k;
			val = v;
		}
	}
	
	
	
	private static final long serialVersionUID = -3062485692012766436L;
	
	private int size;
	private ArrayList<LinkedList<Entry<Key, Val>>> indexes;
	private CRC32 hash;
	
	
	@SuppressWarnings("unchecked")
	public HashTable(int capacity)
	{
		indexes = new ArrayList<LinkedList<Entry<Key, Val>>>(capacity);
		
		for (int index = 0; index < capacity; index++) 
		{
			indexes.add(index, new LinkedList<Entry<Key,Val>>());		
		}
		
		size = capacity;
	}
	
	private int toIndex(Key key)
	{
		return Math.abs(key.hashCode()) % this.size;
	}
	
	@SuppressWarnings("unchecked")
	public Val get(Key key) 
	{		
		LinkedList<Entry<Key,Val>> list = indexes.get(toIndex(key));
		
		for (Entry<Key, Val> entry : list) 
		{
			if (entry.key.equals(key)) return entry.val;
		}
		
		return null;
	}
	
	
	public boolean put(Key key, Val val)
	{
		LinkedList<Entry<Key,Val>> list = indexes.get(toIndex(key));
		return list.add(new Entry<Key,Val>(key,val));
	}

	public int getSize() 
	{
		return size;
	}
	
	public void setSize(int newSize)
	{
		ArrayList<LinkedList<Entry<Key,Val>>> newBase = new ArrayList<LinkedList<Entry<Key,Val>>>(newSize);
		
		for (int index = 0; index < newSize; index++) 
		{
			newBase.add(index, new LinkedList<Entry<Key,Val>>());		
		}
		
		for (LinkedList<Entry<Key,Val>> list : indexes)
		{
			for (Entry<Key,Val> entry : list) 
			{
				int index = Math.abs(entry.key.hashCode()) % newSize;
				newBase.get(index).add(entry);
			}
		}
		
		this.size = newSize;
		this.indexes = newBase;
	}

	
	
	
	
}
