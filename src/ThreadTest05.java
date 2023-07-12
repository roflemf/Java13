import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/* AWT GUI + 멀티쓰레드가 결합된 프로그램으로 awt 프레임 윈도우에 스레드에 의해서 지나가는 글자를 출력 */

class Thread05 extends Frame implements Runnable{
	int x = 1; //프레임 윈도우 x 좌표를 저장할 변수
	
	public Thread05() {
		setBackground(new Color(0,0,0));//프레임 배경색을 R(red),G(green),B(blue) 색상코드로 지정, 배경색을 검정으로 지정
		setSize(680,360); //프레임 폭과 높이를 지정
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);//프레임 윈도우 닫기
			}//현재 프레임 윈도우 닫기 했을 때 호출
		});//익명 클래스 문법으로 프레임 윈도우 닫기 이벤트처리
	}//생성자

	@Override
	public void run() {
		while(true) {
			try{
				Thread.sleep(100);//스레드를 일정시간 대기상태로 둔다.(밀리세컨드 단위 ; 1000->1초)
			}catch(InterruptedException ie) {}
			repaint();//다시 그리고자 그리기 메서드 호출
			x+=5; //스레드에 의해서 x좌표가 5씩 증가
		}
	}//스레드 문장 구현

	@Override
	public void paint(Graphics g) {
		Dimension d;//Diemnsion은 폭과 높이를 가지는 클래스
		d=getSize();//프레임 윈도우 창 크기를 구현
		g.setColor(Color.ORANGE); //글자색을 오렌지색으로 지정
	
		
		
		g.drawString("내일부터 장마가 시작됩니다. 시간당 00mm가 내리니 주의하시길 바랍니다.", x, d.height/2);		
		
		
		
		/*문제) 
		 * 글자크기 키우고, 글꼴 궁서체 진하게,
		 * 프레임 윈도우 폭을 벗어나면 지나간 글자 사라지지않게하기
		 */
	}//무엇을 그리고자 호출되는 메서드
	
	
	
	
	
	
}
public class ThreadTest05 {
	public static void main(String[] args) {
		
		Thread05 th= new Thread05();
		Thread th01 = new Thread(th);
		th01.start();//멀티스레드 시작
	}
}
