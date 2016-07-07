package cn.huntdog.first.util;

import java.util.*;

/**
 * Created by JonDai on 2016/7/6.
 */
public class CollectionUtil {
    public CollectionUtil() {
    }

    public static boolean isEmpty(Object object) {
        if(String.class.isInstance(object)) {
            String obj4 = (String)object;
            return obj4 == null?true:obj4.trim().length() == 0;
        } else if(Collection.class.isInstance(object)) {
            Collection obj3 = (Collection)object;
            return obj3 == null?true:obj3.isEmpty();
        } else if(Map.class.isInstance(object)) {
            Map obj2 = (Map)object;
            return obj2 == null?true:obj2.isEmpty();
        } else if(Vector.class.isInstance(object)) {
            Vector obj1 = (Vector)object;
            return obj1 == null?true:obj1.size() == 0;
        } else if(Object[].class.isInstance(object)) {
            Object[] obj = (Object[])((Object[])object);
            return obj == null?true:obj.length == 0;
        } else {
            return object == null;
        }
    }

    public static boolean isNotEmpty(Object object) {
        return !isEmpty(object);
    }

    public static List removeDuplication(List list) {
        HashSet set = new HashSet();
        set.addAll(list);
        list.clear();
        return new ArrayList(set);
    }

    public static Map sortByValue(Map map) {
        LinkedList list = new LinkedList(map.entrySet());
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable)((Map.Entry)o1).getValue()).compareTo(((Map.Entry)o2).getValue());
            }
        });
        LinkedHashMap result = new LinkedHashMap();
        Iterator it = list.iterator();

        while(it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }

    public static Map sortByValue(Map map, final boolean reverse) {
        LinkedList list = new LinkedList(map.entrySet());
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return reverse?-((Comparable)((Map.Entry)o1).getValue()).compareTo(((Map.Entry)o2).getValue()):((Comparable)((Map.Entry)o1).getValue()).compareTo(((Map.Entry)o2).getValue());
            }
        });
        LinkedHashMap result = new LinkedHashMap();
        Iterator it = list.iterator();

        while(it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }

    public static <K, V> Map<K, V> sortByValue(Map<K, V> map, Comparator<K> comparator) {
        LinkedList list = new LinkedList(map.keySet());
        Collections.sort(list, comparator);
        LinkedHashMap result = new LinkedHashMap();
        int i = 0;

        for(int len = list.size(); i < len; ++i) {
            Object key = list.get(i);
            Object value = map.get(key);
            result.put(key, value);
        }

        return result;
    }
}
