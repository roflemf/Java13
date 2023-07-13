import java.util.ArrayList;

class Table14{
	String[] dishNames = {"donut","donut","burger"};//음식 재료 3개를 문자열 배열에 저장
	final int MAX_FOOD = 6;
	private ArrayList<String> dishes = new ArrayList<>();

	//테이블에 음식을 추가하는 것을 동기화 처리
	public synchronized void add(String dish) {
		if(dishes.size() >=MAX_FOOD) {//테이블에 추가된 음식 개수가 6가지를 초과하면 음식 추가를 중단
			return;//종료기능
		}
		dishes.add(dish);//컬렉션에 음식 추가
		System.out.println("추가된 음식 목록 : " + dishes.toString());
	}//add()

	//테이블에서 음식 제거
	public boolean remove(String dishName) {

		synchronized (this) {
			while(dishes.size() == 0) {//size()메서드는 컬렉션 원소 개수를 반환
				String name = Thread.currentThread().getName(); //현재 실행중인 스레드 이름을 반환
				System.out.println(name + " is waiting."); //소비자 스레드를 기다리게 한다.

				try {Thread.sleep(500);}catch(InterruptedException ie) {}

			}//while
			for(int i =0; i<dishes.size(); i++) {
				if(dishName.equals(dishes.get(i))) { //먹을음식과 컬렉션에 저장된 음식 내용이 같다면 
					dishes.remove(i);//음식을 먹기(제거)
					return true;//소비자가 원하는 음식을 먹었다면 true를 반환
				}//if
			}//for
		}//한번에 하나의 스레드에 의해서만 동작하도록 동기화 처리
		return false;//소비자가 원하는 음식을 못먹었다면 false를 반환
	}//remove

	public int dishNum() { return dishNames.length;}//음식 재료개수 3 반환

}//Table13 class

//소비자 스레드 클래스
class Customer14 implements Runnable{
	
	private Table14 table;
	private String food;
	
	//생성자 오버로딩
	public Customer14() {} 
	
	public Customer14(Table14 table, String food) {
		this.table = table;
		this.food = food;
	}
	
	
	@Override
	public void run() {
		while(true) {//무한루프문
			try {Thread.sleep(10);}catch(InterruptedException ie) {}
			String name = Thread.currentThread().getName();
			
		}//while
	}//run
	
	boolean eatFood() { return table.remove(food);}
		
	
}//Customer14 class

public class ThreadTest14 {
	public static void main(String[] args) {

	}

}
