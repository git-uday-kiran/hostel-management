package management.hostel;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedList;

@SpringBootTest
class HostelManagementApplicationTests {

	@Test
	void contextLoads() {
	}

	public int findTheWinner(int n, int k) {
		assert n > 0;
		LinkedList<Integer> list = new LinkedList<>();
		for (int i = 0; i < n; i++) list.add(i + 1);
		int position = 0;
		while (list.size() > 1) {
			position = (position + k - 1) % list.size();
			list.remove(position);
			position = position % list.size();
		}
		return list.get(0);
	}

}
