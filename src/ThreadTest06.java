/* 쓰레드 동기화 즉 임계영역을 지정하지 않은 상태에서 출금작업이 이루어져서 은행 계좌 잔고가 음수가 나오는 예제 
 * 동기화란 한번에 하나의 스레드에 의해서만 출금작업이 이루어지도록 락을 거는 행위를 말한다.
 * 하나의 스레드에 의해서만 어떤 출금과 특정 작업이이루어 지도록 하는 영역을 임계영역이라고 한다.*/
class Account06{
	private int balance=1000; //은행잔고
	
	public int getBalance() {
		return balance;
	}
	
	//동기화가 처리 안된 출금 작업 => 동시에 여러 스레드에 의해서 출금작업이 이뤄짐
	public void withdraw(int money) {
		if(balance >= money) {//잔액이 출금금액 이상이면
			try {
				Thread.sleep(1000);//sleep()은 스레드 스케줄링 메서드로 스레드를 일시정지
								   //단위가 밀리세컨드단위. 1000으로 지정하면 1초간 쉼.
			}catch(InterruptedException ie){}
			balance -= money; //출금 발생
		}
	}

}//은행계좌 클래스

class Thread06 implements Runnable{
	
	Account06 acc = new Account06();//계좌클래스 객체 생성
	
	
	@Override
	public void run() {
		while(acc.getBalance() > 0) { //계좌 잔액 0보다 클때
			int money = (int)(Math.random()*3+1)*100; //random()메서드는 0.0이상 1.0미만 사이의 실수 숫자 난수 발생
													  // -> *3하면 0.0이상 3.0미만 실수숫자 발생 
													  // -> +1 하면 1.0이상 4.0미만 사이의 실수 숫자 난수 발생
													  // -> (int)로 형변환 하면 반올림 하지 않고, 소수점 이하는 버리고 1이상 4미만
													  // -> *100하면 100이상 400미만 
													  //즉, 100,200,300 중 임의의 정수 출금 숫자 금액이 발생
			acc.withdraw(money); //실제 출금 작업
			System.out.println("계좌 잔액 : "+acc.getBalance()); 
		}
		
	}//스레드 문장 구현
	
}//멀티스레드 클래스
public class ThreadTest06 {
	public static void main(String[] args) {
		
		Thread06 tr = new Thread06();
		new Thread(tr).start();//첫번째 스레드 시작
		Thread th02=new Thread(tr);
		th02.start();//두번째 스레드 시작
		
	}

}
