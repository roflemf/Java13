import javax.swing.JOptionPane;

/* 멀티스레드가 아닌 단일 프로그램이다. 
 * 즉, 하나의 프로그램이 끝나야 다음 프로그램이 실행됌.
 */

public class ThreadTest09 {
	public static void main(String[] args) {

		String inputName = JOptionPane.showInputDialog("이름을 입력하세요");
		//JOptionPane.showInputDialog 스윙 ui 는 확인 취소 버튼을 가지고 메세지를 담고 입력필드를 가진 다이아로그창을 만든다.
		System.out.println("입력하신 이름 : " + inputName);

		for(int i = 10; i>=1; i--) {
			System.out.println(i);
			try {
				Thread.sleep(1000);//1초간 잠시 일시정지    * sleep; 반드시 예외처리와 함께 써야함(아니면 에러)
			}catch(InterruptedException ie){}
		}//for
	}
}
