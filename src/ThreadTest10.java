import javax.swing.JOptionPane;

//동시작업이 이루어지는 멀티 쓰레드 프로그램
class Thread10 extends Thread{ //Thread start -> run()메소드
	@Override
	public void run() {
	
		
		for (int i = 10; i >= 1; i--) {
			System.out.println(i);
			try {
				Thread.sleep(1000);
			}catch(Exception e) {}
		}//for
	}
}
public class ThreadTest10 {
	public static void main(String[] args) {
		Thread10 t = new Thread10();
		t.start();
		
		
		String cityName = JOptionPane.showInputDialog("도시 이름을 입력하세요!");
		System.out.println("도시이름 : " + cityName);
		
		/*문제)
		 * 10부터 1까지 1초 간격으로 카운터 하는 멀티스레드 프로그램을 만들기
		 * 10부터 1까지카운터하는 중간에 도시이름이 출력되는 동시 작업이 이루어진다.
		 * 개발자 테스트까지 해보기
		 */
		
		
		
		
		
		
		
		
	}
}
