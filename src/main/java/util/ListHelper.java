package util;

import java.util.List;

public class ListHelper {

	public static <T> T getLast(List<T> list) {
		T t = null;
		if(list != null) {
			t = list.get(list.size() - 1);
		}
		return t;
	}

}
