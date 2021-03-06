package jadx.tests.integration.loops;

import jadx.core.dex.nodes.ClassNode;
import jadx.tests.api.IntegrationTest;

import org.junit.Test;

import static jadx.tests.api.utils.JadxMatchers.containsOne;
import static jadx.tests.api.utils.JadxMatchers.countString;
import static org.junit.Assert.assertThat;

public class TestBreakInLoop extends IntegrationTest {

	public static class TestCls {
		private int f;

		private void test(int[] a, int b) {
			for (int i = 0; i < a.length; i++) {
				a[i]++;
				if (i < b) {
					break;
				}
			}
			this.f++;
		}
	}

	@Test
	public void test() {
		ClassNode cls = getClassNode(TestCls.class);
		String code = cls.getCode().toString();

		assertThat(code, containsOne("for (int i = 0; i < a.length; i++) {"));
//		assertThat(code, containsOne("a[i]++;"));
		assertThat(code, containsOne("if (i < b) {"));
		assertThat(code, containsOne("break;"));
		assertThat(code, containsOne("this.f++;"));

		assertThat(code, countString(0, "else"));
	}
}
